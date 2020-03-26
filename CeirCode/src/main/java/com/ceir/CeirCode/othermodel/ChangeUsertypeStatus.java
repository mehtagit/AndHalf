package com.ceir.CeirCode.othermodel;

public class ChangeUsertypeStatus {

	private long usertypeId;
	private Integer status;
	public long getUsertypeId() {
		return usertypeId;
	}
	public void setUsertypeId(long usertypeId) {
		this.usertypeId = usertypeId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeUsertypeStatus [usertypeId=").append(usertypeId).append(", status=").append(status)
				.append("]");
		return builder.toString();
	}
	
	
}
