package com.ceir.CeirCode.model;

public class AddressObject extends AllRequest {
private String province;
private Long districtID;
private Long communeID;
private String district;
private String commune;
private String village;
public long localityId;



public long getLocalityId() {
	return localityId;
}
public void setLocalityId(long localityId) {
	this.localityId = localityId;
}
public String  startDate;
public String   endDate;
private String searchString;
public String getSearchString() {
	return searchString;
}
public void setSearchString(String searchString) {
	this.searchString = searchString;
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
public String getVillage() {
	return village;
}
public void setVillage(String village) {
	this.village = village;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("AddressObject [province=");
	builder.append(province);
	builder.append(", districtID=");
	builder.append(districtID);
	builder.append(", communeID=");
	builder.append(communeID);
	builder.append(", district=");
	builder.append(district);
	builder.append(", commune=");
	builder.append(commune);
	builder.append(", village=");
	builder.append(village);
	builder.append(", localityId=");
	builder.append(localityId);
	builder.append(", startDate=");
	builder.append(startDate);
	builder.append(", endDate=");
	builder.append(endDate);
	builder.append(", searchString=");
	builder.append(searchString);
	builder.append("]");
	return builder.toString();
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public Long getDistrictID() {
	return districtID;
}
public void setDistrictID(Long districtID) {
	this.districtID = districtID;
}
public Long getCommuneID() {
	return communeID;
}
public void setCommuneID(Long communeID) {
	this.communeID = communeID;
}

}
