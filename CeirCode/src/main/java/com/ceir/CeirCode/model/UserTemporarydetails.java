package com.ceir.CeirCode.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class UserTemporarydetails {
	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;     
	
	@OneToOne(fetch = FetchType.LAZY ,	optional = false)
	@JoinColumn(name = "user_id",nullable = false, insertable = true)
	private User userDetails;   
	                                               
	private String emailId;   
	private String mobileNo;  
	private String emailIdOtp;
	private String mobileOtp;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailIdOtp() {
		return emailIdOtp;
	}
	public void setEmailIdOtp(String emailIdOtp) {
		this.emailIdOtp = emailIdOtp;
	}
	public String getMobileOtp() {
		return mobileOtp;
	}
	public void setMobileOtp(String mobileOtp) {
		this.mobileOtp = mobileOtp;
	}
	
	public User getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	
	
	
}