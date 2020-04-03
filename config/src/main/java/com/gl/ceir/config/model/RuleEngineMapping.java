package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gl.ceir.config.model.constants.RulesNames;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class RuleEngineMapping implements Serializable {
	/**
	 * 
	 */
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
	@Column(length = 20)
	private String feature;

	@NotNull
	//@Enumerated(EnumType.STRING)
	private String name;
	
	@NotNull
	@Column(length = 20)
	private String graceAction;
	
	@NotNull
	@Column(length = 20)
	private String postGraceAction;
	
	@NotNull
	private Integer ruleOrder;
	
	@NotNull
	@Column(length = 20)
	private String userType;
	
	@Column(length = 10)
	private String failedRuleActionGrace;
	
	@Column(length = 10)
	private String failedRuleActionPostGrace;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getGraceAction() {
		return graceAction;
	}

	public void setGraceAction(String graceAction) {
		this.graceAction = graceAction;
	}

	public String getPostGraceAction() {
		return postGraceAction;
	}

	public void setPostGraceAction(String postGraceAction) {
		this.postGraceAction = postGraceAction;
	}

	

	public Integer getRuleOrder() {
		return ruleOrder;
	}

	public void setRuleOrder(Integer ruleOrder) {
		this.ruleOrder = ruleOrder;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFailedRuleActionGrace() {
		return failedRuleActionGrace;
	}

	public void setFailedRuleActionGrace(String failedRuleActionGrace) {
		this.failedRuleActionGrace = failedRuleActionGrace;
	}

	public String getFailedRuleActionPostGrace() {
		return failedRuleActionPostGrace;
	}

	public void setFailedRuleActionPostGrace(String failedRuleActionPostGrace) {
		this.failedRuleActionPostGrace = failedRuleActionPostGrace;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RuleEngineMapping [id=");
		builder.append(id);
		builder.append(", feature=");
		builder.append(feature);
		builder.append(", name=");
		builder.append(name);
		builder.append(", graceAction=");
		builder.append(graceAction);
		builder.append(", postGraceAction=");
		builder.append(postGraceAction);
		builder.append(", ruleOrder=");
		builder.append(ruleOrder);
		builder.append(", userType=");
		builder.append(userType);
		builder.append("]");
		return builder.toString();
	}
	
}
