package org.gl.substation.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter 
public class DashboardData {
	private String unitID;
    private String substation ;
	private String lastIntervalPacketDate;
	private String lastFaultPacketDate;
	DashboardData(){
		
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DashboardData [unitID=");
		builder.append(unitID);
		builder.append(", substation=");
		builder.append(substation);
		builder.append(", lastIntervalPacketDate=");
		builder.append(lastIntervalPacketDate);
		builder.append(", lastFaultPacketDate=");
		builder.append(lastFaultPacketDate);
		builder.append("]");
		return builder.toString();
	}
	public DashboardData(String unitID, String substation, String lastIntervalPacketDate, String lastFaultPacketDate) {
		super();
		this.unitID = unitID;
		this.substation = substation;
		this.lastIntervalPacketDate = lastIntervalPacketDate;
		this.lastFaultPacketDate = lastFaultPacketDate;
	}
	
	
}
