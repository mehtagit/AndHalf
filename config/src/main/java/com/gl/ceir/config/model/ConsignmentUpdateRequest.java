package com.gl.ceir.config.model;

public class ConsignmentUpdateRequest {

	private int action;
	private String roleType;
	private Integer userId;
	private Long roleTypeUserId;
	private String txnId;
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Long getRoleTypeUserId() {
		return roleTypeUserId;
	}
	public void setRoleTypeUserId(Long roleTypeUserId) {
		this.roleTypeUserId = roleTypeUserId;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	@Override
	public String toString() {
		return "ConsignmentUpdateRequest [action=" + action + ", roleType=" + roleType + ", userId=" + userId
				+ ", roleTypeUserId=" + roleTypeUserId + ", txnId=" + txnId + "]";
	}
	
	
	
	
	
	
	
}
