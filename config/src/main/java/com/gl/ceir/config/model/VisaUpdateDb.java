package com.gl.ceir.config.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class VisaUpdateDb {

	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	private LocalDateTime modifiedOn;
	
	@NotNull
	private Integer visaType; 
	
	@Column(length = 50)
	private String visaNumber;
	
	@Column(length = 50)
	@NotNull
	private String visaFileName;
	
	private String entryDateInCountry;
	
	private String visaExpiryDate;
	
	@Transient
	private String visaTypeInterp;
	
	private Integer status;
	
	@Transient
	private String stateInterp;
	

	private long userId;
	
	
	public VisaUpdateDb() {
		super();
	}

	public VisaUpdateDb(@NotNull Integer visaType, String visaNumber, @NotNull String visaFileName,
			String entryDateInCountry, String visaExpiryDate, Integer status,long userId) {
		super();
		this.visaType = visaType;
		this.visaNumber = visaNumber;
		this.visaFileName = visaFileName;
		this.entryDateInCountry = entryDateInCountry;
		this.visaExpiryDate = visaExpiryDate;
		this.status = status;
		this.userId=userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getVisaFileName() {
		return visaFileName;
	}

	public void setVisaFileName(String visaFileName) {
		this.visaFileName = visaFileName;
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

	public String getVisaTypeInterp() {
		return visaTypeInterp;
	}

	public void setVisaTypeInterp(String visaTypeInterp) {
		this.visaTypeInterp = visaTypeInterp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStateInterp() {
		return stateInterp;
	}

	public void setStateInterp(String stateInterp) {
		this.stateInterp = stateInterp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VisaUpdateDb [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", visaType=");
		builder.append(visaType);
		builder.append(", visaNumber=");
		builder.append(visaNumber);
		builder.append(", visaFileName=");
		builder.append(visaFileName);
		builder.append(", entryDateInCountry=");
		builder.append(entryDateInCountry);
		builder.append(", visaExpiryDate=");
		builder.append(visaExpiryDate);
		builder.append(", visaTypeInterp=");
		builder.append(visaTypeInterp);
		builder.append(", status=");
		builder.append(status);
		builder.append(", stateInterp=");
		builder.append(stateInterp);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	
}
