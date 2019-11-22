package com.gl.ceir.config.model.constants;

public enum StockStatus {
	SUCCESS(3), UPLOADING(0), PROCESSING(1), REJECTED_BY_SYSTEM(2), WITHDRAWAL_BY_USER(4),STOLEN(5),RECOVERY(5);
	
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
