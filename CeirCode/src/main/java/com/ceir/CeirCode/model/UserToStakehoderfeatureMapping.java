package com.ceir.CeirCode.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
public class UserToStakehoderfeatureMapping {

	@Id       
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date createdOn;
	private Date modifiedOn; 
	
	@JsonIgnore
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "feature_id", nullable = false) 
	private StakeholderFeature stakeholderFeature; 
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usertype_id", nullable = false) 
	private Usertype userTypeFeature; 

	private String period;
	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public StakeholderFeature getStakeholderFeature() {
		return stakeholderFeature;
	}

	public void setStakeholderFeature(StakeholderFeature stakeholderFeature) {
		this.stakeholderFeature = stakeholderFeature;
	}

	public Usertype getUserTypeFeature() {
		return userTypeFeature;
	}

	public void setUserTypeFeature(Usertype userTypeFeature) {
		this.userTypeFeature = userTypeFeature;
	}
	

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "UserToStakehoderfeatureMapping [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ "]";
	}

	

	
	
}
