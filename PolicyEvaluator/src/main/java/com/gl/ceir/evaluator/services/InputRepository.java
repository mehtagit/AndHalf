package com.gl.ceir.evaluator.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.gl.ceir.config.system.request.Request;

public interface InputRepository {
	// File one file request
	public List<Request> read();

	public CountDownLatch getFileCountDownLatch();

	public Map<Long, List<Request>> readMap();

	public Map<Long, List<Request>> getRequests();

	public List<Request> read(int count);

	public void moveFile();
}
