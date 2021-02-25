package org.gl.substation.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@Component @Entity @Table(name="substation_details")
@Getter @Setter
public class PowerStation {
	
	@Basic
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "sno")
    private Long id ;

	@Valid
	@NotEmpty(message = "district cannot be null or empty")
	private String district;

	@Valid
	@NotEmpty(message = "substation cannot be null or empty")
	private String substation;

	private String mvar;

	@Column(name="unit_id")
	private String unitID;

	
	@Column(name="sso_no")
	private String sSONo;

	
	@Column(name="je_no")
	private String jeNo;
	
	private String make;
	
	@OneToOne(mappedBy = "powerStation", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@JoinColumn(name="UNIT_ID", insertable = false, updatable = false)
	private SubstationConfiguration substationConfiguration;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PowerStation [id=");
		builder.append(id);
		builder.append(", district=");
		builder.append(district);
		builder.append(", substation=");
		builder.append(substation);
		builder.append(", mvar=");
		builder.append(mvar);
		builder.append(", unitID=");
		builder.append(unitID);
		builder.append(", sSONo=");
		builder.append(sSONo);
		builder.append(", jeNo=");
		builder.append(jeNo);
		builder.append(", make=");
		builder.append(make);
		builder.append(", substationConfiguration=");
		builder.append(substationConfiguration);
		builder.append("]");
		return builder.toString();
	}
}
