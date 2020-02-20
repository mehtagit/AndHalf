package com.ceir.CeirCode.model;
public class UserStatusRequest {
	private String status;
	private Integer userId;
	private String remark;
	private long featureId=0;
	
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
	@Override
	public String toString() {
		return "UserStatusRequest [status=" + status + ", userId=" + userId + ", remark=" + remark + ", featureId="
				+ featureId + "]";
	}
}
