package com.ceir.CeirCode.model;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "users")
public class User {  
	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	@JsonIgnore
	private String password;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedOn;

	private Integer currentStatus;  
	private Integer previousStatus; 
	private String remark;
   
	private long parentId=0;
	@Column(name = "user_language")
    private String userLanguage;
    
	
	@JsonIgnore
	@OneToMany(mappedBy = "userForNofication", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Notification> notificationData;


	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	UserProfile userProfile;



	@JsonIgnore
	@OneToOne(mappedBy = "userDetails", cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.LAZY)
	UserTemporarydetails userTemporarydetails;  


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usertype_id", nullable = false)
	private Usertype usertype;

	@JsonIgnore
	@OneToMany(mappedBy = "userTrack",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<LoginTracking> loginTracking;

	@JsonIgnore
	@OneToMany(mappedBy = "userData", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Userrole> userRole; 

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserSecurityquestion> userSecurityquestion;

	@JsonIgnore
	@OneToMany(mappedBy = "userPassword",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserPasswordHistory> userPasswordHistory;


	public long getId() {      
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn =modifiedOn;
	}
	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}


	public Usertype getUsertype() { return usertype; } public void
	setUsertype(Usertype usertype) { this.usertype = usertype; }

	public List<UserSecurityquestion> getUserSecurityquestion() {
		return userSecurityquestion;
	}
	public void setUserSecurityquestion(List<UserSecurityquestion> userSecurityquestion) {
		this.userSecurityquestion = userSecurityquestion;
	}
	public List<Userrole> getUserRole() {
		return userRole;
	}
	public void setUserRole(List<Userrole> userRole) {
		this.userRole = userRole;
	}

	public List<LoginTracking> getLoginTracking() {
		return loginTracking;
	}
	public void setLoginTracking(List<LoginTracking> loginTracking) {
		this.loginTracking = loginTracking;
	}
	public Integer getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Integer getPreviousStatus() {
		return previousStatus;
	}
	public void setPreviousStatus(Integer previousStatus) {
		this.previousStatus = previousStatus;
	}  


	public UserTemporarydetails getUserTemporarydetails() {
		return userTemporarydetails;
	}
	public void setUserTemporarydetails(UserTemporarydetails userTemporarydetails) {
		this.userTemporarydetails = userTemporarydetails;
	}


	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	//	public List<TypeApprovedDb> getTypeApprovedDb() { return typeApprovedDb; }
	//	public void setTypeApprovedDb(List<TypeApprovedDb> typeApprovedDb) {
	//		this.typeApprovedDb = typeApprovedDb; }
	//


	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	
	public String getUserLanguage() {
		return userLanguage;
	}
	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}
	public List<Notification> getNotificationData() {
		return notificationData;
	}
	public void setNotificationData(List<Notification> notificationData) {
		this.notificationData = notificationData;
	}
	public List<UserPasswordHistory> getUserPasswordHistory() {
		return userPasswordHistory;
	}
	public void setUserPasswordHistory(List<UserPasswordHistory> userPasswordHistory) {
		this.userPasswordHistory = userPasswordHistory;
	}



	public User() {
		super();
	}
	public User(String username, String password, LocalDateTime createdOn, LocalDateTime modifiedOn,
			Integer currentStatus, Integer previousStatus, Usertype usertype) {
		super();
		this.username = username;
		this.password = password;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.currentStatus = currentStatus;
		this.previousStatus = previousStatus;
		this.usertype = usertype;
	}
	
	
	
	public User(Integer currentStatus, Integer previousStatus) {
		super();
		this.currentStatus = currentStatus;
		this.previousStatus = previousStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", currentStatus=");
		builder.append(currentStatus);
		builder.append(", previousStatus=");
		builder.append(previousStatus);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", parentId=");
		builder.append(parentId);
		builder.append(", userLanguage=");
		builder.append(userLanguage);
		builder.append(", notificationData=");
		builder.append(notificationData);
		builder.append("]");
		return builder.toString();
	}
	

}
