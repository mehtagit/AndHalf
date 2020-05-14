package com.gl.ceir.config.model;

import javax.persistence.Entity;

@Entity
public class CustomFilter {

	private Long id;
	private Integer featureId;
	private String featureName;
	private Integer userTypeId;
	private String userTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("FilterRequest [id=");
		builder.append(id);

		builder.append(", featureId=");
		builder.append(featureId);

		builder.append(", featureName=");
		builder.append(featureName);

		builder.append(", userTypeId=");
		builder.append(userTypeId);

		builder.append(", userTypeName=");
		builder.append(userTypeName);

		builder.append("]");
		return builder.toString();
	}

}
