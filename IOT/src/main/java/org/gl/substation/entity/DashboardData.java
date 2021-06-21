package org.gl.substation.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter 
public class DashboardData {
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
	DashboardData(){
		
	}
	
	
	


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DashboardData [id=");
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





	public DashboardData(Integer ID, String modemId, String portId, String modem, Integer statusint ) {
		super();
		this.id = ID;
		this.modemId = modemId;
		this.portId = portId;
		this.modem = modem;
		this.statusint = statusint;
	}
	
	
}
