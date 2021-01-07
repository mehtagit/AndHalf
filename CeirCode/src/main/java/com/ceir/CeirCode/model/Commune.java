package com.ceir.CeirCode.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
@Entity
@Table(name = "commune_db")

public class Commune {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;

	@Column(name = "DISTRICT_ID")
	private Long districtID;
	private String commune;

	/*
	 * @ManyToOne private District district;
	 */

	/*
	 * @OneToMany(mappedBy = "village") private List<Village> village;
	 * 
	 * public List<Village> getVillage() { return village; }
	 * 
	 * public void setVillage(List<Village> village) { this.village = village; }
	 */

	/*
	 * public District getDistrict() { return district; }
	 * 
	 * public void setDistrict(District district) { this.district = district; }
	 */

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

	public Long getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Long districtID) {
		this.districtID = districtID;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Commune [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", districtID=");
		builder.append(districtID);
		builder.append(", commune=");
		builder.append(commune);
		/*
		 * builder.append(", district="); builder.append(district);
		 */
		/*
		 * builder.append(", village="); builder.append(village);
		 */
		builder.append("]");
		return builder.toString();
	}

}
