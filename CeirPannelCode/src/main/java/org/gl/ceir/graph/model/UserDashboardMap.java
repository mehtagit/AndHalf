package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@Component
public class UserDashboardMap {
	@SerializedName("Date")
	private String date;


	@SerializedName("Number of user logged")
	private String numberOfUserLogged;

	@SerializedName("Unique user logged")
	private String uniqueUserLogged;


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getNumberOfUserLogged() {
		return numberOfUserLogged;
	}


	public void setNumberOfUserLogged(String numberOfUserLogged) {
		this.numberOfUserLogged = numberOfUserLogged;
	}


	public String getUniqueUserLogged() {
		return uniqueUserLogged;
	}


	public void setUniqueUserLogged(String uniqueUserLogged) {
		this.uniqueUserLogged = uniqueUserLogged;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDashboardMap [date=");
		builder.append(date);
		builder.append(", numberOfUserLogged=");
		builder.append(numberOfUserLogged);
		builder.append(", uniqueUserLogged=");
		builder.append(uniqueUserLogged);
		builder.append("]");
		return builder.toString();
	}

}