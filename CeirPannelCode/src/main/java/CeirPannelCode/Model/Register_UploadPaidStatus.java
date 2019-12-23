package CeirPannelCode.Model;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.UplodPaidStatusModel;

public class Register_UploadPaidStatus {

private String firstName;
private String middleName;
private String lastName;  
private String passportNo; 
private String email;
private String phoneNo;

private String propertyLocation;
private String street;
private String locality;
private String province;
private String country;

@Override
public String toString() {
	return "Register_UploadPaidStatus [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
			+ ", passportNo=" + passportNo + ", email=" + email + ", phoneNo=" + phoneNo + ", propertyLocation="
			+ propertyLocation + ", street=" + street + ", locality=" + locality + ", province=" + province
			+ ", country=" + country + ", regularizeDeviceDbs=" + regularizeDeviceDbs + "]";
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

public List<UplodPaidStatusModel> getRegularizeDeviceDbs() {
	return regularizeDeviceDbs;
}

public void setRegularizeDeviceDbs(List<UplodPaidStatusModel> regularizeDeviceDbs) {
	this.regularizeDeviceDbs = regularizeDeviceDbs;
}

private List<UplodPaidStatusModel> regularizeDeviceDbs;

}
