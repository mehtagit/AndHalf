package com.gl.ceir.config.model.file;

public class SystemConfigListFileModel {
	
	private Long userId;
	private String featureName;
	private String subFeatureName;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getSubFeatureName() {
		return subFeatureName;
	}
	public void setSubFeatureName(String subFeatureName) {
		this.subFeatureName = subFeatureName;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuditTrailFileModel [userId=");
		builder.append(userId);
		builder.append(", featureName=");
		builder.append(featureName);
		builder.append(", subFeatureName=");
		builder.append(subFeatureName);
		builder.append("]");
		return builder.toString();
	}

}
