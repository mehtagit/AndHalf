package com.gl.ceir.config.model.constants;

public enum StockStatus {
	SUCCESS(0), UPLOADING(1), PROCESSING(2), REJECTED_BY_SYSTEM(3), WITHDRAWAL_BY_USER(4);
	
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
