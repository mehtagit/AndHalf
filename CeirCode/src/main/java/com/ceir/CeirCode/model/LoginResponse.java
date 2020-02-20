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
	private String userLanguage;
	private String period;
	
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
	
	public String getUserLanguage() {
		return userLanguage;
	}
	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public LoginResponse() {
		super();
	}
	public LoginResponse(String response, Integer statusCode, List<Usertype> userRoles, String username, long userId,
			String name, String primaryRole, long primaryRoleId, String status, String operatorTypeName,
			Integer operatorTypeId, String userLanguage,String period) {
		super();
		this.response = response;
		this.statusCode = statusCode;
		this.userRoles = userRoles;
		this.username = username;
		this.userId = userId;
		this.name = name;
		this.primaryRole = primaryRole;
		this.primaryRoleId = primaryRoleId;
		this.status = status;
		this.operatorTypeName = operatorTypeName;
		this.operatorTypeId = operatorTypeId;
		this.userLanguage = userLanguage;
		this.period=period;
	}
	public LoginResponse(String response, Integer statusCode, long userId) {
		super();
		this.response = response;
		this.statusCode = statusCode;
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "LoginResponse [response=" + response + ", statusCode=" + statusCode + ", userRoles=" + userRoles
				+ ", username=" + username + ", userId=" + userId + ", name=" + name + ", primaryRole=" + primaryRole
				+ ", primaryRoleId=" + primaryRoleId + ", status=" + status + ", operatorTypeName=" + operatorTypeName
				+ ", operatorTypeId=" + operatorTypeId + ", userLanguage=" + userLanguage + ", period=" + period + "]";
	}
	
	

}