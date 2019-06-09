package com.gl.ceir.evaluator.services;

import java.util.List;

import com.gl.ceir.config.system.request.Request;

public interface RequestPuller {
	public List<Request> puller();

	public List<Request> puller(int size);
}
