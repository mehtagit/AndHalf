package org.gl.ceir.CeirPannelCode.Model;

public class UserStatus {

	private String userStatus;
	private Integer userId;
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserStatus [userStatus=" + userStatus + ", userId=" + userId + "]";
	}              
	   
	  
}
