package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MediationSource {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private MobileOperator mobileOperator;
	private String fileFormat;
	private String filePath;
	private String fileNameFormat;
	private int poolingFrequency;

	public MediationSource() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MobileOperator getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(MobileOperator mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNameFormat() {
		return fileNameFormat;
	}

	public void setFileNameFormat(String fileNameFormat) {
		this.fileNameFormat = fileNameFormat;
	}

	public int getPoolingFrequency() {
		return poolingFrequency;
	}

	public void setPoolingFrequency(int poolingFrequency) {
		this.poolingFrequency = poolingFrequency;
	}

}
