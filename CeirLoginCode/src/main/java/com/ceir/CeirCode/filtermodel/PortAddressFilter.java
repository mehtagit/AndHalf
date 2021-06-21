package com.ceir.CeirCode.filtermodel;

import com.ceir.CeirCode.model.AllRequest;

public class PortAddressFilter  extends AllRequest{
	long id;
	public String  portId;
	private Integer statusint;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPortId() {
		return portId;
	}
	public void setPortId(String portId) {
		this.portId = portId;
	}
	public Integer getStatusint() {
		return statusint;
	}
	public void setStatusint(Integer statusint) {
		this.statusint = statusint;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PortAddressFilter [id=");
		builder.append(id);
		builder.append(", portId=");
		builder.append(portId);
		builder.append(", statusint=");
		builder.append(statusint);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
		
}
