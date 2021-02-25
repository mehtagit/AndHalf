package org.gl.substation.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component @Entity @Table(name="substation_configuration")
@Getter @Setter 
public class SubstationConfiguration {

	@Basic
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "sno")
    private Long id ;


	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "UNIT_ID", insertable = false, updatable = false)
	private PowerStation powerStation;
	
	@Column(name="UNIT_ID")
	private String unitID;

	@Column(name="CONFIGURATION_DATE")
	private String configurationDate;

	@Column(name="FIRST_INTERVAL_PACKET_DATE")
	private String firstIntervalPacketDate;

	
	@Column(name="LAST_INTERVAL_PACKET_DATE")
	private String lastIntervalPacketDate;

	@Column(name="INTERVAL_PACKET_COUNT")
	private String intervalPacketCount;

	@Column(name="FIRST_FAULT_PACKET_DATE")
	private String firstFaultPacketDate;

	
	@Column(name="LAST_FAULT_PACKET_DATE")
	private String lastFaultPacketDate;

	@Column(name="FAULT_PACKET_COUNT")
	private String faultPacketCount;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubstationConfiguration [id=");
		builder.append(id);
		builder.append(", unitID=");
		builder.append(unitID);
		builder.append(", configurationDate=");
		builder.append(configurationDate);
		builder.append(", firstIntervalPacketDate=");
		builder.append(firstIntervalPacketDate);
		builder.append(", lastIntervalPacketDate=");
		builder.append(lastIntervalPacketDate);
		builder.append(", intervalPacketCount=");
		builder.append(intervalPacketCount);
		builder.append(", firstFaultPacketDate=");
		builder.append(firstFaultPacketDate);
		builder.append(", lastFaultPacketDate=");
		builder.append(lastFaultPacketDate);
		builder.append(", faultPacketCount=");
		builder.append(faultPacketCount);
		builder.append("]");
		return builder.toString();
	}

}
