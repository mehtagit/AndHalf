package com.gl.ceir.pojo;

import java.util.HashMap;
import java.util.Map;

public class UserWiseMailCount {

	private Long userId;
	private int deviceCount;
	Map<String, String> placeholderMap;
	
	public UserWiseMailCount() {
		this.placeholderMap = new HashMap<>();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public int getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public Map<String, String> getPlaceholderMap() {
		return placeholderMap;
	}

	public void setPlaceholderMap(Map<String, String> placeholderMap) {
		this.placeholderMap = placeholderMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserWiseMailCount [userId=");
		builder.append(userId);
		builder.append(", deviceCount=");
		builder.append(deviceCount);
		builder.append(", placeholderMap=");
		builder.append(placeholderMap);
		builder.append("]");
		return builder.toString();
	}
		
}
