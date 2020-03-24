package com.gl.ceir.config.service.businesslogic;

import com.gl.ceir.config.model.constants.ConsignmentStatus;

public class StateMachine {

	public static boolean isConsignmentStatetransitionAllowed(String userType, int currentStatus) {
		if("CEIRADMIN".equalsIgnoreCase(userType)) {
			return ConsignmentStatus.PENDING_APPROVAL_FROM_CEIR_AUTHORITY.getCode() == currentStatus;
		}else if("CUSTOM".equalsIgnoreCase(userType)) {
			return ConsignmentStatus.PENDING_APPROVAL_FROM_CUSTOMS.getCode() == currentStatus;
		}else if("CEIRSYSTEM".equalsIgnoreCase(userType))
			return ConsignmentStatus.PROCESSING.getCode() == currentStatus;

		return Boolean.FALSE;
	}

	public static boolean isConsignmentStatetransitionAllowedWithAction(String userType, int currentStatus, int action) {
		if("CEIRADMIN".equalsIgnoreCase(userType)) {
			if(ConsignmentStatus.PENDING_APPROVAL_FROM_CEIR_AUTHORITY.getCode() == currentStatus) {
				return Boolean.TRUE;
			}

			if(action == 0) {
				if(ConsignmentStatus.REJECTED_BY_CEIR_AUTHORITY.getCode() == currentStatus){
					return Boolean.TRUE;
				}else {
					return Boolean.FALSE;
				}
			}else {
				if(ConsignmentStatus.PENDING_APPROVAL_FROM_CUSTOMS.getCode() == currentStatus){
					return Boolean.TRUE;
				}else {
					return Boolean.FALSE;
				}
			}
		}

		return Boolean.FALSE;
	}

}