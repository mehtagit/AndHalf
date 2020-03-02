package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PolicyConfigContent {

	private Integer id;
	private String createdOn;
	private String modifiedOn;
	private String tag;
	private String value;
	private String description;
	private String period;
	private Integer status;
	private String remark;
	private Integer type;
	private Integer policyOrder;
	private String statusInterp;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPolicyOrder() {
		return policyOrder;
	}
	public void setPolicyOrder(Integer policyOrder) {
		this.policyOrder = policyOrder;
	}
	public String getStatusInterp() {
		return statusInterp;
	}
	public void setStatusInterp(String statusInterp) {
		this.statusInterp = statusInterp;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "PolicyConfigContent [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", tag="
				+ tag + ", value=" + value + ", description=" + description + ", period=" + period + ", status="
				+ status + ", remark=" + remark + ", type=" + type + ", policyOrder=" + policyOrder + ", statusInterp="
				+ statusInterp + ", additionalProperties=" + additionalProperties + "]";
	}
	
	
	
}
