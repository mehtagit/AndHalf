package org.gl.ceir.CeirPannelCode.Model;

public class GsmaDetail {
	
	private String bandName;
	private Integer deviceId;
	private String equipmentType;
	private Long imei;
	private Long imsi;
	private String modelName;
	private Long msisdn;
	private String operatingSystem;
	private String identifierType;
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public Long getImei() {
		return imei;
	}
	public void setImei(Long imei) {
		this.imei = imei;
	}
	public Long getImsi() {
		return imsi;
	}
	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Long getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(Long msisdn) {
		this.msisdn = msisdn;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getIdentifierType() {
		return identifierType;
	}
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}
	@Override
	public String toString() {
		return "GsmaDetail [bandName=" + bandName + ", deviceId=" + deviceId + ", equipmentType=" + equipmentType
				+ ", imei=" + imei + ", imsi=" + imsi + ", modelName=" + modelName + ", msisdn=" + msisdn
				+ ", operatingSystem=" + operatingSystem + ", identifierType=" + identifierType + "]";
	}

	
	
}