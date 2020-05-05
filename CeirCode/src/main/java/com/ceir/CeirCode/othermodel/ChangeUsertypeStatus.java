package com.ceir.CeirCode.othermodel;

import com.ceir.CeirCode.model.AllRequest;

public class ChangeUsertypeStatus extends AllRequest{

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
		builder.append("ChangeUsertypeStatus [usertypeId=");
		builder.append(usertypeId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", getUsername()=");
		builder.append(getUsername());
		builder.append(", getUserTypeId()=");
		builder.append(getUserTypeId());
		builder.append(", getUserId()=");
		builder.append(getUserId());
		builder.append(", getUserType()=");
		builder.append(getUserType());
		builder.append(", getUserAgent()=");
		builder.append(getUserAgent());
		builder.append(", getPublicIp()=");
		builder.append(getPublicIp());
		builder.append(", getFeatureId()=");
		builder.append(getFeatureId());
		builder.append(", getDataId()=");
		builder.append(getDataId());
		builder.append("]");
		return builder.toString();
	}
	
	
}
