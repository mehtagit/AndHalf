package org.gl.ceir.CeirPannelCode.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TRCRegisteration {

	private Integer adminApproveStatus;
	private String adminRemark;
	private String adminStateInterp;
	private Integer adminUserId;
	private String adminUserType;
	private String approveDisapproveDate;
	private Integer approveStatus;
	private List<AttachedFile> attachedFiles; 
	private String country;
	private String createdOn;
	private String fileName;
	private String frequencyRange;
	private Integer id;
	private String manufacturerCountry;
	private String manufacturerId;
	private String manufacturerName;
	private long modelNumber;
	private String modifiedOn;
	private long productName;
	private String remark;
	private String requestDate;
	private String stateInterp;
	private String tac;
	private String trademark;
	private String txnId;
	private Integer userId;
	private String userType;
	private String productNameInterp;
	private String modelNumberInterp;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public Integer getAdminApproveStatus() {
		return adminApproveStatus;
	}
	public void setAdminApproveStatus(Integer adminApproveStatus) {
		this.adminApproveStatus = adminApproveStatus;
	}
	public String getAdminRemark() {
		return adminRemark;
	}
	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}
	public String getAdminStateInterp() {
		return adminStateInterp;
	}
	public void setAdminStateInterp(String adminStateInterp) {
		this.adminStateInterp = adminStateInterp;
	}
	public Integer getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}
	public String getAdminUserType() {
		return adminUserType;
	}
	public void setAdminUserType(String adminUserType) {
		this.adminUserType = adminUserType;
	}
	public String getApproveDisapproveDate() {
		return approveDisapproveDate;
	}
	public void setApproveDisapproveDate(String approveDisapproveDate) {
		this.approveDisapproveDate = approveDisapproveDate;
	}
	public Integer getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	public List<AttachedFile> getAttachedFiles() {
		return attachedFiles;
	}
	public void setAttachedFiles(List<AttachedFile> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getFrequencyRange() {
		return frequencyRange;
	}
	public void setFrequencyRange(String frequencyRange) {
		this.frequencyRange = frequencyRange;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getManufacturerCountry() {
		return manufacturerCountry;
	}
	public void setManufacturerCountry(String manufacturerCountry) {
		this.manufacturerCountry = manufacturerCountry;
	}
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public long getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(long modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public long getProductName() {
		return productName;
	}
	public void setProductName(long productName) {
		this.productName = productName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getStateInterp() {
		return stateInterp;
	}
	public void setStateInterp(String stateInterp) {
		this.stateInterp = stateInterp;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getProductNameInterp() {
		return productNameInterp;
	}
	public void setProductNameInterp(String productNameInterp) {
		this.productNameInterp = productNameInterp;
	}
	public String getModelNumberInterp() {
		return modelNumberInterp;
	}
	public void setModelNumberInterp(String modelNumberInterp) {
		this.modelNumberInterp = modelNumberInterp;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "TRCRegisteration [adminApproveStatus=" + adminApproveStatus + ", adminRemark=" + adminRemark
				+ ", adminStateInterp=" + adminStateInterp + ", adminUserId=" + adminUserId + ", adminUserType="
				+ adminUserType + ", approveDisapproveDate=" + approveDisapproveDate + ", approveStatus="
				+ approveStatus + ", attachedFiles=" + attachedFiles + ", country=" + country + ", createdOn="
				+ createdOn + ", fileName=" + fileName + ", frequencyRange=" + frequencyRange + ", id=" + id
				+ ", manufacturerCountry=" + manufacturerCountry + ", manufacturerId=" + manufacturerId
				+ ", manufacturerName=" + manufacturerName + ", modelNumber=" + modelNumber + ", modifiedOn="
				+ modifiedOn + ", productName=" + productName + ", remark=" + remark + ", requestDate=" + requestDate
				+ ", stateInterp=" + stateInterp + ", tac=" + tac + ", trademark=" + trademark + ", txnId=" + txnId
				+ ", userId=" + userId + ", userType=" + userType + ", productNameInterp=" + productNameInterp
				+ ", modelNumberInterp=" + modelNumberInterp + ", additionalProperties=" + additionalProperties + "]";
	}

	
	
}
