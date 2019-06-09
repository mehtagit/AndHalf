package com.gl.ceir.evaluator.services;

import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.system.request.Request;

public interface OutpuWriter {
	public boolean write(Request request);

	public boolean write(Request request, ActionNames actionNames);
}
