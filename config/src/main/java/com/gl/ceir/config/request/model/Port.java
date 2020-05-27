package com.gl.ceir.config.request.model;

public class Port {

	private Integer userTypeId;
	
	public Integer getUserTypeId() {
		return userTypeId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Registeration [userTypeId=");
		builder.append(userTypeId);
		builder.append(", arrivalPort=");
		builder.append(arrivalPort);
		builder.append(", portAddress=");
		builder.append(portAddress);
		builder.append("]");
		return builder.toString();
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Integer getArrivalPort() {
		return arrivalPort;
	}

	public void setArrivalPort(Integer arrivalPort) {
		this.arrivalPort = arrivalPort;
	}

	public Integer getPortAddress() {
		return portAddress;
	}

	public void setPortAddress(Integer portAddress) {
		this.portAddress = portAddress;
	}

	private Integer arrivalPort;

    private Integer portAddress;
   
}
