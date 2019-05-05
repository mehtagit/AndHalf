package com.gl.ceir.config.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ImeiMsisdnIdentity implements Serializable {

	@NotNull
	private Long imei;
	@NotNull
	private Long msisdn;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ImeiMsisdnIdentity that = (ImeiMsisdnIdentity) o;

		if (imei == null || msisdn == null)
			return false;
		else if (imei == that.imei && msisdn == that.msisdn)
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		int result = imei.hashCode();
		result = 31 * result + msisdn.hashCode();
		return result;
	}
}
