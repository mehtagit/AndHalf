package com.gl.CEIR.FileProcess.model.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity          
public class ErrorCodes {  

	private static long serialVersionUID = 1L;

	@Id       
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 

	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date modifiedOn; 

	@NotNull
	@NotBlank
	@Column(unique = true)
	private String errorCode;

	@NotNull
	@NotBlank
	private String description;

	private Integer active;
	
	public ErrorCodes() {
		// TODO Auto-generated constructor stub
	}

	public ErrorCodes(String errorCode) {
		this.errorCode = errorCode;
	}
	public Long getId() {      
		return id;
	}
	public ErrorCodes setId(Long id) {
		this.id = id;
		return this;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setSerialVersionUID(long serialVersionUID) {
		ErrorCodes.serialVersionUID = serialVersionUID;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public ErrorCodes setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorCodes [id=");
		builder.append(id);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", description=");
		builder.append(description);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

}
