package com.gl.ceir.webactionadapter.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "fileProperties")
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	
	private String consignmentsDir;
	private String stockDir;

	public String getConsignmentsDir() {
		return consignmentsDir;
	}

	public void setConsignmentsDir(String consignmentsDir) {
		this.consignmentsDir = consignmentsDir;
	}

	public String getStockDir() {
		return stockDir;
	}

	public void setStockDir(String stockDir) {
		this.stockDir = stockDir;
	}
	
}
