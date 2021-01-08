package com.ceir.CeirCode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class RequestHeaders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String userAgent;
	private String publicIp;
	@Column(nullable =false)
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;
	
	@Column(nullable =false)
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedOn;
	private String username;
	private String browser;
	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
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
		builder.append("RequestHeaders [id=");
		builder.append(id);
		builder.append(", userAgent=");
		builder.append(userAgent);
		builder.append(", publicIp=");
		builder.append(publicIp);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", username=");
		builder.append(username);
		builder.append(", browser=");
		builder.append(browser);
		builder.append("]");
		return builder.toString();
	}
	public RequestHeaders(String userAgent, String publicIp,String username) {
		super();
		this.userAgent = userAgent;
		this.publicIp = publicIp;
		this.username=username;
	}
	public RequestHeaders(String userAgent, String publicIp, String username, String browser) {
		super();
		this.userAgent = userAgent;
		this.publicIp = publicIp;
		this.username = username;
		this.browser = browser;
	}
	public RequestHeaders() {
		super();
	}
}