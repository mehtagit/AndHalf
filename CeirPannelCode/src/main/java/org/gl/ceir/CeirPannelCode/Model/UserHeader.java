package org.gl.ceir.CeirPannelCode.Model;

public class UserHeader {

	private String userAgent;
	private String publicIp;
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
	@Override
	public String toString() {
		return "UserHeader [userAgent=" + userAgent + ", publicIp=" + publicIp + "]";
	}
	public UserHeader(String userAgent, String publicIp) {
		super();
		this.userAgent = userAgent;
		this.publicIp = publicIp;
	}
	public UserHeader() {
		super();
	}
	
}
