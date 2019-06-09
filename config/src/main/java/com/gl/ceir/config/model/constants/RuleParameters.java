package com.gl.ceir.config.model.constants;

public enum RuleParameters {
	MSISDN("MSISDN"), IMEI("IMEI");
	private String name;

	RuleParameters(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
