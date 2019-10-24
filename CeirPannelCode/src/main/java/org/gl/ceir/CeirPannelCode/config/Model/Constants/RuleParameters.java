package org.gl.ceir.CeirPannelCode.config.Model.Constants;

public enum RuleParameters {
	MSISDN("MSISDN"), IMEI("IMEI"), IMEI_MSISDN("IMEI_MSISDN"), IMEI_LENGTH("IMEI_LENGTH"), IMEI_COUNT(
			"IMEI_COUNT"), IMEI_STATUS("IMEI_STATUS"), IMEI_TAX("IMEI_TAX"), TAC("TAC");
	private String name;

	RuleParameters(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
