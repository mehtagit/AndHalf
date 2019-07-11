package com.gl.ceir.config.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	private String uploadDir;
	private String tacUploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getTacUploadDir() {
		return tacUploadDir;
	}

	public void setTacUploadDir(String tacUploadDir) {
		this.tacUploadDir = tacUploadDir;
	}

}
