package com.ceir.CeirCode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
@Entity
@Table(name = "village_db")
public class Village {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;

	@Column(name = "COMMUNE_ID")
	private Long communeID;
	private String village;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Village [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", communeID=");
		builder.append(communeID);
		builder.append(", village=");
		builder.append(village);
		builder.append("]");
		return builder.toString();
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
	public Long getCommuneID() {
		return communeID;
	}
	public void setCommuneID(Long communeID) {
		this.communeID = communeID;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * @ManyToOne private Commune commune;
	 * 
	 * public Commune getCommune() { return commune; }
	 * 
	 * public void setCommune(Commune commune) { this.commune = commune; }
	 */

	
}
