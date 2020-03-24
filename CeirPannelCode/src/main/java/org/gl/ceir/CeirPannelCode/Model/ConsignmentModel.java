package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.web.multipart.MultipartFile;

public class ConsignmentModel {

private int id;
private String supplierId;
private String supplierName;
private String taxPaidStatus;
private String createdOn;
private String modifiedOn;
private int importerId;
private String consignmentNumber;
private String expectedArrivalDate;
private String expectedArrivaldate;
private String expectedDispatcheDate;
private Integer expectedArrivalPort;
private String organisationcountry,expectedArrivalPortInterp,pendingTacApprovedByCustom;
private String organisationCountry;
private MultipartFile file;
private String txnId;
private String importerName;
private String totalPrice;
private String fileName;
private String fileStatus;
private String consignmentStatus;
private String quantity;
private Long userId ;
private String remarks;
private String roleType;
private Integer currency;



public String getPendingTacApprovedByCustom() {
	return pendingTacApprovedByCustom;
}
public void setPendingTacApprovedByCustom(String pendingTacApprovedByCustom) {
	this.pendingTacApprovedByCustom = pendingTacApprovedByCustom;
}
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public String getSupplierId() {
return supplierId;
}
public void setSupplierId(String supplierId) {
this.supplierId = supplierId;
}
public String getSupplierName() {
return supplierName;
}
public void setSupplierName(String supplierName) {
this.supplierName = supplierName;
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
public int getImporterId() {
return importerId;
}
public String getTaxPaidStatus() {
	return taxPaidStatus;
}
public void setTaxPaidStatus(String taxPaidStatus) {
	this.taxPaidStatus = taxPaidStatus;
}
public void setImporterId(int importerId) {
this.importerId = importerId;
}
public String getConsignmentNumber() {
return consignmentNumber;
}
public void setConsignmentNumber(String consignmentNumber) {
this.consignmentNumber = consignmentNumber;
}
public String getExpectedArrivalDate() {
return expectedArrivalDate;
}
public void setExpectedArrivalDate(String expectedArrivalDate) {
this.expectedArrivalDate = expectedArrivalDate;
}
public String getExpectedArrivaldate() {
return expectedArrivaldate;
}
public void setExpectedArrivaldate(String expectedArrivaldate) {
this.expectedArrivaldate = expectedArrivaldate;
}
public String getExpectedDispatcheDate() {
return expectedDispatcheDate;
}
public void setExpectedDispatcheDate(String expectedDispatcheDate) {
this.expectedDispatcheDate = expectedDispatcheDate;
}

	
	
	
	
public Integer getExpectedArrivalPort() {
	return expectedArrivalPort;
}
public void setExpectedArrivalPort(Integer expectedArrivalPort) {
	this.expectedArrivalPort = expectedArrivalPort;
}
public String getExpectedArrivalPortInterp() {
	return expectedArrivalPortInterp;
}
public void setExpectedArrivalPortInterp(String expectedArrivalPortInterp) {
	this.expectedArrivalPortInterp = expectedArrivalPortInterp;
}
public String getOrganisationcountry() {
return organisationcountry;
}
public void setOrganisationcountry(String organisationcountry) {
this.organisationcountry = organisationcountry;
}
public String getOrganisationCountry() {
return organisationCountry;
}
public void setOrganisationCountry(String organisationCountry) {
this.organisationCountry = organisationCountry;
}
public MultipartFile getFile() {
return file;
}
public void setFile(MultipartFile file) {
this.file = file;
}
public String getTxnId() {
return txnId;
}
public void setTxnId(String txnId) {
this.txnId = txnId;
}
public String getImporterName() {
return importerName;
}
public void setImporterName(String importerName) {
this.importerName = importerName;
}
public String getTotalPrice() {
return totalPrice;
}
public void setTotalPrice(String totalPrice) {
this.totalPrice = totalPrice;
}
public String getFileName() {
return fileName;
}
public void setFileName(String fileName) {
this.fileName = fileName;
}
public String getFileStatus() {
return fileStatus;
}
public void setFileStatus(String fileStatus) {
this.fileStatus = fileStatus;
}
public String getConsignmentStatus() {
return consignmentStatus;
}
public void setConsignmentStatus(String consignmentStatus) {
this.consignmentStatus = consignmentStatus;
}
public String getQuantity() {
return quantity;
}
public void setQuantity(String quantity) {
this.quantity = quantity;
}
public Long getUserId() {
return userId;
}
public void setUserId(Long userId) {
this.userId = userId;
}
public String getRemarks() {
return remarks;
}
public void setRemarks(String remarks) {
this.remarks = remarks;
}
public String getRoleType() {
return roleType;
}
public void setRoleType(String roleType) {
this.roleType = roleType;
}





public Integer getCurrency() {
	return currency;
}
public void setCurrency(Integer currency) {
	this.currency = currency;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("ConsignmentModel [id=");
	builder.append(id);
	builder.append(", supplierId=");
	builder.append(supplierId);
	builder.append(", supplierName=");
	builder.append(supplierName);
	builder.append(", taxPaidStatus=");
	builder.append(taxPaidStatus);
	builder.append(", createdOn=");
	builder.append(createdOn);
	builder.append(", modifiedOn=");
	builder.append(modifiedOn);
	builder.append(", importerId=");
	builder.append(importerId);
	builder.append(", consignmentNumber=");
	builder.append(consignmentNumber);
	builder.append(", expectedArrivalDate=");
	builder.append(expectedArrivalDate);
	builder.append(", expectedArrivaldate=");
	builder.append(expectedArrivaldate);
	builder.append(", expectedDispatcheDate=");
	builder.append(expectedDispatcheDate);
	builder.append(", expectedArrivalPort=");
	builder.append(expectedArrivalPort);
	builder.append(", organisationcountry=");
	builder.append(organisationcountry);
	builder.append(", expectedArrivalPortInterp=");
	builder.append(expectedArrivalPortInterp);
	builder.append(", pendingTacApprovedByCustom=");
	builder.append(pendingTacApprovedByCustom);
	builder.append(", organisationCountry=");
	builder.append(organisationCountry);
	builder.append(", file=");
	builder.append(file);
	builder.append(", txnId=");
	builder.append(txnId);
	builder.append(", importerName=");
	builder.append(importerName);
	builder.append(", totalPrice=");
	builder.append(totalPrice);
	builder.append(", fileName=");
	builder.append(fileName);
	builder.append(", fileStatus=");
	builder.append(fileStatus);
	builder.append(", consignmentStatus=");
	builder.append(consignmentStatus);
	builder.append(", quantity=");
	builder.append(quantity);
	builder.append(", userId=");
	builder.append(userId);
	builder.append(", remarks=");
	builder.append(remarks);
	builder.append(", roleType=");
	builder.append(roleType);
	builder.append(", currency=");
	builder.append(currency);
	builder.append("]");
	return builder.toString();
}

}