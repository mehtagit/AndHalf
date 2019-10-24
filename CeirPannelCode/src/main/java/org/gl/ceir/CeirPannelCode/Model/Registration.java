package org.gl.ceir.CeirPannelCode.Model;

public class Registration {
	private String firstName;
	private String middleName;
	private String lastName;
	private String passportNo;
	private String email;
	private String mobileNo;
	private String companyName;
	private String propertyLocation;
	private String street;
	private String locality;
	private String province;
	private String country;  
	private String type;
	private Integer vatStatus;
	private String vatNo;
	
	
	
	
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
	@Override
	public String toString() {
		return "Registration [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", passportNo=" + passportNo + ", email=" + email + ", mobileNo=" + mobileNo + ", companyName="
				+ companyName + ", propertyLocation=" + propertyLocation + ", street=" + street + ", locality="
				+ locality + ", province=" + province + ", country=" + country + ", type=" + type + ", vatStatus="
				+ vatStatus + ", vatNo=" + vatNo + "]";
	}
	
	
	
}
