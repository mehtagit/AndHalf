package com.gl.ceir.config.model;

import java.io.Serializable;
import java.util.Map;

public class RawMail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tag;
	private UserProfile userProfile;
	private long featureId;
	private String featureName;
	private String subFeature;
	private String featureTxnId;
	private String subject; 
	private Map<String, String> placeholders;
	
	public RawMail(String tag, UserProfile userProfile, long featureId, String featureName, String subFeature,
			String featureTxnId, String subject, Map<String, String> placeholders) {
		super();
		this.tag = tag;
		this.userProfile = userProfile;
		this.featureId = featureId;
		this.featureName = featureName;
		this.subFeature = subFeature;
		this.featureTxnId = featureTxnId;
		this.subject = subject;
		this.placeholders = placeholders;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public long getFeatureId() {
		return featureId;
	}
	public void setFeatureId(long featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getSubFeature() {
		return subFeature;
	}
	public void setSubFeature(String subFeature) {
		this.subFeature = subFeature;
	}
	public String getFeatureTxnId() {
		return featureTxnId;
	}
	public void setFeatureTxnId(String featureTxnId) {
		this.featureTxnId = featureTxnId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Map<String, String> getPlaceholders() {
		return placeholders;
	}
	public void setPlaceholders(Map<String, String> placeholders) {
		this.placeholders = placeholders;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RawMail [tag=");
		builder.append(tag);
		builder.append(", userProfile=");
		builder.append(userProfile);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", featureName=");
		builder.append(featureName);
		builder.append(", subFeature=");
		builder.append(subFeature);
		builder.append(", featureTxnId=");
		builder.append(featureTxnId);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", placeholders=");
		builder.append(placeholders);
		builder.append("]");
		return builder.toString();
	}
	
	

}
