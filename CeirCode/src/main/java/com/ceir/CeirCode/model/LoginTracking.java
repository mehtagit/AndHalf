package com.ceir.CeirCode.model;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity 
@Audited
public class LoginTracking {
	private static long serialVersionUID = 1L;
	@Id       
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable =false)
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;
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
	public LoginTracking(Integer loginStatus, User userTrack) {
		super();
		this.loginStatus = loginStatus;
		this.userTrack = userTrack;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LoginTracking(Integer loginStatus, User userTrack,LocalDateTime createdOn) {
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
