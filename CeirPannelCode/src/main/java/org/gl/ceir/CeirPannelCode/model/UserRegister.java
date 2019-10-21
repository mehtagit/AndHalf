package org.gl.ceir.CeirPannelCode.model;

import java.util.Arrays;

public class UserRegister {
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
	private Integer userid;
	private String username;
	private String phoneNo;
	private String password; 
	private String usertype;
	private Integer usertypeId;
	private Integer[] roles;     
	public Integer[] getRoles() {
		return roles;
	}
	public void setRoles(Integer[] roles) {
		this.roles = roles;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Integer getUsertypeId() {
		return usertypeId;
	}
	public void setUsertypeId(Integer usertypeId) {
		this.usertypeId = usertypeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Integer getUserid() {
		return userid;
	} 
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstName;
	}
	public void setFirstname(String firstname) {
		this.firstName = firstname;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Override
	public String toString() {
		return "UserRegister [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", companyName=" + companyName + ", type=" + type + ", vatStatus=" + vatStatus + ", vatNo=" + vatNo
				+ ", propertyLocation=" + propertyLocation + ", street=" + street + ", locality=" + locality
				+ ", province=" + province + ", country=" + country + ", passportNo=" + passportNo + ", email=" + email
				+ ", userid=" + userid + ", username=" + username + ", phoneNo=" + phoneNo + ", password=" + password
				+ ", usertype=" + usertype + ", usertypeId=" + usertypeId + ", roles=" + Arrays.toString(roles) + "]";
	}
	
	
}
