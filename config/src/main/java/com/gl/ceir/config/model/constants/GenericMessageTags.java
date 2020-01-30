package com.gl.ceir.config.model.constants;

public enum GenericMessageTags {

	NULL_VISA ("NULL_VISA", "Request visa update should not be null."),
	EMPTY_VISA ("EMPTY_VISA", "Request visa update should not be empty."),
	NULL_NID ("NULL_NID", "Null NID is not allowed for the request."),
	VISA_UPDATE_NOT_ALLOWED ("VISA_UPDATE_NOT_ALLOWED", "You are not allowed to update Visa."),
	VISA_UPDATE_SUCCESS ("VISA_UPDATE_SUCCESS", "Visa of user have been updated succesfully."),
	VISA_UPDATE_FAIL ("VISA_UPDATE_FAIL", "Updation of Visa of user have been failed."),
	INVALID_USER ("INVALID_USER", "User does not exist."),
	NULL_REQ ("NULL_REQ", "Request can't be null."),
	USER_UPDATE_SUCCESS("USER_UPDATE_SUCCESS", "User have been updated successfully.");	
	
	private String tag;
	private String message;
	
	private GenericMessageTags() {
		// TODO Auto-generated constructor stub
	}

	GenericMessageTags(String tag, String message) {
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
