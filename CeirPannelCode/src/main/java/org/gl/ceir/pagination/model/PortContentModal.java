package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PortContentModal {
	
	private Integer id;
	private String modemId;
	private String portId;
	private String modem;
	private String simNum;
	private String imeiNum;
	private String lastInitTime;
	private String lastActivityTime;
	private String reason;
	private String flag;
	private Integer statusInt;
	private String portInterp;
	
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	
	public String getPortInterp() {
		return portInterp;
	}

	public void setPortInterp(String portInterp) {
		this.portInterp = portInterp;
	}

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

	public String getImeiNum() {
		return imeiNum;
	}

	public void setImeiNum(String imeiNum) {
		this.imeiNum = imeiNum;
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

	public Integer getStatusInt() {
		return statusInt;
	}

	public void setStatusInt(Integer statusInt) {
		this.statusInt = statusInt;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PortContentModal [id=");
		builder.append(id);
		builder.append(", modemId=");
		builder.append(modemId);
		builder.append(", portId=");
		builder.append(portId);
		builder.append(", modem=");
		builder.append(modem);
		builder.append(", simNum=");
		builder.append(simNum);
		builder.append(", imeiNum=");
		builder.append(imeiNum);
		builder.append(", lastInitTime=");
		builder.append(lastInitTime);
		builder.append(", lastActivityTime=");
		builder.append(lastActivityTime);
		builder.append(", reason=");
		builder.append(reason);
		builder.append(", flag=");
		builder.append(flag);
		builder.append(", statusInt=");
		builder.append(statusInt);
		builder.append(", portInterp=");
		builder.append(portInterp);
		builder.append(", additionalProperties=");
		builder.append(additionalProperties);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
