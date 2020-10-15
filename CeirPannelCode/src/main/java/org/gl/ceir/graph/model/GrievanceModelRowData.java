package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class GrievanceModelRowData {
	
	
	@SerializedName("Date")
	@JsonProperty("Date")
	private String date;
	
		
	@SerializedName("Closed")
	@JsonProperty("Closed")
	private String closed;
	
	@SerializedName("Pending With Admin")
	@JsonProperty("Pending With Admin")
	private String pendigWithAdmin;
	
	@SerializedName("Pending With User")
	@JsonProperty("Pending With User")
	private String pendingWithUser;
	

	@SerializedName("New")
	@JsonProperty("New")
	private String newState;


	

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}



	public String getClosed() {
		return closed;
	}


	public void setClosed(String closed) {
		this.closed = closed;
	}


	public String getPendigWithAdmin() {
		return pendigWithAdmin;
	}


	public void setPendigWithAdmin(String pendigWithAdmin) {
		this.pendigWithAdmin = pendigWithAdmin;
	}


	public String getPendingWithUser() {
		return pendingWithUser;
	}


	public void setPendingWithUser(String pendingWithUser) {
		this.pendingWithUser = pendingWithUser;
	}


	public String getNewState() {
		return newState;
	}


	public void setNewState(String newState) {
		this.newState = newState;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GrievanceModelRowData [date=");
		builder.append(date);
		builder.append(", closed=");
		builder.append(closed);
		builder.append(", pendigWithAdmin=");
		builder.append(pendigWithAdmin);
		builder.append(", pendingWithUser=");
		builder.append(pendingWithUser);
		builder.append(", newState=");
		builder.append(newState);
		builder.append("]");
		return builder.toString();
	}


	
	
	
}
