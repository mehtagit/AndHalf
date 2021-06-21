package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class SubstationDefaultResponseModel {
	
	private Integer id;
	private String modemId;
	private String portId;
	private String modem;
	private String simNum;
	private String mobileNum;
	private String imeiNum;
	private Integer statusint;
	private String lastInitTime;
	private String lastActivityTime;
	private String reason;
	private String flag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModemId() {
		return modemId;
	}
	public void setModemId(String modemId) {
		this.modemId = modemId;
	}
	public String getPortId() {
		return portId;
	}
	public void setPortId(String portId) {
		this.portId = portId;
	}
	public String getModem() {
		return modem;
	}
	public void setModem(String modem) {
		this.modem = modem;
	}
	public String getSimNum() {
		return simNum;
	}
	public void setSimNum(String simNum) {
		this.simNum = simNum;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getImeiNum() {
		return imeiNum;
	}
	public void setImeiNum(String imeiNum) {
		this.imeiNum = imeiNum;
	}
	public Integer getStatusint() {
		return statusint;
	}
	public void setStatusint(Integer statusint) {
		this.statusint = statusint;
	}
	public String getLastInitTime() {
		return lastInitTime;
	}
	public void setLastInitTime(String lastInitTime) {
		this.lastInitTime = lastInitTime;
	}
	public String getLastActivityTime() {
		return lastActivityTime;
	}
	public void setLastActivityTime(String lastActivityTime) {
		this.lastActivityTime = lastActivityTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubstationDefaultResponseModel [id=");
		builder.append(id);
		builder.append(", modemId=");
		builder.append(modemId);
		builder.append(", portId=");
		builder.append(portId);
		builder.append(", modem=");
		builder.append(modem);
		builder.append(", simNum=");
		builder.append(simNum);
		builder.append(", mobileNum=");
		builder.append(mobileNum);
		builder.append(", imeiNum=");
		builder.append(imeiNum);
		builder.append(", statusint=");
		builder.append(statusint);
		builder.append(", lastInitTime=");
		builder.append(lastInitTime);
		builder.append(", lastActivityTime=");
		builder.append(lastActivityTime);
		builder.append(", reason=");
		builder.append(reason);
		builder.append(", flag=");
		builder.append(flag);
		builder.append("]");
		return builder.toString();
	}
	
	


}
