package org.gl.ceir.CeirPannelCode.Model;

public class UserStatus {
	private String status;
	private Integer userId;
	private String remark;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "UserStatus [status=" + status + ", userId=" + userId + ", remark=" + remark + "]";
	}
	
}
