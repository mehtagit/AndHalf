package com.gl.ceir.config.model.constants;

public enum ConsignmentStatus {
	UPLOADING(0), PROCESSING(1), REJECTED_BY_SYSTEM(2), PENDING_FOR_APPROVAL_CEIR_AUTHORITY(3), 
	REJECTED_BY_CEIR_AUTHORITY(4), PENDING_FOR_APPROVAL_CUSTOM(5),APPROVED(6), REJECTED_BY_CUSTOM(7), WITHDRAWAL_BY_IMPORTER(8),
	WITHDRAWAL_BY_CEIR(9);

	private int code;

	ConsignmentStatus(int code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public static ConsignmentStatus getActionNames(int code) {
		for (ConsignmentStatus codes : ConsignmentStatus.values()) {
			if (codes.equals(code))
				return codes;
		}

		return null;
	}
}
