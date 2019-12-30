package com.gl.CEIR.FileProcess.service;

import com.gl.CEIR.FileProcess.model.entity.WebActionDb;

@FunctionalInterface
public interface WebActionFactory {
	public WebActionService create(WebActionDb webActionDb);
}
