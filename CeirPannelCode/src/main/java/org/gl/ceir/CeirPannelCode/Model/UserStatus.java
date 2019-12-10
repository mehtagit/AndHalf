package org.gl.ceir.CeirPannelCode.Model;

public class UserStatus {
	private String status;
	private Integer userId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserStatusRequest [status=" + status + ", userId=" + userId + "]";
	}
}
