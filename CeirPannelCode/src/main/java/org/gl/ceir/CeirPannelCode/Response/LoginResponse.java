package org.gl.ceir.CeirPannelCode.Response;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.Usertype;


public class LoginResponse {
	private String response;
	private Integer statusCode;
	private List<Usertype> userRoles;
	private String username; 
	private Integer userId;
	private String name; 
	private String primaryRole;
	private Integer primaryRoleId; 
	private String status;
	private String operatorTypeName;
    private Integer operatorTypeId;
    
    
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public Integer getPrimaryRoleId() {
		return primaryRoleId;
	}
	public void setPrimaryRoleId(Integer primaryRoleId) {
		this.primaryRoleId = primaryRoleId;
	}
	
	@Override
	public String toString() {
		return "LoginResponse [response=" + response + ", statusCode=" + statusCode + ", userRoles=" + userRoles
				+ ", username=" + username + ", userId=" + userId + ", name=" + name + ", primaryRole=" + primaryRole
				+ ", primaryRoleId=" + primaryRoleId + ", status=" + status + ", operatorTypeName=" + operatorTypeName
				+ ", operatorTypeId=" + operatorTypeId + "]";
	}
}