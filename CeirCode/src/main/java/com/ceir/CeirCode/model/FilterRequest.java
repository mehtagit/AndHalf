package com.ceir.CeirCode.model;

public class FilterRequest {
	public String  startDate;
	public String   endDate; 
	private Integer  status;
	private Integer asType;
	private long userRoleTypeId;
	private Integer viewAllUserStatus;
	private Integer featureId;
	private long userId;
	private Integer userTypeId;
	private String searchString;
	private String email;
	private String phoneNo;
	private String username;
	private Long id;
  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getAsType() {
		return asType;
	}
	public void setAsType(Integer asType) {
		this.asType = asType;
	}
	public long getUserRoleTypeId() {
		return userRoleTypeId;
	}
	public void setUserRoleTypeId(long userRoleTypeId) {
		this.userRoleTypeId = userRoleTypeId;
	}
	public Integer getViewAllUserStatus() {
		return viewAllUserStatus;
	}
	public void setViewAllUserStatus(Integer viewAllUserStatus) {
		this.viewAllUserStatus = viewAllUserStatus;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	 
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer usertypeId) {
		this.userTypeId = usertypeId;
	}
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilterRequest [startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", asType=");
		builder.append(asType);
		builder.append(", userRoleTypeId=");
		builder.append(userRoleTypeId);
		builder.append(", viewAllUserStatus=");
		builder.append(viewAllUserStatus);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", username=");
		builder.append(username);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}