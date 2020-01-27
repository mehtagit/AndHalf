package org.gl.ceir.CeirPannelCode.Model;
import java.util.ArrayList;

public class Registration {
	private long id;
	private String firstName;
	private String middleName;
	private String lastName;  
	private String passportNo; 
	private String email;
	private String phoneNo;
	private String companyName;
	private String propertyLocation;
	private String street;
	private String village;
	private String locality;
	private String district;
	private String commune;
	private String postalCode;
	private String province;
	private String country; 
	private Integer type;
	private String asTypeName;
	private Integer vatStatus; 
	private String vatNo; 
	private ArrayList<QuestionPair> questionList;
	private String password;
	private String  rePassword;
	private String username;
	private Long[] roles;
	private String captcha;
	private String usertypeName;
	private String employeeId;
	private String natureOfEmployment;
	private String designation;
	private String authorityName;
	private String authorityEmail;
	private String authorityPhoneNo;
	private String operatorTypeName;
    private Integer operatorTypeId;
	private String nidFilename;
	private String photoFilename;
	private String idCardFilename;
	private Integer arrivalPort;
	private String arrivalPortName;
    private String vatFilename;
    private String userLanguage;
    
	public Registration() {};
	public ArrayList<QuestionPair> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(ArrayList<QuestionPair> questionList) {
		this.questionList = questionList;
	}

	/*
	 * public class QuestionPair{ private Integer question; private String answer;
	 * public QuestionPair(){};
	 * 
	 * public Integer getQuestion() { return question; }
	 * 
	 * public void setQuestion(Integer question) { this.question = question; }
	 * 
	 * public String getAnswer() { return answer; }
	 * 
	 * public void setAnswer(String answer) { this.answer = answer; }
	 * 
	 * @Override public String toString() { return "QuestionPair [question=" +
	 * question + ", answer=" + answer + "]"; }
	 * 
	 * }
	 */
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAsTypeName() {
		return asTypeName;
	}
	public void setAsTypeName(String asTypeName) {
		this.asTypeName = asTypeName;
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

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public Long[] getRoles() {
		return roles;
	}
	public void setRoles(Long[] roles) {
		this.roles = roles;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getNatureOfEmployment() {
		return natureOfEmployment;
	}
	public void setNatureOfEmployment(String natureOfEmployment) {
		this.natureOfEmployment = natureOfEmployment;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getAuthorityEmail() {
		return authorityEmail;
	}
	public void setAuthorityEmail(String authorityEmail) {
		this.authorityEmail = authorityEmail;
	}
	public String getAuthorityPhoneNo() {
		return authorityPhoneNo;
	}
	public void setAuthorityPhoneNo(String authorityPhoneNo) {
		this.authorityPhoneNo = authorityPhoneNo;
	}
	

	public String getOperatorTypeName() {
		return operatorTypeName;
	}
	public void setOperatorTypeName(String operatorTypeName) {
		this.operatorTypeName = operatorTypeName;
	}
	public Integer getOperatorTypeId() {
		return operatorTypeId;
	}
	public void setOperatorTypeId(Integer operatorTypeId) {
		this.operatorTypeId = operatorTypeId;
	}
	
	public String getUsertypeName() {
		return usertypeName;
	}
	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}
	

	public String getNidFilename() {
		return nidFilename;
	}
	public void setNidFilename(String nidFilename) {
		this.nidFilename = nidFilename;
	}
	public String getPhotoFilename() {
		return photoFilename;
	}
	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}
	public String getIdCardFilename() {
		return idCardFilename;
	}
	public void setIdCardFilename(String idCardFilename) {
		this.idCardFilename = idCardFilename;
	}
	
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
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
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Integer getArrivalPort() {
		return arrivalPort;
	}
	public void setArrivalPort(Integer arrivalPort) {
		this.arrivalPort = arrivalPort;
	}
	public String getArrivalPortName() {
		return arrivalPortName;
	}
	public void setArrivalPortName(String arrivalPortName) {
		this.arrivalPortName = arrivalPortName;
	}
	public String getVatFilename() {
		return vatFilename;
	}
	public void setVatFilename(String vatFilename) {
		this.vatFilename = vatFilename;
	}
	
	public String getUserLanguage() {
		return userLanguage;
	}
	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Registration [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", passportNo=");
		builder.append(passportNo);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", propertyLocation=");
		builder.append(propertyLocation);
		builder.append(", street=");
		builder.append(street);
		builder.append(", village=");
		builder.append(village);
		builder.append(", locality=");
		builder.append(locality);
		builder.append(", district=");
		builder.append(district);
		builder.append(", commune=");
		builder.append(commune);
		builder.append(", postalCode=");
		builder.append(postalCode);
		builder.append(", province=");
		builder.append(province);
		builder.append(", country=");
		builder.append(country);
		builder.append(", type=");
		builder.append(type);
		builder.append(", asTypeName=");
		builder.append(asTypeName);
		builder.append(", vatStatus=");
		builder.append(vatStatus);
		builder.append(", vatNo=");
		builder.append(vatNo);
		builder.append(", questionList=");
		builder.append(questionList);
		builder.append(", password=");
		builder.append(password);
		builder.append(", rePassword=");
		builder.append(rePassword);
		builder.append(", username=");
		builder.append(username);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", captcha=");
		builder.append(captcha);
		builder.append(", usertypeName=");
		builder.append(usertypeName);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", natureOfEmployment=");
		builder.append(natureOfEmployment);
		builder.append(", designation=");
		builder.append(designation);
		builder.append(", authorityName=");
		builder.append(authorityName);
		builder.append(", authorityEmail=");
		builder.append(authorityEmail);
		builder.append(", authorityPhoneNo=");
		builder.append(authorityPhoneNo);
		builder.append(", operatorTypeName=");
		builder.append(operatorTypeName);
		builder.append(", operatorTypeId=");
		builder.append(operatorTypeId);
		builder.append(", nidFilename=");
		builder.append(nidFilename);
		builder.append(", photoFilename=");
		builder.append(photoFilename);
		builder.append(", idCardFilename=");
		builder.append(idCardFilename);
		builder.append(", arrivalPort=");
		builder.append(arrivalPort);
		builder.append(", arrivalPortName=");
		builder.append(arrivalPortName);
		builder.append(", vatFilename=");
		builder.append(vatFilename);
		builder.append(", userLanguage=");
		builder.append(userLanguage);
		builder.append("]");
		return builder.toString();
	}
	

}
