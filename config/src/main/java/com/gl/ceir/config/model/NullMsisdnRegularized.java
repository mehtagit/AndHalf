package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class NullMsisdnRegularized {

	@Id
	private Long msisdn;

	public Long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(Long msisdn) {
		this.msisdn = msisdn;
	}

}
