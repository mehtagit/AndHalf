package com.gl.filemover.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name="cdr_file_records_db")
@Getter @Setter
public class CDRFileRecords implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;
	
	private String source;
	private String operator;
	
	@Column(name="FILE_NAME")
	private String fileName;
	
	
	@Column(name="STATUS_SIG1")
	private String statusSIG1;
	
	@Column(name="STATUS_SIG2")
	private String statusSIG2;
		
	@Column(name="CDR_RECD_SERVER")
	private String cdrRecdServer;
	
	@Column(name="STS_SIG1_UTIME")
	private String sig1Utime;
	
	@Column(name="STS_SIG2_UTIME")
	private String  sig2Utime;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CDRFileRecords [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", source=");
		builder.append(source);
		builder.append(", operator=");
		builder.append(operator);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", statusSIG1=");
		builder.append(statusSIG1);
		builder.append(", statusSIG2=");
		builder.append(statusSIG2);
		builder.append(", cdrRecdServer=");
		builder.append(cdrRecdServer);
		builder.append(", sig1Utime=");
		builder.append(sig1Utime);
		builder.append(", sig2Utime=");
		builder.append(sig2Utime);
		builder.append("]");
		return builder.toString();
	}

	
}
