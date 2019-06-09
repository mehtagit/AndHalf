package com.gl.ceir.config.model.constants;

public enum ImeiStatus {
	SYSTEM_REGULARIZED("SYSTEM_REGULARIZED"), AUTO_REGULARIZED("AUTO_REGULARIZED"), USER_REGULARIZED(
			"USER_REGULARIZED");
	private String name;

	ImeiStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}