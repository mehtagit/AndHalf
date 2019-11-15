package org.gl.ceir.CeirPannelCode.Model;

public class ConsignmentUpdateRequest {
	
	private int action;
	private String roleType;
	private int roleTypeUserId;
	private String txnId;
	private int userId;
	private String remark;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
		return "ConsignmentUpdateRequest [action=" + action + ", roleType=" + roleType + ", roleTypeUserId="
				+ roleTypeUserId + ", txnId=" + txnId + ", userId=" + userId + ", remark=" + remark + "]";
	}
	
	

}
