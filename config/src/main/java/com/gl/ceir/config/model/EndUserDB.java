package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class EndUserDB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	private LocalDateTime modifiedOn;

	private String nid;
	
	@NotNull
	private String txnId;
	private String firstName;  
	private String middleName;
	private String lastName;
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
	private String email;
	private String phoneNo;
	
	private Integer docType;
	@Transient
	private Integer docTypeInterp;

	@OneToMany(mappedBy = "endUserDB", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<RegularizeDeviceDb> regularizeDeviceDbs ;
	
	@Column(length = 50)
	private String nationality;
	
	@Column(length = 1)
	private String onVisa="N";
	
	@OneToMany(mappedBy = "endUserDB", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VisaDb> visaDb;
	
	@Column(length = 1)
	private String isVip="N";
	
	@OneToOne(mappedBy = "endUserDB", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private UserDepartment userDepartment;
	
	@Column(length = 50)
	private String passportFileName;
	
	private long creatorUserId;
	
	private Integer status;
	
	private String remarks;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
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
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getOnVisa() {
		return onVisa;
	}
	public void setOnVisa(String onVisa) {
		this.onVisa = onVisa;
	}
	
	public List<VisaDb> getVisaDb() {
		return visaDb;
	}
	public void setVisaDb(List<VisaDb> visaDb) {
		this.visaDb = visaDb;
	}
	public String getIsVip() {
		return isVip;
	}
	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}
	public UserDepartment getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(UserDepartment userDepartment) {
		this.userDepartment = userDepartment;
	}
	
	public String getPassportFileName() {
		return passportFileName;
	}
	public void setPassportFileName(String passportFileName) {
		this.passportFileName = passportFileName;
	}
	
	public long getCreatorUserId() {
		return creatorUserId;
	}
	public void setCreatorUserId(long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EndUserDB [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", nid=");
		builder.append(nid);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", propertyLocation=");
		builder.append(propertyLocation);
		builder.append(", street=");
		builder.append(street);
		builder.append(", locality=");
		builder.append(locality);
		builder.append(", district=");
		builder.append(district);
		builder.append(", commune=");
		builder.append(commune);
		builder.append(", village=");
		builder.append(village);
		builder.append(", postalCode=");
		builder.append(postalCode);
		builder.append(", province=");
		builder.append(province);
		builder.append(", country=");
		builder.append(country);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", docType=");
		builder.append(docType);
		builder.append(", docTypeInterp=");
		builder.append(docTypeInterp);
		builder.append(", nationality=");
		builder.append(nationality);
		builder.append(", onVisa=");
		builder.append(onVisa);
		builder.append(", isVip=");
		builder.append(isVip);
		builder.append(", passportFileName=");
		builder.append(passportFileName);
		builder.append("]");
		return builder.toString();
	}

}
