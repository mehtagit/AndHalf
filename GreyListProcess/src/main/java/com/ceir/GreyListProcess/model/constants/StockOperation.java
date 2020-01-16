package com.ceir.GreyListProcess.model.constants;

public enum StockOperation {

INSERT(0,"Insert"),DELETE(1,"delete"),UPDATE(2,"Update");
	
	private int code;
	private String description;
	
	StockOperation(Integer code, String description) {
		this.code = code;
		this.description = description; 
	}       

	public Integer getCode() {
		return code;
	}
            
	public String getDescription() {
		return description;
	}
	

	public static StockOperation getUserStatusByCode(Integer code) {
		for (StockOperation approveStatus : StockOperation.values()) {
			if (approveStatus.getCode() == code)
				return approveStatus;
		}

		return null;
	}
	
	public static StockOperation getUserStatusByDesc(String desc) {
		for (StockOperation approveStatus : StockOperation.values()) {
			if (approveStatus.getDescription().equals(desc))
				return approveStatus;
		}

		return null;
	}
}
