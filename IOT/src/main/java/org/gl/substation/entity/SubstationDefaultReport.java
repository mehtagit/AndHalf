package org.gl.substation.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Component @Entity @Table(name="substation_default_report")
@Getter @Setter
public class SubstationDefaultReport {

	@Basic
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "sno")
	private Long id ;

	@Column(name="unit_id")
	private String unitId;

	@Column(name="district_name")
	private String district;

	@Column(name="substation_name")
	private String substation;

	@Column(name="capacity")
	private String capacity;

	@Column(name="packet_received_date")
	private String packetReceivedDate;

	@JsonIgnore
	@Column(name="panel_datetime")
	private String panelDatetime;


	@Column(name="ry_voltage")
	private String ryVoltage;

	@Column(name="yb_voltage")
	private String ybVoltage;

	@Column(name="br_voltage")
	private String brVoltage;

	@Column(name="r_current")
	private String rampereCurrent;

	@Column(name="y_current")
	private String yampereCurrent;

	@Column(name="b_current")
	private String bampereCurrent;
	
	@Column(name="r_cap_current")
	private String rcapCurrent;

	@Column(name="y_cap_current")
	private String ycapCurrent;

	@Column(name="b_cap_current")
	private String bcapCurrent;
	
	@Column(name="total_pf")
	private String totalPF;

	@Column(name="overall_apparent_power")
	private String overallApparentPower;

	@Column(name="overall_active_power")
	private String overallActivePower;

	@Column(name="overall_reactive_power_ind")
	private String overallReactivePowerInd;


	@Column(name="overall_reactive_power_cap")
	private String overallReactivePowerCap;

	@Column(name="remark")
	private String remark;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubstationDefaultReport [id=");
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
