package com.functionapps.pojo;

import java.io.Serializable;

public class UserWithProfile  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;

	public UserWithProfile() {
	}

	public UserWithProfile(Long id, String firstName) {
		this.id = id;
		this.firstName = firstName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserWithProfile [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append("]");
		return builder.toString();
	}
}
