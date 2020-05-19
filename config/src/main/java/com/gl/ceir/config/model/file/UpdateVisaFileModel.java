package com.gl.ceir.config.model.file;

public class UpdateVisaFileModel {

	
	private String requestedOn;
	private String modifiedOn;
	private String visaExpiryDate;
	private String visaNumber;
	private String visaType;
	private String status;
	
	public String getRequestedOn() {
		return requestedOn;
	}
	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getVisaExpiryDate() {
		return visaExpiryDate;
	}
	public void setVisaExpiryDate(String visaExpiryDate) {
		this.visaExpiryDate = visaExpiryDate;
	}
	public String getVisaNumber() {
		return visaNumber;
	}
	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateVisaFileModel [requestedOn=");
		builder.append(requestedOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", visaExpiryDate=");
		builder.append(visaExpiryDate);
		builder.append(", visaNumber=");
		builder.append(visaNumber);
		builder.append(", visaType=");
		builder.append(visaType);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	
	
}
