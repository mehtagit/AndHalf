package com.ceir.CeirCode.model;
public class Otp extends UserHeader{   
	private String phoneOtp;
	private String emailOtp;
	private long userid;
	
	public String getPhoneOtp() {
		return phoneOtp;
	} 
	public void setPhoneOtp(String phoneOtp) {
		this.phoneOtp = phoneOtp;
	}
	public String getEmailOtp() {
		return emailOtp;
	}
	public void setEmailOtp(String emailOtp) {
		this.emailOtp = emailOtp;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Otp [phoneOtp=" + phoneOtp + ", emailOtp=" + emailOtp + ", userid=" + userid + "]";
	}
	
}
