package com.gl.ceir.config.model;

import java.util.ArrayList;
import java.util.List;

public enum DocumentStatus {
	PENDING("PENDING"), APPROVED("APPROVED"), REJECTED("REJECTED");

	private String name;

	DocumentStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static List<String> getDocumentStatus() {
		List<String> list = new ArrayList<>();
		for (DocumentStatus documentStatus : DocumentStatus.values()) {
			list.add(documentStatus.toString());
		}
		return list;
	}
}
