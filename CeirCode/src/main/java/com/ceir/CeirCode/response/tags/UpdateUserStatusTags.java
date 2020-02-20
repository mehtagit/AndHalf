package com.ceir.CeirCode.response.tags;

public enum UpdateUserStatusTags {

	USER_STATUS_CHANGED ("USER_STATUS_CHANGED", "User status has been changed"),
	USER_STATUS_CHANGE_FAIL("USER_STATUS_CHANGE_FAIL", "User status failed to change");
	
	private String tag;
	private String message;
	
	private UpdateUserStatusTags() {

	}

	UpdateUserStatusTags(String tag, String message) {
		this.tag = tag;
		this.message = message;
	}

	public String getTag() {
		return tag;
	}

	public String getMessage() {
		return message;
	}

}
