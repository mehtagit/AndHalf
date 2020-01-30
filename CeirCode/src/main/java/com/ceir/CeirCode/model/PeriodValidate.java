package com.ceir.CeirCode.model;

public class PeriodValidate {

	private long usertypeId;
	private long featureID;
	public long getUsertypeId() {
		return usertypeId;
	}
	public void setUsertypeId(long usertypeId) {
		this.usertypeId = usertypeId;
	}
	public long getFeatureID() {
		return featureID;
	}
	public void setFeatureID(long featureID) {
		this.featureID = featureID;
	}
	@Override
	public String toString() {
		return "PeriodValidate [usertypeId=" + usertypeId + ", featureID=" + featureID + "]";
	}
	
	
}
