
package com.ceir.CeirCode.model;

public class ChangeUserStatus {

	private Integer status;

	private long userId;
	
	private long id;
	private String username;
	private String remark;
	private String referenceId;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeUserStatus [status=");
		builder.append(status);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", referenceId=");
		builder.append(referenceId);
		builder.append("]");
		return builder.toString();
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
}
