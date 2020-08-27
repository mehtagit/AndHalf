package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@Component
public class CountData {
	@JsonProperty("Date")
	@SerializedName("Date")
	private String date;
	
	@JsonProperty("Total IMEI")
	@SerializedName("Total IMEI")
	private String totalIMEI;
	
	@JsonProperty("Total Unique IMEI")
	@SerializedName("Total Unique IMEI")
	private String totalUniqueIMEI;
	
	@JsonProperty("Total Paired IMEI")
	@SerializedName("Total Paired IMEI")
	private String totalPairedIMEI;
	
	@JsonProperty("Approved TAC")
	@SerializedName("Approved TAC")
	private String approvedTAC;
	
	@JsonProperty("Rejected TAC")
	@SerializedName("Rejected TAC")
	private String rejectedTAC;

	
	
	@JsonProperty("Total Grey IMEI Count")
	@SerializedName("Total Grey IMEI Count")
	private String totalGreyIMEI;
	
	@JsonProperty("Total BlackList IMEI Count")
	@SerializedName("Total BlackList IMEI Count")
	private String totalBlackListIMEI;
	
	
	@JsonProperty("Total GSMA BlackList Count")
	@SerializedName("Total GSMA BlackList Count")
	private String totalGSMABlackList;
	
	@JsonProperty("Total Importer Device Count")
	@SerializedName("Total Importer Device Count")
	private String totalImporterDevice;
	
	@JsonProperty("Total Distributor Device Count")
	@SerializedName("Total Distributor Device Count")
	private String totalDistributorDevice;
	
	@JsonProperty("Total Retailer Device Count")
	@SerializedName("Total Retailer Device Count")
	private String totalRetailerDevice;
	
	@JsonProperty("Total Manufacturer Device Count")
	@SerializedName("Total Manufacturer Device Count")
	private String totalManufacturerDevice;
	
	@JsonProperty("Total End User Device Count")
	@SerializedName("Total End User Device Count")
	private String totalEndUserDevice;

	@JsonProperty("Total Stolen Device Count")
  	@SerializedName("Total Stolen Device Count")
  	private String totalStolenDeviceCount;
  	
  	@JsonProperty("Total Blocked Device Count")
  	@SerializedName("Total Blocked Device Count")
  	private String totalBlockedDeviceCount;
  	
  	@JsonProperty("Total Tax Paid Device Count")
  	@SerializedName("Total Tax Paid Device Count")
  	private String totalTaxPaidDeviceCount;
     
  	@JsonProperty("Total Model Count")
  	@SerializedName("Total Model Count")
  	private String totalModelCount;
  	
  	@JsonProperty("Total Brand Count")
  	@SerializedName("Total Brand Count")
  	private String totalBrandCount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotalIMEI() {
		return totalIMEI;
	}

	public void setTotalIMEI(String totalIMEI) {
		this.totalIMEI = totalIMEI;
	}

	public String getTotalUniqueIMEI() {
		return totalUniqueIMEI;
	}

	public void setTotalUniqueIMEI(String totalUniqueIMEI) {
		this.totalUniqueIMEI = totalUniqueIMEI;
	}

	public String getTotalPairedIMEI() {
		return totalPairedIMEI;
	}

	public void setTotalPairedIMEI(String totalPairedIMEI) {
		this.totalPairedIMEI = totalPairedIMEI;
	}

	public String getApprovedTAC() {
		return approvedTAC;
	}

	public void setApprovedTAC(String approvedTAC) {
		this.approvedTAC = approvedTAC;
	}

	public String getRejectedTAC() {
		return rejectedTAC;
	}

	public void setRejectedTAC(String rejectedTAC) {
		this.rejectedTAC = rejectedTAC;
	}

	public String getTotalGreyIMEI() {
		return totalGreyIMEI;
	}

	public void setTotalGreyIMEI(String totalGreyIMEI) {
		this.totalGreyIMEI = totalGreyIMEI;
	}

	public String getTotalBlackListIMEI() {
		return totalBlackListIMEI;
	}

	public void setTotalBlackListIMEI(String totalBlackListIMEI) {
		this.totalBlackListIMEI = totalBlackListIMEI;
	}

	public String getTotalGSMABlackList() {
		return totalGSMABlackList;
	}

	public void setTotalGSMABlackList(String totalGSMABlackList) {
		this.totalGSMABlackList = totalGSMABlackList;
	}

	public String getTotalImporterDevice() {
		return totalImporterDevice;
	}

	public void setTotalImporterDevice(String totalImporterDevice) {
		this.totalImporterDevice = totalImporterDevice;
	}

	public String getTotalDistributorDevice() {
		return totalDistributorDevice;
	}

	public void setTotalDistributorDevice(String totalDistributorDevice) {
		this.totalDistributorDevice = totalDistributorDevice;
	}

	public String getTotalRetailerDevice() {
		return totalRetailerDevice;
	}

	public void setTotalRetailerDevice(String totalRetailerDevice) {
		this.totalRetailerDevice = totalRetailerDevice;
	}

	public String getTotalManufacturerDevice() {
		return totalManufacturerDevice;
	}

	public void setTotalManufacturerDevice(String totalManufacturerDevice) {
		this.totalManufacturerDevice = totalManufacturerDevice;
	}

	public String getTotalEndUserDevice() {
		return totalEndUserDevice;
	}

	public void setTotalEndUserDevice(String totalEndUserDevice) {
		this.totalEndUserDevice = totalEndUserDevice;
	}

	public String getTotalStolenDeviceCount() {
		return totalStolenDeviceCount;
	}

	public void setTotalStolenDeviceCount(String totalStolenDeviceCount) {
		this.totalStolenDeviceCount = totalStolenDeviceCount;
	}

	public String getTotalBlockedDeviceCount() {
		return totalBlockedDeviceCount;
	}

	public void setTotalBlockedDeviceCount(String totalBlockedDeviceCount) {
		this.totalBlockedDeviceCount = totalBlockedDeviceCount;
	}

	public String getTotalTaxPaidDeviceCount() {
		return totalTaxPaidDeviceCount;
	}

	public void setTotalTaxPaidDeviceCount(String totalTaxPaidDeviceCount) {
		this.totalTaxPaidDeviceCount = totalTaxPaidDeviceCount;
	}

	public String getTotalModelCount() {
		return totalModelCount;
	}

	public void setTotalModelCount(String totalModelCount) {
		this.totalModelCount = totalModelCount;
	}

	public String getTotalBrandCount() {
		return totalBrandCount;
	}

	public void setTotalBrandCount(String totalBrandCount) {
		this.totalBrandCount = totalBrandCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CountData [date=");
		builder.append(date);
		builder.append(", totalIMEI=");
		builder.append(totalIMEI);
		builder.append(", totalUniqueIMEI=");
		builder.append(totalUniqueIMEI);
		builder.append(", totalPairedIMEI=");
		builder.append(totalPairedIMEI);
		builder.append(", approvedTAC=");
		builder.append(approvedTAC);
		builder.append(", rejectedTAC=");
		builder.append(rejectedTAC);
		builder.append(", totalGreyIMEI=");
		builder.append(totalGreyIMEI);
		builder.append(", totalBlackListIMEI=");
		builder.append(totalBlackListIMEI);
		builder.append(", totalGSMABlackList=");
		builder.append(totalGSMABlackList);
		builder.append(", totalImporterDevice=");
		builder.append(totalImporterDevice);
		builder.append(", totalDistributorDevice=");
		builder.append(totalDistributorDevice);
		builder.append(", totalRetailerDevice=");
		builder.append(totalRetailerDevice);
		builder.append(", totalManufacturerDevice=");
		builder.append(totalManufacturerDevice);
		builder.append(", totalEndUserDevice=");
		builder.append(totalEndUserDevice);
		builder.append(", totalStolenDeviceCount=");
		builder.append(totalStolenDeviceCount);
		builder.append(", totalBlockedDeviceCount=");
		builder.append(totalBlockedDeviceCount);
		builder.append(", totalTaxPaidDeviceCount=");
		builder.append(totalTaxPaidDeviceCount);
		builder.append(", totalModelCount=");
		builder.append(totalModelCount);
		builder.append(", totalBrandCount=");
		builder.append(totalBrandCount);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
