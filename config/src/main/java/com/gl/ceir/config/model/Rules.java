package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gl.ceir.config.model.constants.RuleOperator;
import com.gl.ceir.config.model.constants.RuleParameters;
import com.gl.ceir.config.model.constants.RuleType;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class Rules {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private boolean guiDisplay;
	private boolean enabled;
	private String createdBy;
	private String approvedBy;
	private Date createdOn;
	private Date modifiedOn;
	@Enumerated(EnumType.STRING)
	private RuleOperator operator;
	@Enumerated(EnumType.STRING)
	private RuleParameters parameters;
	private String min;
	private String max;

	public RuleOperator getOperator() {
		return operator;
	}

	public void setOperator(RuleOperator operator) {
		this.operator = operator;
	}

	public RuleParameters getParameters() {
		return parameters;
	}

	public void setParameters(RuleParameters parameters) {
		this.parameters = parameters;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	@Enumerated(EnumType.STRING)
	private RuleType ruleType;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isGuiDisplay() {
		return guiDisplay;
	}

	public void setGuiDisplay(boolean guiDisplay) {
		this.guiDisplay = guiDisplay;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
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

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Rules [id=" + id + ", name=" + name + ", operator=" + operator + ", parameters=" + parameters + ", min="
				+ min + ", max=" + max + ", ruleType=" + ruleType + "]";
	}

}
