package com.ceir.CeirCode.model;
public class UserStatusRequest {
	private String status;
	private Integer userId;
	private Integer statusValue;
	private String remark;
	private long featureId;
	private String username;
	
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
		public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getFeatureId() {
		return featureId;
	}
	public void setFeatureId(long featureId) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("UserStatusRequest [status=");
		builder.append(status);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", statusValue=");
		builder.append(statusValue);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
}
