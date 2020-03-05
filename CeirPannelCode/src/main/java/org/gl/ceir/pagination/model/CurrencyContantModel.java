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
	private Double riel;
	private Double dollar;
	private String currencyInterp;
	private String month;
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
	public Double getRiel() {
		return riel;
	}
	public void setRiel(Double riel) {
		this.riel = riel;
	}
	public Double getDollar() {
		return dollar;
	}
	public void setDollar(Double dollar) {
		this.dollar = dollar;
	}
	public String getCurrencyInterp() {
		return currencyInterp;
	}
	public void setCurrencyInterp(String currencyInterp) {
		this.currencyInterp = currencyInterp;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "CurrencyContantModel [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", date="
				+ date + ", currency=" + currency + ", riel=" + riel + ", dollar=" + dollar + ", currencyInterp="
				+ currencyInterp + ", month=" + month + ", additionalProperties=" + additionalProperties + "]";
	}
	
}
