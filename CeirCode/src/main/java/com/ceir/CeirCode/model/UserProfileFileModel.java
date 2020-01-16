package com.ceir.CeirCode.model;

import java.time.LocalDateTime;

public class UserProfileFileModel {
    
	/*
	 * private String firstName; private String middleName; private String lastName;
	 * private String companyName;
	 * 
	 * private Integer vatStatus; private String vatNo; private String
	 * propertyLocation; private String street; private String locality; private
	 * String province; private String country; private String passportNo; private
	 * String email; private String phoneNo; private String displayName; private
	 * String employeeId; private String natureOfEmployment; private String
	 * designation; private String authorityName; private String authorityEmail;
	 * private String authorityPhoneNo; private String operatorTypeName;
	 */
	
	
	
	private LocalDateTime requestedOn;
	private String userId;
	private String asType;
	private String roleType;
	private String status;
	public LocalDateTime getRequestedOn() {
		return requestedOn;
	}
	public void setRequestedOn(LocalDateTime requestedOn) {
		this.requestedOn = requestedOn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAsType() {
		return asType;
	}
	public void setAsType(String asType) {
		this.asType = asType;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserProfileFileModel [requestedOn=" + requestedOn + ", userId=" + userId + ", asType=" + asType
				+ ", roleType=" + roleType + ", status=" + status + "]";
	}
	
	
}
