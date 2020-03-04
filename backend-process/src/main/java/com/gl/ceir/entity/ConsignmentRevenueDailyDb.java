package com.gl.ceir.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ConsignmentRevenueDailyDb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;

	private Double totalAmountInDollar;

	private Double totalAmountInRiel;

	public ConsignmentRevenueDailyDb() {
		
	}

	public ConsignmentRevenueDailyDb(double totalAmountInDollar, double totalAmountInRiel) {
		this.totalAmountInDollar = totalAmountInDollar;
		this.totalAmountInRiel = totalAmountInRiel;
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

	public Double getTotalAmountInDollar() {
		return totalAmountInDollar;
	}

	public void setTotalAmountInDollar(Double totalAmountInDollar) {
		this.totalAmountInDollar = totalAmountInDollar;
	}

	public Double getTotalAmountInRiel() {
		return totalAmountInRiel;
	}

	public void setTotalAmountInRiel(Double totalAmountInRiel) {
		this.totalAmountInRiel = totalAmountInRiel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConsignmentRevenueDailyDb [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", totalAmountInDollar=");
		builder.append(totalAmountInDollar);
		builder.append(", totalAmountInRiel=");
		builder.append(totalAmountInRiel);
		builder.append("]");
		return builder.toString();
	}

}
