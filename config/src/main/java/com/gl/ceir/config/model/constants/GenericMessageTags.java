package com.gl.ceir.config.model.constants;

public enum GenericMessageTags {

	NULL_VISA ("NULL_VISA", ""),
	EMPTY_VISA ("EMPTY_VISA",""),
	NULL_NID ("NULL_NID",""),
	VISA_UPDATE_NOT_ALLOWED ("VISA_UPDATE_NOT_ALLOWED",""),
	VISA_UPDATE_SUCCESS ("VISA_UPDATE_SUCCESS",""),
	INVALID_USER ("INVALID_USER",""),
	NULL_REQ ("NULL_REQ","Request can't be null.");	
	
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
