package com.ceir.CeirCode.filemodel;

public class SlaFile {

	private String createdOn;

	private String modifiedOn;
    private String state;
    private String username;
    private String usertype;
    private String transactionId;
    private String feature;
    
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SlaFile [createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", state=");
		builder.append(state);
		builder.append(", username=");
		builder.append(username);
		builder.append(", usertype=");
		builder.append(usertype);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append(", feature=");
		builder.append(feature);
		builder.append("]");
		return builder.toString();
	}
    
    
    
}
