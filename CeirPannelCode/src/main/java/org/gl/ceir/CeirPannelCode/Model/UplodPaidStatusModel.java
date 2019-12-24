package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class UplodPaidStatusModel {
	private String currency,currencyInterp,deviceIdType,deviceIdTypeInterp,deviceSerialNumber,deviceStatus,deviceType,deviceTypeInterp ,
	   firstImei,secondImei,thirdImei,fourthImei,multiSimStatus,taxPaidStatus,taxPaidStatusInterp,txnId,price,nid,modifiedOn,createdOn,country;

	@Override
	public String toString() {
		return "UplodPaidStatusModel [currency=" + currency + ", currencyInterp=" + currencyInterp + ", deviceIdType="
				+ deviceIdType + ", deviceIdTypeInterp=" + deviceIdTypeInterp + ", deviceSerialNumber="
				+ deviceSerialNumber + ", deviceStatus=" + deviceStatus + ", deviceType=" + deviceType
				+ ", deviceTypeInterp=" + deviceTypeInterp + ", firstImei=" + firstImei + ", secondImei=" + secondImei
				+ ", thirdImei=" + thirdImei + ", fourthImei=" + fourthImei + ", multiSimStatus=" + multiSimStatus
				+ ", taxPaidStatus=" + taxPaidStatus + ", taxPaidStatusInterp=" + taxPaidStatusInterp + ", txnId="
				+ txnId + ", price=" + price + ", nid=" + nid + ", modifiedOn=" + modifiedOn + ", createdOn="
				+ createdOn + ", country=" + country + "]";
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyInterp() {
		return currencyInterp;
	}

	public void setCurrencyInterp(String currencyInterp) {
		this.currencyInterp = currencyInterp;
	}

	public String getDeviceIdType() {
		return deviceIdType;
	}

	public void setDeviceIdType(String deviceIdType) {
		this.deviceIdType = deviceIdType;
	}

	public String getDeviceIdTypeInterp() {
		return deviceIdTypeInterp;
	}

	public void setDeviceIdTypeInterp(String deviceIdTypeInterp) {
		this.deviceIdTypeInterp = deviceIdTypeInterp;
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceTypeInterp() {
		return deviceTypeInterp;
	}

	public void setDeviceTypeInterp(String deviceTypeInterp) {
		this.deviceTypeInterp = deviceTypeInterp;
	}

	public String getFirstImei() {
		return firstImei;
	}

	public void setFirstImei(String firstImei) {
		this.firstImei = firstImei;
	}

	public String getSecondImei() {
		return secondImei;
	}

	public void setSecondImei(String secondImei) {
		this.secondImei = secondImei;
	}

	public String getThirdImei() {
		return thirdImei;
	}

	public void setThirdImei(String thirdImei) {
		this.thirdImei = thirdImei;
	}

	public String getFourthImei() {
		return fourthImei;
	}

	public void setFourthImei(String fourthImei) {
		this.fourthImei = fourthImei;
	}

	public String getMultiSimStatus() {
		return multiSimStatus;
	}

	public void setMultiSimStatus(String multiSimStatus) {
		this.multiSimStatus = multiSimStatus;
	}

	public String getTaxPaidStatus() {
		return taxPaidStatus;
	}

	public void setTaxPaidStatus(String taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}

	public String getTaxPaidStatusInterp() {
		return taxPaidStatusInterp;
	}

	public void setTaxPaidStatusInterp(String taxPaidStatusInterp) {
		this.taxPaidStatusInterp = taxPaidStatusInterp;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
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


}

