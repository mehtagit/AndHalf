package com.gl.ceir.config.model.constants;

public enum StockStatus {
	UPLOADING(0), PROCESSING(1), ERROR(2), SUCCESS(4);
	
	private int code;

	StockStatus(int code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public static StockStatus getActionNames(int code) {
		for (StockStatus codes : StockStatus.values()) {
			if (codes.equals(code))
				return codes;
		}

		return null;
	}
}
