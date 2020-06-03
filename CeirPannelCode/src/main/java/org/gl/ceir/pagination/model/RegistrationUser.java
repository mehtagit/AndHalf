package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class RegistrationUser {
	private Integer id;
	private String username;
	private String createdOn;
	private String modifiedOn;
	private Integer currentStatus;
	private Integer previousStatus;
	private RegistrationUserType usertype;
	private RegistrationHandler hibernateLazyInitializer;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@Override
	public String toString() {
		return "RegistrationUser [id=" + id + ", username=" + username + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + ", currentStatus=" + currentStatus + ", previousStatus=" + previousStatus + ", usertype="
				+ usertype + ", hibernateLazyInitializer=" + hibernateLazyInitializer + ", additionalProperties="
				+ additionalProperties + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public Integer getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Integer getPreviousStatus() {
		return previousStatus;
	}
	public void setPreviousStatus(Integer previousStatus) {
		this.previousStatus = previousStatus;
	}
	public RegistrationUserType getUsertype() {
		return usertype;
	}
	public void setUsertype(RegistrationUserType usertype) {
		this.usertype = usertype;
	}
	public RegistrationHandler getHibernateLazyInitializer() {
		return hibernateLazyInitializer;
	}
	public void setHibernateLazyInitializer(RegistrationHandler hibernateLazyInitializer) {
		this.hibernateLazyInitializer = hibernateLazyInitializer;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	
}
