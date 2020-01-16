package com.ceir.CeirCode.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity 
public class LoginTracking {
	private static long serialVersionUID = 1L;
	@Id       
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date createdOn;
	private Integer loginStatus;
	  
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id",nullable = false)
	private User userTrack;        
	
	public Integer getId() { 
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Integer getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}
	public User getUserTrack() {
		return userTrack;
	}
	public void setUserTrack(User userTrack) {
		this.userTrack = userTrack;
	}
	@Override
	public String toString() {
		return "LoginTracking [id=" + id + ", createdOn=" + createdOn + ", loginStatus=" + loginStatus + "]";
	}
	
	
	
}
