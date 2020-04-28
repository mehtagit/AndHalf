package com.ceir.CeirCode.filemodel;


import org.springframework.format.annotation.DateTimeFormat;


public class ReqHeaderFile {

	private String userAgent;
	private String publicIp;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createdOn;
	private String username;
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getPublicIp() {
		return publicIp;
	}
	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqHeaderFile [userAgent=");
		builder.append(userAgent);
		builder.append(", publicIp=");
		builder.append(publicIp);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}

	
}
