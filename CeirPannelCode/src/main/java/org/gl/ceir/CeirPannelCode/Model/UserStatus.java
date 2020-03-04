package org.gl.ceir.CeirPannelCode.Model;

public class UserStatus {
	private String status;
	private Integer userId;
	private String remark;
	private Integer featureId;
	private Integer statusValue;
	private String username;
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
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public Integer getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(Integer statusValue) {
		this.statusValue = statusValue;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserStatus [status=" + status + ", userId=" + userId + ", remark=" + remark + ", featureId=" + featureId
				+ ", statusValue=" + statusValue + ", username=" + username + "]";
	}
	
	
}
