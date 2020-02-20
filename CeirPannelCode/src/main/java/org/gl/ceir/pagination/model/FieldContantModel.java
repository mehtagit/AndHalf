package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FieldContantModel {
	private String tag;
	private Integer value;
	private String interp;
	private Integer listOrder;
	private String tagId;
	private String description;
	private String displayName;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getInterp() {
		return interp;
	}
	public void setInterp(String interp) {
		this.interp = interp;
	}
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "FieldContantModel [tag=" + tag + ", value=" + value + ", interp=" + interp + ", listOrder=" + listOrder
				+ ", tagId=" + tagId + ", description=" + description + ", displayName=" + displayName
				+ ", additionalProperties=" + additionalProperties + "]";
	}
	
	

}
