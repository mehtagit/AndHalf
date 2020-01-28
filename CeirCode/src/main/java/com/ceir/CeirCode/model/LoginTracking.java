package com.ceir.CeirCode.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
@Entity 
public class LoginTracking {
	private static long serialVersionUID = 1L;
	@Id       
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@CreationTimestamp
	private Date createdOn;
	private Integer loginStatus;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id",nullable = false)
	private User userTrack;        
	
	public long getId() { 
		return id;
	}
	public void setId(long id) {
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
	
	public LoginTracking() {
		super();
	}
	public LoginTracking(Integer loginStatus, User userTrack,Date createdOn) {
		super();
		this.loginStatus = loginStatus;
		this.userTrack = userTrack;
		this.createdOn = createdOn;
	}
	@Override
	public String toString() {
		return "LoginTracking [id=" + id + ", createdOn=" + createdOn + ", loginStatus=" + loginStatus + "]";
	}
	
	
	
}
