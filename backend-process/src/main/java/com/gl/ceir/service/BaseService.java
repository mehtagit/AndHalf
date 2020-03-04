package com.gl.ceir.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gl.ceir.configuration.PropertiesReader;
import com.gl.ceir.entity.SystemConfigurationDb;

public abstract class BaseService implements Service{

	protected Map<String, SystemConfigurationDb> systemConfigMap = new HashMap<>();
	
	protected PropertiesReader propertiesReader;

	@Override
	public void fetchAndProcess() {
		fetch();
	}

	public PropertiesReader getPropertiesReader() {
		return propertiesReader;
	}

	@Autowired
	public final void setPropertiesReader(PropertiesReader propertiesReader) {
		this.propertiesReader = propertiesReader;
	}

	
}
