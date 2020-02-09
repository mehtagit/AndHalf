package org.gl.ceir.CeirPannelCode.Model;

public class LawfulStolenRecovey {
	
	private Integer blockCategory;
	private String blockCategoryInterp;
	private String blockingTimePeriod;
	private String blockingType;
	private String createdOn;
	private String fileName;
	private Integer fileStatus;
	private Integer id;
	private Integer imei;
	private String modifiedOn;
	private Integer operatorTypeId;
	private String operatorTypeIdInterp;
	private Integer qty;
	private String remark;
	private Integer requestType;
	private String requestTypeInterp;
	private String roleType;
	private SingleImeiDetails singleImeiDetails;
	private Integer sourceType;
	private String sourceTypeInterp;
	private String stateInterp;
	private StolenIndividualUserDB stolenIndividualUserDB;
	private StolenOrganizationUserDB stolenOrganizationUserDB;
	private String txnId;
	private Integer userId;
	
	public Integer getBlockCategory() {
	return blockCategory;
	}

	public void setBlockCategory(Integer blockCategory) {
	this.blockCategory = blockCategory;
	}

	public String getBlockCategoryInterp() {
	return blockCategoryInterp;
	}

	public void setBlockCategoryInterp(String blockCategoryInterp) {
	this.blockCategoryInterp = blockCategoryInterp;
	}

	public String getBlockingTimePeriod() {
	return blockingTimePeriod;
	}

	public void setBlockingTimePeriod(String blockingTimePeriod) {
	this.blockingTimePeriod = blockingTimePeriod;
	}

	public String getBlockingType() {
	return blockingType;
	}

	public void setBlockingType(String blockingType) {
	this.blockingType = blockingType;
	}

	public String getCreatedOn() {
	return createdOn;
	}

	public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
	}

	public String getFileName() {
	return fileName;
	}

	public void setFileName(String fileName) {
	this.fileName = fileName;
	}

	public Integer getFileStatus() {
	return fileStatus;
	}

	public void setFileStatus(Integer fileStatus) {
	this.fileStatus = fileStatus;
	}

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public Integer getImei() {
	return imei;
	}

	public void setImei(Integer imei) {
	this.imei = imei;
	}

	public String getModifiedOn() {
	return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
	this.modifiedOn = modifiedOn;
	}

	public Integer getOperatorTypeId() {
	return operatorTypeId;
	}

	public void setOperatorTypeId(Integer operatorTypeId) {
	this.operatorTypeId = operatorTypeId;
	}

	public String getOperatorTypeIdInterp() {
	return operatorTypeIdInterp;
	}

	public void setOperatorTypeIdInterp(String operatorTypeIdInterp) {
	this.operatorTypeIdInterp = operatorTypeIdInterp;
	}

	public Integer getQty() {
	return qty;
	}

	public void setQty(Integer qty) {
	this.qty = qty;
	}

	public String getRemark() {
	return remark;
	}

	public void setRemark(String remark) {
	this.remark = remark;
	}

	public Integer getRequestType() {
	return requestType;
	}

	public void setRequestType(Integer requestType) {
	this.requestType = requestType;
	}

	public String getRequestTypeInterp() {
	return requestTypeInterp;
	}

	public void setRequestTypeInterp(String requestTypeInterp) {
	this.requestTypeInterp = requestTypeInterp;
	}

	public String getRoleType() {
	return roleType;
	}

	public void setRoleType(String roleType) {
	this.roleType = roleType;
	}

	public SingleImeiDetails getSingleImeiDetails() {
	return singleImeiDetails;
	}

	public void setSingleImeiDetails(SingleImeiDetails singleImeiDetails) {
	this.singleImeiDetails = singleImeiDetails;
	}

	public Integer getSourceType() {
	return sourceType;
	}

	public void setSourceType(Integer sourceType) {
	this.sourceType = sourceType;
	}

	public String getSourceTypeInterp() {
	return sourceTypeInterp;
	}

	public void setSourceTypeInterp(String sourceTypeInterp) {
	this.sourceTypeInterp = sourceTypeInterp;
	}

	public String getStateInterp() {
	return stateInterp;
	}

	public void setStateInterp(String stateInterp) {
	this.stateInterp = stateInterp;
	}

	public StolenIndividualUserDB getStolenIndividualUserDB() {
	return stolenIndividualUserDB;
	}

	public void setStolenIndividualUserDB(StolenIndividualUserDB stolenIndividualUserDB) {
	this.stolenIndividualUserDB = stolenIndividualUserDB;
	}

	public StolenOrganizationUserDB getStolenOrganizationUserDB() {
	return stolenOrganizationUserDB;
	}

	public void setStolenOrganizationUserDB(StolenOrganizationUserDB stolenOrganizationUserDB) {
	this.stolenOrganizationUserDB = stolenOrganizationUserDB;
	}

	public String getTxnId() {
	return txnId;
	}

	public void setTxnId(String txnId) {
	this.txnId = txnId;
	}

	public Integer getUserId() {
	return userId;
	}

	public void setUserId(Integer userId) {
	this.userId = userId;
	}

	@Override
	public String toString() {
		return "LawfulStolenRecovey [blockCategory=" + blockCategory + ", blockCategoryInterp=" + blockCategoryInterp
				+ ", blockingTimePeriod=" + blockingTimePeriod + ", blockingType=" + blockingType + ", createdOn="
				+ createdOn + ", fileName=" + fileName + ", fileStatus=" + fileStatus + ", id=" + id + ", imei=" + imei
				+ ", modifiedOn=" + modifiedOn + ", operatorTypeId=" + operatorTypeId + ", operatorTypeIdInterp="
				+ operatorTypeIdInterp + ", qty=" + qty + ", remark=" + remark + ", requestType=" + requestType
				+ ", requestTypeInterp=" + requestTypeInterp + ", roleType=" + roleType + ", singleImeiDetails="
				+ singleImeiDetails + ", sourceType=" + sourceType + ", sourceTypeInterp=" + sourceTypeInterp
				+ ", stateInterp=" + stateInterp + ", stolenIndividualUserDB=" + stolenIndividualUserDB
				+ ", stolenOrganizationUserDB=" + stolenOrganizationUserDB + ", txnId=" + txnId + ", userId=" + userId
				+ "]";
	}

	

}
