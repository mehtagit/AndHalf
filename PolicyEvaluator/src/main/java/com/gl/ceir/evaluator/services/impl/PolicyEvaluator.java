package com.gl.ceir.evaluator.services.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;
import com.gl.ceir.config.service.NullMsisdnRegularizedService;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.Step;
import com.gl.ceir.evaluator.pojo.Result;
import com.gl.ceir.evaluator.services.InputRepository;

@Service
public class PolicyEvaluator {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private InputRepository inputRepository;

	@Autowired
	private Executor executor;

	@Autowired
	@Qualifier("ruleSolverStep")
	private Step ruleSolverStep;

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@Autowired
	private PendingActionsService pendingActionsService;

	@Autowired
	private DuplicateImeiMsisdnService duplicateImeiMsisdnService;

	@Autowired
	private NullMsisdnRegularizedService nullMsisdnRegularizedService;

	@Autowired
	private Result result;

	@Autowired
	public PolicyEvaluator() {
		System.out.println("I am registered PolicyEvaluator");
	}

	public void run() {
		while (true) {
			try {
				result.reset();
				Map<Long, List<Request>> requests = inputRepository.readMap();

				if (requests == null) {
					Thread.sleep(1000);
					continue;
				} else {
					for (Long imei : requests.keySet()) {
						Request request = requests.get(imei).get(0);
						executor.execute(() -> {
							ruleSolverStep.process(request);
						});
					}

					inputRepository.getFileCountDownLatch().await();
					// if (save(result))
					inputRepository.moveFile();
					// logger.info("total file count = "+requests.size());
					// logger.info("query insert in snapshor =
					// "+result.getDeviceSnapshotBatch().size());
					Thread.sleep(1000);
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Transactional
	private boolean save(Result result) {
		try {
			deviceSnapShotService.saveAll(result.getDeviceSnapshotBatch());
			pendingActionsService.saveAll(result.getPendingBatch());
			duplicateImeiMsisdnService.saveAll(result.getDuplicateImeiMsisdnsBatch());
			nullMsisdnRegularizedService.saveAll(result.getNullMsisdnRegularizeds());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
