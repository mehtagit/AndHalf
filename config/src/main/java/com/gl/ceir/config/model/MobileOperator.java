package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
public class MobileOperator {
	@Id
	@GeneratedValue
	private Integer iD;
	@NotNull
	private String name;
	
	public MobileOperator(){}

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MobileOperator [iD=" + iD + ", name=" + name + "]";
	}
	
	
}
