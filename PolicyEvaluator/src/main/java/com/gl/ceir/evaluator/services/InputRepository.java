package com.gl.ceir.evaluator.services;

import java.util.List;

import com.gl.ceir.config.system.request.Request;

public interface InputRepository {
	// File one file request
	public List<Request> read();

	public List<Request> read(int count);

	public void moveFile();
}
