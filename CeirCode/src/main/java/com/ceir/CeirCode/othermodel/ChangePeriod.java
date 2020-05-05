package com.ceir.CeirCode.othermodel;

import com.ceir.CeirCode.model.AllRequest;

public class ChangePeriod extends AllRequest{


	private Integer period;

	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePeriod [period=");
		builder.append(period);
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
