package com.ceir.SLAModule.entity;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity   
@Table(name = "users")
public class User {  

	private static long serialVersionUID = 1L;
	
	@Id       
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	
	@JsonIgnore
	private String password; 
	
	private Date createdOn;
	
	private Date modifiedOn; 
	
	private Integer currentStatus; 
	
    private Integer previousStatus;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	UserProfile userProfile;
	
	@OneToMany(mappedBy = "userSlaReport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SlaReport> SlaReport;
	
	
	public long getId() {      
		return id;
	}
	public User setId(long id) {
		this.id = id;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setSerialVersionUID(long serialVersionUID) {
		User.serialVersionUID = serialVersionUID;
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

	public List<SlaReport> getSlaReport() {
		return SlaReport;
	}
	public void setSlaReport(List<SlaReport> slaReport) {
		SlaReport = slaReport;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", currentStatus=");
		builder.append(currentStatus);
		builder.append(", previousStatus=");
		builder.append(previousStatus);
		builder.append("]");
		return builder.toString();
	}
	
}
