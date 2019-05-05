package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SmsAccount {
	@Id
	@GeneratedValue
	private int iD;
	private String ip;
	private int port;
	private String userName;
	private String passWord;
	private String url;
	private int tps;
	private boolean status;
	
	public SmsAccount(){}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTps() {
		return tps;
	}

	public void setTps(int tps) {
		this.tps = tps;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SmsAccount [iD=" + iD + ", ip=" + ip + ", port=" + port + ", userName=" + userName + ", passWord="
				+ passWord + ", url=" + url + ", tps=" + tps + ", status=" + status + ", getiD()=" + getiD()
				+ ", getIp()=" + getIp() + ", getPort()=" + getPort() + ", getUserName()=" + getUserName()
				+ ", getPassWord()=" + getPassWord() + ", getUrl()=" + getUrl() + ", getTps()=" + getTps()
				+ ", isStatus()=" + isStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
