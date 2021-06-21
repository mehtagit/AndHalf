package org.gl.substation.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component @Entity @Table(name="modem_info_table")
@Getter @Setter
public class ModemInfoTable {
	@Basic
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id ;
	
	@Column(name = "modem_id")
	private String modemId;
	
	@Column(name = "port_id")
	private String portId;
	
	@Column(name = "Modem")
	private String modem;
	
	@Column(name = "mobile_num")
	private String mobileNum;
	
	@Column(name = "sim_num")
	private String simNum;
	
	@Column(name = "imei_num")
	private String imeiNum;
	
	@Column(name = "statusint")
	private Integer statusint;
	
	@Column(name = "last_init_time")
	private String lastInitTime;
	
	@Column(name = "last_activity_time")
	private String lastActivityTime;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "flag")
	private String flag;
	
	@javax.persistence.Transient
	private String portInterp;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModemInfoTable [id=");
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
		builder.append(", portInterp=");
		builder.append(portInterp);
		builder.append("]");
		return builder.toString();
	}
	
	
}
