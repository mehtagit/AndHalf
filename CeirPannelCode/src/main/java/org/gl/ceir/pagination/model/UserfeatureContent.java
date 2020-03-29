package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserfeatureContent {

	private Integer id;
	private String createdOn;
	private String modifiedOn;
	private String usertypeInterp;
	private String featureInterp;
	private String period;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
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
	public String getUsertypeInterp() {
		return usertypeInterp;
	}
	public void setUsertypeInterp(String usertypeInterp) {
		this.usertypeInterp = usertypeInterp;
	}
	public String getFeatureInterp() {
		return featureInterp;
	}
	public void setFeatureInterp(String featureInterp) {
		this.featureInterp = featureInterp;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "UserfeatureContent [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", usertypeInterp=" + usertypeInterp + ", featureInterp=" + featureInterp + ", period=" + period
				+ ", additionalProperties=" + additionalProperties + "]";
	}
	
	
	
}
