package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ActionParameters {

	@Id
	@GeneratedValue
	private Integer iD;
	private ActionParametersName name;
	private String value;
	public Integer getiD() {
		return iD;
	}
	public void setiD(Integer iD) {
		this.iD = iD;
	}
	public ActionParametersName getName() {
		return name;
	}
	public void setName(ActionParametersName name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}

