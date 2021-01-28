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
	private String filteredUserType;
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
	private Integer filteredUserId;
	
	private String state;
	
	private String ruleName;
	
	private String remark;
	
	private String displayName;
	
	private String quantity;
	
	public String deviceQuantity;
	
	private String subject;
	
	private String supplierName;
	
	public String getFilteredUserType() {
		return filteredUserType;
	}
	
	public void setFilteredUserType(String filteredUserType) {
		this.filteredUserType = filteredUserType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getFilteredUserId() {
		return filteredUserId;
	}
	public void setFilteredUserId(Integer filteredUserId) {
		this.filteredUserId = filteredUserId;
	}
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

	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDeviceQuantity() {
		return deviceQuantity;
	}

	public void setDeviceQuantity(String deviceQuantity) {
		this.deviceQuantity = deviceQuantity;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Override
	public String toString() {
		return "FilterRequest [id=" + id + ", userId=" + userId + ", importerId=" + importerId + ", nid=" + nid
				+ ", txnId=" + txnId + ", startDate=" + startDate + ", endDate=" + endDate + ", consignmentStatus="
				+ consignmentStatus + ", roleType=" + roleType + ", requestType=" + requestType + ", sourceType="
				+ sourceType + ", userType=" + userType + ", filteredUserType=" + filteredUserType + ", featureId="
				+ featureId + ", featureName=" + featureName + ", subFeatureName=" + subFeatureName + ", userName="
				+ userName + ", userTypeId=" + userTypeId + ", searchString=" + searchString + ", taxPaidStatus="
				+ taxPaidStatus + ", deviceIdType=" + deviceIdType + ", deviceType=" + deviceType + ", type=" + type
				+ ", channel=" + channel + ", status=" + status + ", operatorTypeId=" + operatorTypeId + ", origin="
				+ origin + ", tac=" + tac + ", tag=" + tag + ", childTag=" + childTag + ", parentValue=" + parentValue
				+ ", imei=" + imei + ", contactNumber=" + contactNumber + ", filteredUserId=" + filteredUserId
				+ ", state=" + state + ", ruleName=" + ruleName + ", remark=" + remark + ", displayName=" + displayName
				+ ", quantity=" + quantity + ", deviceQuantity=" + deviceQuantity + ", subject=" + subject
				+ ", supplierName=" + supplierName + "]";
	}

}


