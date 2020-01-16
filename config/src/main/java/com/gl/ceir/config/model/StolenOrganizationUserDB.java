package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StolenOrganizationUserDB extends StolenandRecoveryMgmt implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	*/
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	private LocalDateTime modifiedOn;

	
	private String username;
	private String companyName;  
	private String propertyLocation;
	private String street;
	private String locality;
	
	@NotNull
	@Column(length = 50)
	private String district;
	
	@NotNull
	@Column(length = 50)
	private String commune;
	
	@NotNull
	@Column(length = 50)
	private String village;
	
	@NotNull
	private Integer postalCode;
	
	private String province;
	private String country;
	
	// Authorize person Info.
	private String personnelFirstName;  
	private String personnelMiddleName;
	private String personnelLastName;
	private String email;
	private String phoneNo;
	
	private Integer docType;
	@Transient
	private Integer docTypeInterp;

	@OneToMany(mappedBy = "endUserDB", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<RegularizeDeviceDb> regularizeDeviceDbs ;

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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPersonnelFirstName() {
		return personnelFirstName;
	}
	public void setPersonnelFirstName(String personnelFirstName) {
		this.personnelFirstName = personnelFirstName;
	}
	public String getPersonnelMiddleName() {
		return personnelMiddleName;
	}
	public void setPersonnelMiddleName(String personnelMiddleName) {
		this.personnelMiddleName = personnelMiddleName;
	}
	public String getPersonnelLastName() {
		return personnelLastName;
	}
	public void setPersonnelLastName(String personnelLastName) {
		this.personnelLastName = personnelLastName;
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
	public List<RegularizeDeviceDb> getRegularizeDeviceDbs() {
		return regularizeDeviceDbs;
	}
	public void setRegularizeDeviceDbs(List<RegularizeDeviceDb> regularizeDeviceDbs) {
		this.regularizeDeviceDbs = regularizeDeviceDbs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	public Integer getDocType() {
		return docType;
	}
	public void setDocType(Integer docType) {
		this.docType = docType;
	}
	public Integer getDocTypeInterp() {
		return docTypeInterp;
	}
	public void setDocTypeInterp(Integer docTypeInterp) {
		this.docTypeInterp = docTypeInterp;
	}

}
