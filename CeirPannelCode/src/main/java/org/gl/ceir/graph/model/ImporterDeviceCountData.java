package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class ImporterDeviceCountData {
	  @JsonProperty("Date")
	  @SerializedName("Date")
	  private String date;
	  
	  @JsonProperty("Importer")
	  @SerializedName("Importer")
	  private String importer;

	  @JsonProperty("Importer & Distributor")
	  @SerializedName("Importer & Distributor")
	  private String importerDistributor;

	  @JsonProperty("Importer & Retailer")
	  @SerializedName("Importer & Retailer")
	  private String importerRetailer;
	  
	  @JsonProperty("Importer & Distributor & Retailer")
	  @SerializedName("Importer & Distributor & Retailer")
	  private String importerDistributorRetailer;
	  
	  @JsonProperty("Import Grievance")
	  @SerializedName("Import Grievance")
	  private String importGrievance;

	  @JsonProperty("Total Importer Device Count")
	  @SerializedName("Total Importer Device Count")
	  private String importerDevice;
	  
	  @JsonProperty("Total Importer User Count")
	  @SerializedName("Total Importer User Count")
	  private String importerUserFound;
	  
	  @JsonProperty("Total Stock Uploaded Importer")
	  @SerializedName("Total Stock Uploaded Importer")
	  private String importerStockFound;

	  @JsonProperty("Count")
	  @SerializedName("Count")
	  private String count;

	  
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImporter() {
		return importer;
	}

	public void setImporter(String importer) {
		this.importer = importer;
	}

	public String getImporterDistributor() {
		return importerDistributor;
	}

	public void setImporterDistributor(String importerDistributor) {
		this.importerDistributor = importerDistributor;
	}

	public String getImporterRetailer() {
		return importerRetailer;
	}

	public void setImporterRetailer(String importerRetailer) {
		this.importerRetailer = importerRetailer;
	}

	public String getImporterDistributorRetailer() {
		return importerDistributorRetailer;
	}

	public void setImporterDistributorRetailer(String importerDistributorRetailer) {
		this.importerDistributorRetailer = importerDistributorRetailer;
	}

	public String getImportGrievance() {
		return importGrievance;
	}

	public void setImportGrievance(String importGrievance) {
		this.importGrievance = importGrievance;
	}

	public String getImporterDevice() {
		return importerDevice;
	}

	public void setImporterDevice(String importerDevice) {
		this.importerDevice = importerDevice;
	}

	public String getImporterUserFound() {
		return importerUserFound;
	}

	public void setImporterUserFound(String importerUserFound) {
		this.importerUserFound = importerUserFound;
	}

	public String getImporterStockFound() {
		return importerStockFound;
	}

	public void setImporterStockFound(String importerStockFound) {
		this.importerStockFound = importerStockFound;
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
		builder.append("ImporterDeviceCountData [date=");
		builder.append(date);
		builder.append(", importer=");
		builder.append(importer);
		builder.append(", importerDistributor=");
		builder.append(importerDistributor);
		builder.append(", importerRetailer=");
		builder.append(importerRetailer);
		builder.append(", importerDistributorRetailer=");
		builder.append(importerDistributorRetailer);
		builder.append(", importGrievance=");
		builder.append(importGrievance);
		builder.append(", importerDevice=");
		builder.append(importerDevice);
		builder.append(", importerUserFound=");
		builder.append(importerUserFound);
		builder.append(", importerStockFound=");
		builder.append(importerStockFound);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

	  
}
