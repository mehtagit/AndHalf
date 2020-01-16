package com.ceir.CeirCode.model;
import java.util.List;
public class LoginResponse {
	private String response;
	private Integer statusCode;
	private List<Usertype> userRoles;
	private String username; 
	private long userId;
	private String name;
	private String primaryRole;
	private long primaryRoleId; 
	private String status;
	private String operatorTypeName;
    private Integer operatorTypeId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status; 
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public List<Usertype> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<Usertype> userRoles) {
		this.userRoles = userRoles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrimaryRole() {
		return primaryRole;
	}
	public void setPrimaryRole(String primaryRole) {
		this.primaryRole = primaryRole;
	}
	public long getPrimaryRoleId() {
		return primaryRoleId;
	}
	public void setPrimaryRoleId(long primaryRoleId) {
		this.primaryRoleId = primaryRoleId;
	}
	public String getOperatorTypeName() {
		return operatorTypeName;
	}
	public void setOperatorTypeName(String operatorTypeName) {
		this.operatorTypeName = operatorTypeName;
	}
	public Integer getOperatorTypeId() {
		return operatorTypeId;
	}
	public void setOperatorTypeId(Integer operatorTypeId) {
		this.operatorTypeId = operatorTypeId;
	}
	@Override
	public String toString() {
		return "LoginResponse [response=" + response + ", statusCode=" + statusCode + ", username=" + username
				+ ", userId=" + userId + ", name=" + name + ", primaryRole=" + primaryRole + ", primaryRoleId="
				+ primaryRoleId + ", status=" + status + "]";
	}


}