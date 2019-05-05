package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Imei 
{
	@Id
	private String imei;
	private int tac;
	private String brand;
	
}
