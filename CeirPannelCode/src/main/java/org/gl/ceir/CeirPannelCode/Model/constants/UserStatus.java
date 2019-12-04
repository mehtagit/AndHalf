package org.gl.ceir.CeirPannelCode.Model.constants;

public enum UserStatus {
	INIT(0, "Init"), OTP_VERIFICATION_PENDING(1, "OTP Verification Pending"), 
	PENDING_ADMIN_APPROVAL(2, "Pending Admin Approval"), APPROVED(3, "Approved"),
	REJECTED(4, "Rejected"), DISABLE(5, "Disable"), DEACTIVATE(6, "Deactivate");
	
	private Integer code;
	private String description;

	UserStatus(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static UserStatus getUserStatusDesc(Integer code) {
		for (UserStatus userStatus : UserStatus.values()) {
			if (userStatus.getCode() == code)
				return userStatus;
		}

		return null;
	}
}
