package com.gl.CEIR.FileProcess.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "fileProperties")
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	
	private String consignmentsDir;

	public String getConsignmentsDir() {
		return consignmentsDir;
	}

	public void setConsignmentsDir(String consignmentsDir) {
		this.consignmentsDir = consignmentsDir;
	}
	
}
