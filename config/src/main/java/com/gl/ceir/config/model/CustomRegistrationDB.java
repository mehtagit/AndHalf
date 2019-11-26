package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CustomRegistrationDB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	private LocalDateTime createdOn;
	@UpdateTimestamp
	private LocalDateTime modifiedOn;

	private String  nid;
	private String firstName;  
	private String middleName;
	private String lastName;
	private String propertyLocation;
	private String street;
	private String locality;
	private String province;
	private String country;
	private String email;
	private String phoneNo;


	@OneToMany(mappedBy = "customRegistrationDB", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserCustomDb> userCustomDb ;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}


	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}


	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}


	public String getNid() {
		return nid;
	}


	public void setNid(String nid) {
		this.nid = nid;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPropertyLocation() {
		return propertyLocation;
	}


	public void setPropertyLocation(String propertyLocation) {
		this.propertyLocation = propertyLocation;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getLocality() {
		return locality;
	}


	public void setLocality(String locality) {
		this.locality = locality;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public List<UserCustomDb> getUserCustomDb() {
		return userCustomDb;
	}


	public void setUserCustomDb(List<UserCustomDb> userCustomDb) {
		this.userCustomDb = userCustomDb;
	}


	@Override
	public String toString() {
		return "CustomRegistrationDB [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", nid="
				+ nid + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", propertyLocation=" + propertyLocation + ", street=" + street + ", locality=" + locality
				+ ", province=" + province + ", country=" + country + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", userCustomDb=" + userCustomDb + "]";
	} 






}
