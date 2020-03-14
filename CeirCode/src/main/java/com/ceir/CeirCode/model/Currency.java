package com.ceir.CeirCode.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Currency {

	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedOn;
	
	@Type(type="date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Transient
	private String month;
	
	
	private Integer currency;
	private double riel;
	private double dollar;
	@Transient
	private String currencyInterp;
	
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setSerialVersionUID(long serialVersionUID) {
		Currency.serialVersionUID = serialVersionUID;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public double getRiel() {
		return riel;
	}
	public void setRiel(double riel) {
		this.riel = riel;
	}
	public double getDollar() {
		return dollar;
	}
	public void setDollar(double dollar) {
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Currency [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", date=");
		builder.append(date);
		builder.append(", month=");
		builder.append(month);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", riel=");
		builder.append(riel);
		builder.append(", dollar=");
		builder.append(dollar);
		builder.append(", currencyInterp=");
		builder.append(currencyInterp);
		builder.append("]");
		return builder.toString();
	}
}
