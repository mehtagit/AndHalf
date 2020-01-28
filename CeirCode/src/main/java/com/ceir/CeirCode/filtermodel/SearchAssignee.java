package com.ceir.CeirCode.filtermodel;

public class SearchAssignee {

	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNumber;
	private String email;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "SearchAssignee [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + "]";
	}
	
	
	
}
