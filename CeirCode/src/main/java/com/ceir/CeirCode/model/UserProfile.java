package com.ceir.CeirCode.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import com.ceir.CeirCode.othermodel.RolesData;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity 
@Audited
public class UserProfile {
	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;     
	private String firstName;  
	private String middleName;
	private String lastName;
	private String companyName;
	private Integer type;
	private Integer vatStatus;  
	private String vatNo;
	private String propertyLocation;
	private String street;
	private String locality;

	@Column(length = 50)
	private String district;

	@Column(length = 50)
	private String commune;

	@Column(length = 50)
	private String village;

	
	@Column(length = 50)
	private String postalCode;
	private String province;   
	private String country;
	private String passportNo;
	private String email;
	private String phoneNo;
	private Integer arrivalPort;
	private Integer PortAddress;
	@Transient
	private String PortAddressName;
	@Transient
	private String arrivalPortName;
	@Transient
	private String asTypeName;
	@Column(nullable =false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;

	@Column(nullable =false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime  modifiedOn;
	private String phoneOtp;  
	private String emailOtp;
	private String displayName;
	private String employeeId;
	private Integer natureOfEmployment;
	@Transient
	private String natureOfEmploymentInterp;
	private String designation;
	private String authorityName;
	private String authorityEmail;
	private String authorityPhoneNo;
	private String operatorTypeName;
	private Integer operatorTypeId;
	private String nidFilename;
	private String photoFilename;
	private String idCardFilename;
	private String vatFilename;
	
	@Transient
	private String nidFilePath;
	@Transient
	private String photoFilePath;
	@Transient
	private String idCardFilePath;
    @Transient
    private String  vatFilePath;

	@Transient
	private String username;
	@Transient
	private List<QuestionPair> questionList ; 
	@Transient 
	private long[] roles;

	@Transient
	private long userTypeId;
	
	@Transient
	private String usertypeName;
	@Transient
	private String password;    

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "userid", nullable = false)
    private User user;  
	
    private String source;
	@Type(type="date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiryDate;
    private String sourceUsername;
    @Transient
    private String userAgent;
    @Transient
	private String publicIp;
    
	@Transient
	private String userLanguage;
	
	@Transient
	List<RolesData> rolesList;
	
	public long[] getRoles() {
		return roles;
	}
	public void setRoles(long[] roles) {
		this.roles = roles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setSerialVersionUID(long serialVersionUID) {
		UserProfile.serialVersionUID = serialVersionUID;
	}
	
	
	public long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUsertypeName() {
		return usertypeName;
	}
	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
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
	public List<QuestionPair> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<QuestionPair> questionList) {
		this.questionList = questionList; 
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	public Integer getNatureOfEmployment() {
		return natureOfEmployment;
	}
	public void setNatureOfEmployment(Integer natureOfEmployment) {
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

	public String getAsTypeName() {
		return asTypeName;
	}
	public void setAsTypeName(String asTypeName) {
		this.asTypeName = asTypeName;
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
	public String  getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String  postalCode) {
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

	public void setNidFilePath(String nidFilePath) {
		this.nidFilePath = nidFilePath;
	}
	public String getPhotoFilePath() {
		return photoFilePath;
	}
	public void setPhotoFilePath(String photoFilePath) {
		this.photoFilePath = photoFilePath;
	}
	public String getIdCardFilePath() {
		return idCardFilePath;
	}
	public void setIdCardFilePath(String idCardFilePath) {
		this.idCardFilePath = idCardFilePath;
	}

	public String getVatFilename() {
		return vatFilename;
	}
	public void setVatFilename(String vatFilename) {
		this.vatFilename = vatFilename;
	}
	public String getVatFilePath() {
		return vatFilePath;
	}
	public void setVatFilePath(String vatFilePath) {
		this.vatFilePath = vatFilePath;
	}
	public String getNidFilePath() {
		return nidFilePath;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSourceUsername() {
		return sourceUsername;
	}
	public void setSourceUsername(String sourceUsername) {
		this.sourceUsername = sourceUsername;
	}
	
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getPublicIp() {
		return publicIp;
	}
	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	public String getUserLanguage() {
		return userLanguage;
	}
	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}
	public Integer getPortAddress() {
		return PortAddress;
	}
	public void setPortAddress(Integer portAddress) {
		PortAddress = portAddress;
	}
	public String getPortAddressName() {
		return PortAddressName;
	}
	public void setPortAddressName(String portAddressName) {
		PortAddressName = portAddressName;
	}
	
	
	
	public List<RolesData> getRolesList() {
		return rolesList;
	}
	public void setRolesList(List<RolesData> rolesList) {
		this.rolesList = rolesList;
	}
	
	public String getNatureOfEmploymentInterp() {
		return natureOfEmploymentInterp;
	}
	public void setNatureOfEmploymentInterp(String natureOfEmploymentInterp) {
		this.natureOfEmploymentInterp = natureOfEmploymentInterp;
	}
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", companyName=" + companyName + ", type=" + type + ", vatStatus=" + vatStatus + ", vatNo="
				+ vatNo + ", propertyLocation=" + propertyLocation + ", street=" + street + ", locality=" + locality
				+ ", district=" + district + ", commune=" + commune + ", village=" + village + ", postalCode="
				+ postalCode + ", province=" + province + ", country=" + country + ", passportNo=" + passportNo
				+ ", email=" + email + ", phoneNo=" + phoneNo + ", arrivalPort=" + arrivalPort + ", arrivalPortName="
				+ arrivalPortName + ", asTypeName=" + asTypeName + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + ", phoneOtp=" + phoneOtp + ", emailOtp=" + emailOtp + ", displayName=" + displayName
				+ ", employeeId=" + employeeId + ", natureOfEmployment=" + natureOfEmployment + ", designation="
				+ designation + ", authorityName=" + authorityName + ", authorityEmail=" + authorityEmail
				+ ", authorityPhoneNo=" + authorityPhoneNo + ", operatorTypeName=" + operatorTypeName
				+ ", operatorTypeId=" + operatorTypeId + ", nidFilename=" + nidFilename + ", photoFilename="
				+ photoFilename + ", idCardFilename=" + idCardFilename + ", vatFilename=" + vatFilename
				+ ", nidFilePath=" + nidFilePath + ", photoFilePath=" + photoFilePath + ", idCardFilePath="
				+ idCardFilePath + ", vatFilePath=" + vatFilePath + ", username=" + username + ", questionList="
				+ questionList + ", roles=" + roles + ", usertypeName=" + usertypeName + ", password=" + password
				+ ", source=" + source + ", expiryDate=" + expiryDate + ", sourceUsername=" + sourceUsername
				+ ", userAgent=" + userAgent + ", publicIp=" + publicIp + ", userLanguage=" + userLanguage +
				" portAddress=" + PortAddress +" , PortAddressName=" + PortAddressName +", userTypeId=" + userTypeId +""
						+ "  RolesList="+rolesList+"]";
	}
	public UserProfile(String firstName, String middleName, String lastName, String propertyLocation, String street,
			String locality, @NotNull String district, @NotNull String commune, @NotNull String village,
			String  postalCode, String province, String country, String email, String phoneNo) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.propertyLocation = propertyLocation;
		this.street = street;
		this.locality = locality;
		this.district = district;
		this.commune = commune;
		this.village = village;
		this.postalCode = postalCode;
		this.province = province;
		this.country = country;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	public UserProfile() {
		super();
	}
	public UserProfile(String firstName, String middleName, String lastName, String email, String phoneNo) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	
	public UserProfile(String firstName, String middleName, String lastName, String email, String phoneNo,String displayName) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.displayName=displayName;
	}


}
