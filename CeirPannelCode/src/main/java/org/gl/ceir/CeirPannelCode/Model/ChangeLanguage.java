package org.gl.ceir.CeirPannelCode.Model;

public class ChangeLanguage {

	private long userId;
	private String language;
	
	
	public ChangeLanguage(long userId, String language) {
		super();
		this.userId = userId;
		this.language = language;
	}
	public ChangeLanguage() {
		super();
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeLanguage [userId=");
		builder.append(userId);
		builder.append(", language=");
		builder.append(language);
		builder.append("]");
		return builder.toString();
	}
	
	
}
