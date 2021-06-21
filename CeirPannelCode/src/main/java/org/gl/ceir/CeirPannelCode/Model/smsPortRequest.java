package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class smsPortRequest {
	private Integer id,statusint;
	private String portId;
	private String modemId,clientId,status,action;
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatusint() {
		return statusint;
	}
	public void setStatusint(Integer statusint) {
		this.statusint = statusint;
	}
	public String getPortId() {
		return portId;
	}
	public void setPortId(String portId) {
		this.portId = portId;
	}
	public String getModemId() {
		return modemId;
	}
	public void setModemId(String modemId) {
		this.modemId = modemId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("smsPortRequest [id=");
		builder.append(id);
		builder.append(", statusint=");
		builder.append(statusint);
		builder.append(", portId=");
		builder.append(portId);
		builder.append(", modemId=");
		builder.append(modemId);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", action=");
		builder.append(action);
		builder.append("]");
		return builder.toString();
	}
	
}
