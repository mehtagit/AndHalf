package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

public class UPSContentModel {
	private Integer id;
	private String createdOn;
	private String modifiedOn;
	private String nid;
	private Integer deviceStatus;
	private Integer taxPaidStatus;
	private String taxPaidStatusInterp;
	private Integer deviceType;
	private String deviceTypeInterp;
	private Integer deviceIdType;
	private String deviceIdTypeInterp;
	private String multiSimStatus;
	private String country;
	private String deviceSerialNumber;
	private String txnId;
	private Integer price;
	private Integer currency;
	private String currencyInterp;
	private Integer firstImei, secondImei, thirdImei, fourthImei;
	private Object endUserDB;
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
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public Integer getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public Integer getTaxPaidStatus() {
		return taxPaidStatus;
	}
	public void setTaxPaidStatus(Integer taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}
	public String getTaxPaidStatusInterp() {
		return taxPaidStatusInterp;
	}
	public void setTaxPaidStatusInterp(String taxPaidStatusInterp) {
		this.taxPaidStatusInterp = taxPaidStatusInterp;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceTypeInterp() {
		return deviceTypeInterp;
	}
	public void setDeviceTypeInterp(String deviceTypeInterp) {
		this.deviceTypeInterp = deviceTypeInterp;
	}
	public Integer getDeviceIdType() {
		return deviceIdType;
	}
	public void setDeviceIdType(Integer deviceIdType) {
		this.deviceIdType = deviceIdType;
	}
	public String getDeviceIdTypeInterp() {
		return deviceIdTypeInterp;
	}
	public void setDeviceIdTypeInterp(String deviceIdTypeInterp) {
		this.deviceIdTypeInterp = deviceIdTypeInterp;
	}
	public String getMultiSimStatus() {
		return multiSimStatus;
	}
	public void setMultiSimStatus(String multiSimStatus) {
		this.multiSimStatus = multiSimStatus;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}
	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public String getCurrencyInterp() {
		return currencyInterp;
	}
	public void setCurrencyInterp(String currencyInterp) {
		this.currencyInterp = currencyInterp;
	}
	public Integer getFirstImei() {
		return firstImei;
	}
	public void setFirstImei(Integer firstImei) {
		this.firstImei = firstImei;
	}
	public Integer getSecondImei() {
		return secondImei;
	}
	public void setSecondImei(Integer secondImei) {
		this.secondImei = secondImei;
	}
	public Integer getThirdImei() {
		return thirdImei;
	}
	public void setThirdImei(Integer thirdImei) {
		this.thirdImei = thirdImei;
	}
	public Integer getFourthImei() {
		return fourthImei;
	}
	public void setFourthImei(Integer fourthImei) {
		this.fourthImei = fourthImei;
	}
	public Object getEndUserDB() {
		return endUserDB;
	}
	public void setEndUserDB(Object endUserDB) {
		this.endUserDB = endUserDB;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		return "UPSContent [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", nid=" + nid
				+ ", deviceStatus=" + deviceStatus + ", taxPaidStatus=" + taxPaidStatus + ", taxPaidStatusInterp="
				+ taxPaidStatusInterp + ", deviceType=" + deviceType + ", deviceTypeInterp=" + deviceTypeInterp
				+ ", deviceIdType=" + deviceIdType + ", deviceIdTypeInterp=" + deviceIdTypeInterp + ", multiSimStatus="
				+ multiSimStatus + ", country=" + country + ", deviceSerialNumber=" + deviceSerialNumber + ", txnId="
				+ txnId + ", price=" + price + ", currency=" + currency + ", currencyInterp=" + currencyInterp
				+ ", firstImei=" + firstImei + ", secondImei=" + secondImei + ", thirdImei=" + thirdImei
				+ ", fourthImei=" + fourthImei + ", endUserDB=" + endUserDB + ", additionalProperties="
				+ additionalProperties + "]";
	}
}
