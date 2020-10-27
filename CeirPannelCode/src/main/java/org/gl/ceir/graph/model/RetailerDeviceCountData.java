package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@Component
public class RetailerDeviceCountData {
	  @JsonProperty("Date")
	  @SerializedName("Date")
	  private String date;
	  
	  @JsonProperty("Retailer")
	  @SerializedName("Retailer")
	  private String retailer;
	  
	  @JsonProperty("Retailer & Importer")
	  @SerializedName("Retailer & Importer")
	  private String retailerImporter;
	  
	  @JsonProperty("Retailer & Distributor")
	  @SerializedName("Retailer & Distributor")
	  private String retailerDistributor;
	  
	  @JsonProperty("Retailer & Importer & Distributor")
	  @SerializedName("Retailer & Importer & Distributor")
	  private String retailerImporterDistributor;
	  
	  @JsonProperty("Retailer Grievance")
	  @SerializedName("Retailer Grievance")
	  private String retailerGrievance;

	  @JsonProperty("Total Retailer Device Count")
	  @SerializedName("Total Retailer Device Count")
	  private String retailerDevice;
	  
	  @JsonProperty("Total Stock Uploaded Retailer")
	  @SerializedName("Total Stock Uploaded Retailer")
	  private String retailerStock;
	  
	  @JsonProperty("Total Retailer User Count")
	  @SerializedName("Total Retailer User Count")
	  private String totalRetailer;
	
	  @JsonProperty("Count")
	  @SerializedName("Count")
	  private String count;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getRetailerImporter() {
		return retailerImporter;
	}

	public void setRetailerImporter(String retailerImporter) {
		this.retailerImporter = retailerImporter;
	}

	public String getRetailerDistributor() {
		return retailerDistributor;
	}

	public void setRetailerDistributor(String retailerDistributor) {
		this.retailerDistributor = retailerDistributor;
	}

	public String getRetailerImporterDistributor() {
		return retailerImporterDistributor;
	}

	public void setRetailerImporterDistributor(String retailerImporterDistributor) {
		this.retailerImporterDistributor = retailerImporterDistributor;
	}

	public String getRetailerGrievance() {
		return retailerGrievance;
	}

	public void setRetailerGrievance(String retailerGrievance) {
		this.retailerGrievance = retailerGrievance;
	}

	public String getRetailerDevice() {
		return retailerDevice;
	}

	public void setRetailerDevice(String retailerDevice) {
		this.retailerDevice = retailerDevice;
	}

	public String getRetailerStock() {
		return retailerStock;
	}

	public void setRetailerStock(String retailerStock) {
		this.retailerStock = retailerStock;
	}

	public String getTotalRetailer() {
		return totalRetailer;
	}

	public void setTotalRetailer(String totalRetailer) {
		this.totalRetailer = totalRetailer;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RetailerDeviceCountData [date=");
		builder.append(date);
		builder.append(", retailer=");
		builder.append(retailer);
		builder.append(", retailerImporter=");
		builder.append(retailerImporter);
		builder.append(", retailerDistributor=");
		builder.append(retailerDistributor);
		builder.append(", retailerImporterDistributor=");
		builder.append(retailerImporterDistributor);
		builder.append(", retailerGrievance=");
		builder.append(retailerGrievance);
		builder.append(", retailerDevice=");
		builder.append(retailerDevice);
		builder.append(", retailerStock=");
		builder.append(retailerStock);
		builder.append(", totalRetailer=");
		builder.append(totalRetailer);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}


}
