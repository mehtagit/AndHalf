package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.gl.ceir.CeirPannelCode.Model.User;
import org.springframework.stereotype.Component;

@Component
public class RegistrationContentModel {
	
	private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String companyName;
	private String type;
	private Integer vatStatus;
	private String vatNo;
	private String propertyLocation;
	private String street;
	private String locality;
	private String province;
	private String country;
	private String passportNo;
	private String email;
	private String phoneNo;
	private Object createdOn;
	private Object modifiedOn;
	private String phoneOtp;
	private String emailOtp;
	private Integer status;
	private Object username;
	private Object questionList;
	private Object roles;
	private Object usertypeId;
	private Object password;
	private RegistrationUser user;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getVatStatus() {
		return vatStatus;
	}
	public void setVatStatus(Integer vatStatus) {
		this.vatStatus = vatStatus;
	}
	public String getVatNo() {
		return vatNo;
	}
	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
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
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
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
	public Object getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Object createdOn) {
		this.createdOn = createdOn;
	}
	public Object getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Object modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getPhoneOtp() {
		return phoneOtp;
	}
	public void setPhoneOtp(String phoneOtp) {
		this.phoneOtp = phoneOtp;
	}
	public String getEmailOtp() {
		return emailOtp;
	}
	public void setEmailOtp(String emailOtp) {
		this.emailOtp = emailOtp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getUsername() {
		return username;
	}
	public void setUsername(Object username) {
		this.username = username;
	}
	public Object getQuestionList() {
		return questionList;
	}
	public void setQuestionList(Object questionList) {
		this.questionList = questionList;
	}
	public Object getRoles() {
		return roles;
	}
	public void setRoles(Object roles) {
		this.roles = roles;
	}
	public Object getUsertypeId() {
		return usertypeId;
	}
	public void setUsertypeId(Object usertypeId) {
		this.usertypeId = usertypeId;
	}
	public Object getPassword() {
		return password;
	}
	public void setPassword(Object password) {
		this.password = password;
	}
	public RegistrationUser getUser() {
		return user;
	}
	public void setUser(RegistrationUser user) {
		this.user = user;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "RegistrationContentModel [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", companyName=" + companyName + ", type=" + type + ", vatStatus="
				+ vatStatus + ", vatNo=" + vatNo + ", propertyLocation=" + propertyLocation + ", street=" + street
				+ ", locality=" + locality + ", province=" + province + ", country=" + country + ", passportNo="
				+ passportNo + ", email=" + email + ", phoneNo=" + phoneNo + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + ", phoneOtp=" + phoneOtp + ", emailOtp=" + emailOtp + ", status="
				+ status + ", username=" + username + ", questionList=" + questionList + ", roles=" + roles
				+ ", usertypeId=" + usertypeId + ", password=" + password + ", user=" + user + ", additionalProperties="
				+ additionalProperties + "]";
	}
	
	
}
