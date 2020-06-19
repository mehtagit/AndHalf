package org.gl.ceir.CeirPannelCode.Model;

public class UserLoginReport {

	private String createdOn;
	private Integer noUserLogged;  
	private Integer uniqueUserLogged;
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public Integer getNoUserLogged() {
		return noUserLogged;
	}
	public void setNoUserLogged(Integer noUserLogged) {
		this.noUserLogged = noUserLogged;
	}
	public Integer getUniqueUserLogged() {
		return uniqueUserLogged;
	}
	public void setUniqueUserLogged(Integer uniqueUserLogged) {
		this.uniqueUserLogged = uniqueUserLogged;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", noUserLogged=");
		builder.append(noUserLogged);
		builder.append(", uniqueUserLogged=");
		builder.append(uniqueUserLogged);
		builder.append("]");
		return builder.toString();
	}  
}
