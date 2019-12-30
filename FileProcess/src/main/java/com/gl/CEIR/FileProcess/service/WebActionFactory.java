package com.gl.CEIR.FileProcess.service;

import com.gl.ceir.fileprocess.model.entity.WebActionDb;

@FunctionalInterface
public interface WebActionFactory {
	public WebActionService create(WebActionDb webActionDb);
}
