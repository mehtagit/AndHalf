package org.gl.ceir.pageElement.model;

import org.springframework.stereotype.Component;

@Component
public class Button {
	private String buttonTitle = null;
	private String buttonURL = null;
	private String id =null;
	@Override
	public String toString() {
		return "Button [buttonTitle=" + buttonTitle + ", buttonURL=" + buttonURL + ", id=" + id + "]";
	}
	public String getButtonTitle() {
		return buttonTitle;
	}
	public void setButtonTitle(String buttonTitle) {
		this.buttonTitle = buttonTitle;
	}
	public String getButtonURL() {
		return buttonURL;
	}
	public void setButtonURL(String buttonURL) {
		this.buttonURL = buttonURL;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
