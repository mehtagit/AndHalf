package com.gl.ceir.config.model;

import java.io.Serializable;

public class FeatureValidateReq implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int featureID;
	private int usertypeId;
	
	public FeatureValidateReq(int featureID, int usertypeId) {
		this.featureID = featureID;
		this.usertypeId = usertypeId;
	}

	public int getFeatureID() {
		return featureID;
	}

	public void setFeatureID(int featureID) {
		this.featureID = featureID;
	}

	public int getUsertypeId() {
		return usertypeId;
	}

	public void setUsertypeId(int usertypeId) {
		this.usertypeId = usertypeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FeatureValidateReq [featureID=");
		builder.append(featureID);
		builder.append(", usertypeId=");
		builder.append(usertypeId);
		builder.append("]");
		return builder.toString();
	}

}
