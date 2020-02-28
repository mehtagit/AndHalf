package com.gl.ceir.config.model;

public class FilterRequest {

	private Long id;
	private Integer userId;
	private Long importerId;
	private String nid;
	private String txnId;
	private String startDate;
	private String endDate;
	private Integer consignmentStatus;
	private String roleType;
	private Integer requestType;
	private Integer sourceType;
	private String userType;
	private Integer featureId;
	private String featureName;
	private String subFeatureName;
	private String userName;
	private Integer userTypeId;
	private String searchString;

	private Integer taxPaidStatus;
	private Integer deviceIdType;
	private Integer deviceType;
	private Integer type;
	private Integer channel;

	private Integer status;

	private Integer operatorTypeId;
	private String origin;
	
	private String tac;
	
	// Mapping for parent child tags.
	private String tag;
	private String childTag;
	private Integer parentValue;
	
	private String imei;
	private Long contactNumber;
	
	public String getSubFeatureName() {
		return subFeatureName;
	}
	public void setSubFeatureName(String subFeatureName) {
		this.subFeatureName = subFeatureName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getTxnId() {
		return txnId;
	}
	public FilterRequest setTxnId(String txnId) {
		this.txnId = txnId;
		return this;
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
	public Integer getTaxPaidStatus() {
		return taxPaidStatus;
	}
	public void setTaxPaidStatus(Integer taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public Integer getConsignmentStatus() {
		return consignmentStatus;
	}
	public void setConsignmentStatus(Integer consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public Integer getDeviceIdType() {
		return deviceIdType;
	}
	public void setDeviceIdType(Integer deviceIdType) {
		this.deviceIdType = deviceIdType;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOperatorTypeId() {
		return operatorTypeId;
	}
	public void setOperatorTypeId(Integer operatorTypeId) {
		this.operatorTypeId = operatorTypeId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getChildTag() {
		return childTag;
	}
	public void setChildTag(String childTag) {
		this.childTag = childTag;
	}
	public Integer getParentValue() {
		return parentValue;
	}
	public void setParentValue(Integer parentValue) {
		this.parentValue = parentValue;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public Long getImporterId() {
		return importerId;
	}
	public void setImporterId(Long importerId) {
		this.importerId = importerId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilterRequest [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", importerId=");
		builder.append(importerId);
		builder.append(", nid=");
		builder.append(nid);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", consignmentStatus=");
		builder.append(consignmentStatus);
		builder.append(", roleType=");
		builder.append(roleType);
		builder.append(", requestType=");
		builder.append(requestType);
		builder.append(", sourceType=");
		builder.append(sourceType);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", taxPaidStatus=");
		builder.append(taxPaidStatus);
		builder.append(", deviceIdType=");
		builder.append(deviceIdType);
		builder.append(", deviceType=");
		builder.append(deviceType);
		builder.append(", type=");
		builder.append(type);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", status=");
		builder.append(status);
		builder.append(", operatorTypeId=");
		builder.append(operatorTypeId);
		builder.append(", origin=");
		builder.append(origin);
		builder.append(", tac=");
		builder.append(tac);
		builder.append(", tag=");
		builder.append(tag);
		builder.append(", childTag=");
		builder.append(childTag);
		builder.append(", parentValue=");
		builder.append(parentValue);
		builder.append("]");
		return builder.toString();
	}
	
}


