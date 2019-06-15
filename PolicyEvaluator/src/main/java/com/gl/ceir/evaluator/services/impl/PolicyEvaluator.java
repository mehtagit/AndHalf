package com.gl.ceir.evaluator.services.impl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.ceir.config.service.DeviceSnapShotService;
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

	public CountDownLatch fileCountDownLatch;

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@Autowired
	private PendingActionsService pendingActionsService;

	@Autowired
	private Result result;

	@Autowired
	public PolicyEvaluator() {
		System.out.println("I am registered PolicyEvaluator");
	}

	public CountDownLatch getFileCountDownLatch() {
		return fileCountDownLatch;
	}

	public void run() {
		while (true) {
			try {
				result.reset();
				List<Request> requests = inputRepository.read();

				if (requests == null) {
					continue;
				} else {
					fileCountDownLatch = new CountDownLatch(requests.size());
					for (Request request : requests) {
						executor.execute(() -> {
							ruleSolverStep.process(request);
						});
					}

					fileCountDownLatch.await();
					save(result);
					break;
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			break;
		}
	}

	@Transactional
	private void save(Result result) {
		deviceSnapShotService.saveAll(result.getDeviceSnapshotBatch());
		pendingActionsService.saveAll(result.getPendingBatch());
	}

}
