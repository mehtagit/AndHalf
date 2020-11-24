package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class SubstationDefaultResponseModel {

	private Long id ;
	private String unitId;
	private String district;

	private String substation;

	private String capacity;

	private String packetReceivedDate;

	private String panelDatetime;

	private String ryVoltage;
	private String ybVoltage;

	private String brVoltage;

	private String rampereCurrent;

	private String yampereCurrent;

	private String bampereCurrent;
	
	private String rcapCurrent;

	private String ycapCurrent;

	private String bcapCurrent;
	
	private String totalPF;

	private String overallApparentPower;

	private String overallActivePower;

	private String overallReactivePowerInd;
	private String overallReactivePowerCap;

	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubstation() {
		return substation;
	}

	public void setSubstation(String substation) {
		this.substation = substation;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getPacketReceivedDate() {
		return packetReceivedDate;
	}

	public void setPacketReceivedDate(String packetReceivedDate) {
		this.packetReceivedDate = packetReceivedDate;
	}

	public String getPanelDatetime() {
		return panelDatetime;
	}

	public void setPanelDatetime(String panelDatetime) {
		this.panelDatetime = panelDatetime;
	}

	public String getRyVoltage() {
		return ryVoltage;
	}

	public void setRyVoltage(String ryVoltage) {
		this.ryVoltage = ryVoltage;
	}

	public String getYbVoltage() {
		return ybVoltage;
	}

	public void setYbVoltage(String ybVoltage) {
		this.ybVoltage = ybVoltage;
	}

	public String getBrVoltage() {
		return brVoltage;
	}

	public void setBrVoltage(String brVoltage) {
		this.brVoltage = brVoltage;
	}

	public String getRampereCurrent() {
		return rampereCurrent;
	}

	public void setRampereCurrent(String rampereCurrent) {
		this.rampereCurrent = rampereCurrent;
	}

	public String getYampereCurrent() {
		return yampereCurrent;
	}

	public void setYampereCurrent(String yampereCurrent) {
		this.yampereCurrent = yampereCurrent;
	}

	public String getBampereCurrent() {
		return bampereCurrent;
	}

	public void setBampereCurrent(String bampereCurrent) {
		this.bampereCurrent = bampereCurrent;
	}

	public String getRcapCurrent() {
		return rcapCurrent;
	}

	public void setRcapCurrent(String rcapCurrent) {
		this.rcapCurrent = rcapCurrent;
	}

	public String getYcapCurrent() {
		return ycapCurrent;
	}

	public void setYcapCurrent(String ycapCurrent) {
		this.ycapCurrent = ycapCurrent;
	}

	public String getBcapCurrent() {
		return bcapCurrent;
	}

	public void setBcapCurrent(String bcapCurrent) {
		this.bcapCurrent = bcapCurrent;
	}

	public String getTotalPF() {
		return totalPF;
	}

	public void setTotalPF(String totalPF) {
		this.totalPF = totalPF;
	}

	public String getOverallApparentPower() {
		return overallApparentPower;
	}

	public void setOverallApparentPower(String overallApparentPower) {
		this.overallApparentPower = overallApparentPower;
	}

	public String getOverallActivePower() {
		return overallActivePower;
	}

	public void setOverallActivePower(String overallActivePower) {
		this.overallActivePower = overallActivePower;
	}

	public String getOverallReactivePowerInd() {
		return overallReactivePowerInd;
	}

	public void setOverallReactivePowerInd(String overallReactivePowerInd) {
		this.overallReactivePowerInd = overallReactivePowerInd;
	}

	public String getOverallReactivePowerCap() {
		return overallReactivePowerCap;
	}

	public void setOverallReactivePowerCap(String overallReactivePowerCap) {
		this.overallReactivePowerCap = overallReactivePowerCap;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubstationDefaultResponseModel [id=");
		builder.append(id);
		builder.append(", unitId=");
		builder.append(unitId);
		builder.append(", district=");
		builder.append(district);
		builder.append(", substation=");
		builder.append(substation);
		builder.append(", capacity=");
		builder.append(capacity);
		builder.append(", packetReceivedDate=");
		builder.append(packetReceivedDate);
		builder.append(", panelDatetime=");
		builder.append(panelDatetime);
		builder.append(", ryVoltage=");
		builder.append(ryVoltage);
		builder.append(", ybVoltage=");
		builder.append(ybVoltage);
		builder.append(", brVoltage=");
		builder.append(brVoltage);
		builder.append(", rampereCurrent=");
		builder.append(rampereCurrent);
		builder.append(", yampereCurrent=");
		builder.append(yampereCurrent);
		builder.append(", bampereCurrent=");
		builder.append(bampereCurrent);
		builder.append(", rcapCurrent=");
		builder.append(rcapCurrent);
		builder.append(", ycapCurrent=");
		builder.append(ycapCurrent);
		builder.append(", bcapCurrent=");
		builder.append(bcapCurrent);
		builder.append(", totalPF=");
		builder.append(totalPF);
		builder.append(", overallApparentPower=");
		builder.append(overallApparentPower);
		builder.append(", overallActivePower=");
		builder.append(overallActivePower);
		builder.append(", overallReactivePowerInd=");
		builder.append(overallReactivePowerInd);
		builder.append(", overallReactivePowerCap=");
		builder.append(overallReactivePowerCap);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}



}
