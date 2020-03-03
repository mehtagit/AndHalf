package com.gl.ceir.service;

import java.util.HashMap;
import java.util.Map;

import com.gl.ceir.entity.SystemConfigurationDb;

public abstract class BaseService implements Service{

	protected Map<String, SystemConfigurationDb> systemConfigMap = new HashMap<>();

	@Override
	public void fetchAndProcess() {
		fetch();
	}

}
