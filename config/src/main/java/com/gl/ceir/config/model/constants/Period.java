package com.gl.ceir.config.model.constants;

public enum Period {

	GRACE("GRACE"), POST_GRACE("POST_GRACE");

	private String name;

	Period(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
