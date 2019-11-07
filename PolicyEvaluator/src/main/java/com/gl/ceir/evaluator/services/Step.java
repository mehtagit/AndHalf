package com.gl.ceir.evaluator.services;

import com.gl.ceir.config.system.request.Request;

public interface Step {

	public abstract void process(Request request);
}
