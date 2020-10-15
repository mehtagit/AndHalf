package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class GrievanceUserTpeModelRowData {


	@SerializedName("DRT")
	@JsonProperty("DRT")
	private String drt;
	
	@SerializedName("Custom")
	@JsonProperty("Custom")
	private String custom;
	
	@SerializedName("Distributor")
	@JsonProperty("Distributor")
	private String distributor;
	
	@SerializedName("Immigration")
	@JsonProperty("Immigration")
	private String immigration;
	
	
	
	@SerializedName("Importer")
	@JsonProperty("Importer")
	private String importer;
	
	@SerializedName("Lawful Agency")
	@JsonProperty("Lawful Agency")
	private String lawfulAgency;
	
	@SerializedName("Manufacturer")
	@JsonProperty("Manufacturer")
	private String manufacturer;
	
	@SerializedName("Operator")
	@JsonProperty("Operator")
	private String operator;
	
	@SerializedName("Retailer")
	@JsonProperty("Retailer")
	private String retailer;
	
	@SerializedName("TRC")
	@JsonProperty("TRC")
	private String trc;
	
	
	@SerializedName("Date")
	@JsonProperty("Date")
	private String date;


	
	public String getDrt() {
		return drt;
	}

	public void setDrt(String drt) {
		this.drt = drt;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getImmigration() {
		return immigration;
	}

	public void setImmigration(String immigration) {
		this.immigration = immigration;
	}

	public String getImporter() {
		return importer;
	}

	public void setImporter(String importer) {
		this.importer = importer;
	}

	public String getLawfulAgency() {
		return lawfulAgency;
	}

	public void setLawfulAgency(String lawfulAgency) {
		this.lawfulAgency = lawfulAgency;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getTrc() {
		return trc;
	}

	public void setTrc(String trc) {
		this.trc = trc;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GrievanceUserTpeModelRowData [drt=");
		builder.append(drt);
		builder.append(", custom=");
		builder.append(custom);
		builder.append(", distributor=");
		builder.append(distributor);
		builder.append(", immigration=");
		builder.append(immigration);
		builder.append(", importer=");
		builder.append(importer);
		builder.append(", lawfulAgency=");
		builder.append(lawfulAgency);
		builder.append(", manufacturer=");
		builder.append(manufacturer);
		builder.append(", operator=");
		builder.append(operator);
		builder.append(", retailer=");
		builder.append(retailer);
		builder.append(", trc=");
		builder.append(trc);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

	
	

}
