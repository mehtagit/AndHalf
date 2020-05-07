package org.gl.ceir.CeirPannelCode.Model;

import java.util.List;

public class UserStatus {
	private Integer id;
	private String status;
	private Integer userId;
	private String remark;
	private Integer featureId;
	private Integer statusValue;
	private String username;
	private String referenceId;
	private String action;
	private List<Integer> roles ;
	private String usertype;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<Integer> getRoles() {
		return roles;
	}
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserStatus [id=");
		builder.append(id);
		builder.append(", status=");
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
		builder.append(", action=");
		builder.append(action);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", usertype=");
		builder.append(usertype);
		builder.append("]");
		return builder.toString();
	}
	
}
