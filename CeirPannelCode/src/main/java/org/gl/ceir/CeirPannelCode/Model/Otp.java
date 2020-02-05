package org.gl.ceir.CeirPannelCode.Model;
public class Otp extends UserHeader{  
	private String phoneOtp;
	private String emailOtp;
	private Integer userid;
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
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Otp [phoneOtp=" + phoneOtp + ", emailOtp=" + emailOtp + ", userid=" + userid + "]";
	}
	
}