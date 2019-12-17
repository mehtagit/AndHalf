package com.gl.CEIR.FileProcess.service;

import com.gl.ceir.config.model.WebActionDb;

@FunctionalInterface
public interface WebActionFactory {
	public WebActionService create(WebActionDb webActionDb);
}
