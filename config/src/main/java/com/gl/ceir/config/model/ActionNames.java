package com.gl.ceir.config.model;

public enum ActionNames {
	USER_REGULARIZED("USER_REGULARIZED"), SYSTEM_REGULARIZED("SYSTEM_REGULARIZED");
	private String name;

	ActionNames(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
