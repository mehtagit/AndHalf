package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class VisaDb implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;

	@UpdateTimestamp
	private LocalDateTime modifiedOn;

	@NotNull
	private Integer visaType; 
	
	@Column(length = 50)
	private String visaNumber;
	
	@NotNull
	private String entryDateInCountry;
	
	private String visaExpiryDate;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	EndUserDB endUserDB;

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getVisaType() {
		return visaType;
	}

	public void setVisaType(Integer visaType) {
		this.visaType = visaType;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public String getEntryDateInCountry() {
		return entryDateInCountry;
	}

	public void setEntryDateInCountry(String entryDateInCountry) {
		this.entryDateInCountry = entryDateInCountry;
	}

	public String getVisaExpiryDate() {
		return visaExpiryDate;
	}

	public void setVisaExpiryDate(String visaExpiryDate) {
		this.visaExpiryDate = visaExpiryDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VisaDb [createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", visaType=");
		builder.append(visaType);
		builder.append(", visaNumber=");
		builder.append(visaNumber);
		builder.append(", entryDateInCountry=");
		builder.append(entryDateInCountry);
		builder.append(", visaExpiryDate=");
		builder.append(visaExpiryDate);
		builder.append("]");
		return builder.toString();
	}
	
}
