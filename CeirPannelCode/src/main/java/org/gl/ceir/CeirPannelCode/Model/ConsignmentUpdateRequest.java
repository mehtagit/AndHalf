package org.gl.ceir.CeirPannelCode.Model;

public class ConsignmentUpdateRequest {
	
	private int action;
	private String roleType;
	private int roleTypeUserId;
	private String txnId;
	private long userId;
	private String remarks;
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
	public int getRoleTypeUserId() {
		return roleTypeUserId;
	}
	public void setRoleTypeUserId(int roleTypeUserId) {
		this.roleTypeUserId = roleTypeUserId;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "ConsignmentUpdateRequest [action=" + action + ", roleType=" + roleType + ", roleTypeUserId="
				+ roleTypeUserId + ", txnId=" + txnId + ", userId=" + userId + ", remarks=" + remarks + "]";
	}
	
	

}
