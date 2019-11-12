package com.gl.ceir.config.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity          
public class User {  
	
	private static long serialVersionUID = 1L;
	
	@Id       
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	
	private String password; 
	
	private Date createdOn;
	
	private Date modifiedOn; 
	
	private String status;  
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	UserProfile userProfile;

	/*
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usertype_id", nullable = false) 
	private Usertype usertype; 
 
	@OneToMany(mappedBy = "userTrack",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<LoginTracking> loginTracking;
	
	@OneToMany(mappedBy = "userData", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Userrole> userRole; 
	 
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserSecurityquestion> userSecurityquestion;
	
	*/
	public Integer getId() {      
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn =modifiedOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	/*
	public Usertype getUsertype() {
		return usertype;
	}
	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	} */
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/*
	public List<UserSecurityquestion> getUserSecurityquestion() {
		return userSecurityquestion;
	}
	public void setUserSecurityquestion(List<UserSecurityquestion> userSecurityquestion) {
		this.userSecurityquestion = userSecurityquestion;
	}
	public List<Userrole> getUserRole() {
		return userRole;
	}
	public void setUserRole(List<Userrole> userRole) {
		this.userRole = userRole;
	}
	
	public List<LoginTracking> getLoginTracking() {
		return loginTracking;
	}
	public void setLoginTracking(List<LoginTracking> loginTracking) {
		this.loginTracking = loginTracking;
	}
	*/
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + ", status=" + status + "]";
	}
	
}
