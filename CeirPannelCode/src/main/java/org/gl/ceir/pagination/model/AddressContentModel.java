package org.gl.ceir.pagination.model;

import org.springframework.stereotype.Component;

@Component
public class AddressContentModel {

	private Integer id;
	private String createdOn;
	private String modifiedOn;
	private String country;
	private String province;
	private String district;
	private String commune;
	private String village;
	private String updatingProvinceName; //New Province which is going to update
	private String currentDistrictName; //New District which is going to update
	private String currentCommuneName; //New Commune which is going to update
	private String districtID;
	private String districtName;
	private String communeID;
	private String currentVillage;
	public String getCurrentVillage() {
		return currentVillage;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public void setCurrentVillage(String currentVillage) {
		this.currentVillage = currentVillage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public String getUpdatingProvinceName() {
		return updatingProvinceName;
	}
	public void setUpdatingProvinceName(String updatingProvinceName) {
		this.updatingProvinceName = updatingProvinceName;
	}
	public String getCurrentDistrictName() {
		return currentDistrictName;
	}
	public void setCurrentDistrictName(String currentDistrictName) {
		this.currentDistrictName = currentDistrictName;
	}
	public String getCurrentCommuneName() {
		return currentCommuneName;
	}
	public void setCurrentCommuneName(String currentCommuneName) {
		this.currentCommuneName = currentCommuneName;
	}
	public String getDistrictID() {
		return districtID;
	}
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getCommuneID() {
		return communeID;
	}
	public void setCommuneID(String communeID) {
		this.communeID = communeID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressContentModel [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", country=");
		builder.append(country);
		builder.append(", province=");
		builder.append(province);
		builder.append(", district=");
		builder.append(district);
		builder.append(", commune=");
		builder.append(commune);
		builder.append(", village=");
		builder.append(village);
		builder.append(", updatingProvinceName=");
		builder.append(updatingProvinceName);
		builder.append(", currentDistrictName=");
		builder.append(currentDistrictName);
		builder.append(", currentCommuneName=");
		builder.append(currentCommuneName);
		builder.append(", districtID=");
		builder.append(districtID);
		builder.append(", districtName=");
		builder.append(districtName);
		builder.append(", communeID=");
		builder.append(communeID);
		builder.append(", currentVillage=");
		builder.append(currentVillage);
		builder.append("]");
		return builder.toString();
	}
	
	
}
