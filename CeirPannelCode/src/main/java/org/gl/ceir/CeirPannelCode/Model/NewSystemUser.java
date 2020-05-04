package org.gl.ceir.CeirPannelCode.Model;

public class NewSystemUser {
	private String email;
	private String firstName;
	private Integer id;
	private String lastName;
	private String middleName;
	private String password;
	private String phoneNo;
	private String remarks;
	private String userType;
	private Integer userTypeId;
	private String username;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NewSystemUser [email=");
		builder.append(email);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", id=");
		builder.append(id);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", remarks=");
		builder.append(remarks);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
