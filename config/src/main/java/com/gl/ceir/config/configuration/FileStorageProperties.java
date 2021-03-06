package com.gl.ceir.config.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	private String uploadDir;
	private String tacUploadDir;
	private String stokeUploadDir;
	private String actionUploadDir;
	private String downloadDir;
	private String immegreationUploadDir;


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

	public String getStokeUploadDir() {
		return stokeUploadDir;
	}

	public void setStokeUploadDir(String stokeUploadDir) {
		this.stokeUploadDir = stokeUploadDir;
	}

	public String getActionUploadDir() {
		return actionUploadDir;
	}

	public void setActionUploadDir(String actionUploadDir) {
		this.actionUploadDir = actionUploadDir;
	}

	public String getDownloadDir() {
		return downloadDir;
	}

	public void setDownloadDir(String downloadDir) {
		this.downloadDir = downloadDir;
	}

	public String getImmegreationUploadDir() {
		return immegreationUploadDir;
	}

	public void setImmegreationUploadDir(String immegreationUploadDir) {
		this.immegreationUploadDir = immegreationUploadDir;
	}





}
