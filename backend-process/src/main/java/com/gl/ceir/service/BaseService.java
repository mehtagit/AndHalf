package com.gl.ceir.service;

public abstract class BaseService implements Service{

	@Override
	public void fetchAndProcess() {
		fetch();
	}

}
