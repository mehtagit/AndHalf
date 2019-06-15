package com.gl.ceir.evaluator.services;

import com.gl.ceir.config.system.request.Request;

public interface Chain {
	public abstract void setNext(Chain nextInChain);

	public abstract void process(Request request);
}
