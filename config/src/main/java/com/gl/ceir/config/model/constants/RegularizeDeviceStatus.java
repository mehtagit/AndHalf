package com.gl.ceir.config.model.constants;

public enum RegularizeDeviceStatus {
	New(0, "New"), 
	Processing(1, "Processing"), 
	Rejected_By_System(2, "Rejected By System"), 
	PENDING_APPROVAL_FROM_CEIR_ADMIN(3, "Pending Approval From CEIR Admin"), 
	Withdrawn_By_User(4, "Withdrawn By User"), 
	APPROVED(6, "Approved"),
	REJECTED_BY_CEIR_ADMIN(7, "Rejected by CEIR Admin"), 
	WithDrawn_BY_CEIR_ADMIN(8, "Withdrawn By CEIR Admin");

	private int code;
	private String desc;

	RegularizeDeviceStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static RegularizeDeviceStatus getActionNames(int code) {
		for (RegularizeDeviceStatus consignmentStatus : RegularizeDeviceStatus.values()) {
			if (consignmentStatus.getCode() == code)
				return consignmentStatus;
		}

		return null;
	}
}
