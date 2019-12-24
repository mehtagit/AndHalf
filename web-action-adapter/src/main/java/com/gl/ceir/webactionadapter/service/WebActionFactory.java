package com.gl.ceir.webactionadapter.service;

import com.gl.ceir.config.model.WebActionDb;

@FunctionalInterface
public interface WebActionFactory {
	public WebActionService create(WebActionDb webActionDb);
}
