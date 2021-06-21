package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class Dropdown {
	private Integer id;
	private String createdOn;
	private String modifiedOn;
	private Integer featureId;
	private Integer userTypeId;
	private Integer state;
	private String interp;
	private String value;
	private String tagId;
	private String brand_name;
	private String modelName;
	private Integer brand_id;
	private Integer listOrder;
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
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getInterp() {
		return interp;
	}
	public void setInterp(String interp) {
		this.interp = interp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dropdown [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", state=");
		builder.append(state);
		builder.append(", interp=");
		builder.append(interp);
		builder.append(", value=");
		builder.append(value);
		builder.append(", tagId=");
		builder.append(tagId);
		builder.append(", brand_name=");
		builder.append(brand_name);
		builder.append(", modelName=");
		builder.append(modelName);
		builder.append(", brand_id=");
		builder.append(brand_id);
		builder.append(", listOrder=");
		builder.append(listOrder);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
