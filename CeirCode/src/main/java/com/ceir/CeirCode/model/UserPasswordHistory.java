package com.ceir.CeirCode.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class UserPasswordHistory {
	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;    

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User userPassword;
	private String password;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(User userPassword) {
		this.userPassword = userPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserPasswordHistory [id=" + id + ", password=" + password + "]";
	}
}
