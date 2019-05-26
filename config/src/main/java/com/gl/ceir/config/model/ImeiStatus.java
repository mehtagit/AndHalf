package com.gl.ceir.config.model;

public enum ImeiStatus {
	OK("OK"), NOT_OK("NOT_OK");
	private String name;

	ImeiStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static ImeiStatus fromOrdinal(String nn) {
		if (nn.equals("OK"))
			return ImeiStatus.OK;
		else if (nn.equals("NOT_OK"))
			return ImeiStatus.NOT_OK;
		else
			return null;
	}
}
