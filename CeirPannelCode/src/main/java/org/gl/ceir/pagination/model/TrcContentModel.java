package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class TrcContentModel {

	private Integer id,userID,status,approveStatus,adminApproveStatus;
	private String manufacturerId, manufacturerName,country,requestDate,tac;
	private String approveDisapproveDate, remark,file,createdOn,modifiedOn,txnId,stateInterp;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public Integer getAdminApproveStatus() {
		return adminApproveStatus;
	}
	public void setAdminApproveStatus(Integer adminApproveStatus) {
		this.adminApproveStatus = adminApproveStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public String getApproveDisapproveDate() {
		return approveDisapproveDate;
	}
	public void setApproveDisapproveDate(String approveDisapproveDate) {
		this.approveDisapproveDate = approveDisapproveDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getStateInterp() {
		return stateInterp;
	}
	public void setStateInterp(String stateInterp) {
		this.stateInterp = stateInterp;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "TrcContentModel [id=" + id + ", userID=" + userID + ", status=" + status + ", approveStatus="
				+ approveStatus + ", adminApproveStatus=" + adminApproveStatus + ", manufacturerId=" + manufacturerId
				+ ", manufacturerName=" + manufacturerName + ", country=" + country + ", requestDate=" + requestDate
				+ ", tac=" + tac + ", approveDisapproveDate=" + approveDisapproveDate + ", remark=" + remark + ", file="
				+ file + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", txnId=" + txnId
				+ ", stateInterp=" + stateInterp + ", additionalProperties=" + additionalProperties + "]";
	}
	
	
	
}
