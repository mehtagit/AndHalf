package com.ceir.BlackListProcess.model.constants;

public enum StockOperation {

	INSERTION(0);

	private int code;

	StockOperation(int code){
		this.code=code;
	}

	public Integer getCode() {
		return code;
	}

	public static StockOperation getActionNames(int code) {
		for (StockOperation codes : StockOperation.values()) {
			if (codes.getCode() == code)
				return codes;
		}
		return null;
	}
}
