package com.gl.ceir.evaluator.services.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.Step;

@Service
public class DuplicateImeiProcessor implements Runnable {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private Executor executor;

	private BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

	@Autowired
	@Qualifier("ruleSolverStep")
	private Step ruleSolverStep;

	public BlockingQueue<Request> getQueue() {
		return queue;
	}

	@Override
	public void run() {
		logger.info("DuplicateImeiProcessor Thread Started");
		while (true) {
			try {
				Request request = queue.take();
				logger.info("Duplicate " + request);
				ruleSolverStep.process(request);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

}
