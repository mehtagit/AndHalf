package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class DocumentsByImeiMsisdn {

	@NotNull
	private Long imei;

	@NotNull
	private Long msisdn;
	
	
}
