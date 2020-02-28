package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CurrencyContantModel {

	private Integer id;
	private String createdOn;
	private String modifiedOn;
	private String date;
	private Integer currency;
	private Integer riel;
	private Integer dollar;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Integer getRiel() {
		return riel;
	}
	public void setRiel(Integer riel) {
		this.riel = riel;
	}
	public Integer getDollar() {
		return dollar;
	}
	public void setDollar(Integer dollar) {
		this.dollar = dollar;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "CurrencyContant [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", date="
				+ date + ", currency=" + currency + ", riel=" + riel + ", dollar=" + dollar + ", additionalProperties="
				+ additionalProperties + "]";
	}
}
