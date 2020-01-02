package com.gl.CEIR.FileProcess.model;

import java.io.Serializable;

public class ErrorRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String imeiEsnMeid;
	private String errorCode;
	private String description;
	
	public ErrorRecord() {
		// TODO Auto-generated constructor stub
	}
	
	public ErrorRecord(String imeiEsnMeid, String errorCode, String description) {
		this.imeiEsnMeid = imeiEsnMeid;
		this.errorCode = errorCode;
		this.description = description;
	}
	
	public String getImeiEsnMeid() {
		return imeiEsnMeid;
	}
	public void setImeiEsnMeid(String imeiEsnMeid) {
		this.imeiEsnMeid = imeiEsnMeid;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorRecord [imeiEsnMeid=");
		builder.append(imeiEsnMeid);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
}
