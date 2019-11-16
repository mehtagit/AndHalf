package org.gl.ceir.CeirPannelCode.Model;

public class Dropdown {
	private Integer id;
	private String createdOn;
	private String modifiedOn;
	private Integer featureId;
	private Integer userTypeId;
	private Integer state;
	private String interp;
	@Override
	public String toString() {
		return "Dropdown [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", featureId="
				+ featureId + ", userTypeId=" + userTypeId + ", state=" + state + ", interp=" + interp + "]";
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
	
}
