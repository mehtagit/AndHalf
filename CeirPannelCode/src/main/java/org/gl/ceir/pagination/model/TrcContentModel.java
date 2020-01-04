package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class TrcContentModel {

	private Integer id;
	private String manufacturerId;
	private String manufacturerName;
	private String country;
	private String requestDate;
	private String tac;
	private Integer approveStatus;
	private Integer adminApproveStatus;
	private Integer userId;
	private Object userType;
	private Object adminUserId;
	private Object adminUserType;
	private String approveDisapproveDate;
	private String remark;
	private Object adminRemark;
	private String file;
	private String createdOn;
	private String modifiedOn;
	private String txnId;
	private String stateInterp;
	private String adminStateInterp;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	public Integer getAdminApproveStatus() {
		return adminApproveStatus;
	}
	public void setAdminApproveStatus(Integer adminApproveStatus) {
		this.adminApproveStatus = adminApproveStatus;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Object getUserType() {
		return userType;
	}
	public void setUserType(Object userType) {
		this.userType = userType;
	}
	public Object getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(Object adminUserId) {
		this.adminUserId = adminUserId;
	}
	public Object getAdminUserType() {
		return adminUserType;
	}
	public void setAdminUserType(Object adminUserType) {
		this.adminUserType = adminUserType;
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
	public Object getAdminRemark() {
		return adminRemark;
	}
	public void setAdminRemark(Object adminRemark) {
		this.adminRemark = adminRemark;
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
	public String getAdminStateInterp() {
		return adminStateInterp;
	}
	public void setAdminStateInterp(String adminStateInterp) {
		this.adminStateInterp = adminStateInterp;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "TrcContentModel [id=" + id + ", manufacturerId=" + manufacturerId + ", manufacturerName="
				+ manufacturerName + ", country=" + country + ", requestDate=" + requestDate + ", tac=" + tac
				+ ", approveStatus=" + approveStatus + ", adminApproveStatus=" + adminApproveStatus + ", userId="
				+ userId + ", userType=" + userType + ", adminUserId=" + adminUserId + ", adminUserType="
				+ adminUserType + ", approveDisapproveDate=" + approveDisapproveDate + ", remark=" + remark
				+ ", adminRemark=" + adminRemark + ", file=" + file + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + ", txnId=" + txnId + ", stateInterp=" + stateInterp + ", adminStateInterp="
				+ adminStateInterp + ", additionalProperties=" + additionalProperties + "]";
	}
	
}
