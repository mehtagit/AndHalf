package org.gl.ceir.CeirPannelCode.Model;

public class UserStatus {
	private Integer id;
	private String status;
	private Integer userId;
	private String remark;
	private Integer featureId;
	private Integer statusValue;
	private String username;
	private String referenceId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserStatus [status=");
		builder.append(status);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", statusValue=");
		builder.append(statusValue);
		builder.append(", username=");
		builder.append(username);
		builder.append(", referenceId=");
		builder.append(referenceId);
		builder.append("]");
		return builder.toString();
	}
	
	
}
