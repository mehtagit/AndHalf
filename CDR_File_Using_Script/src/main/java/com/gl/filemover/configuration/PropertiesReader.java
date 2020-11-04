package com.gl.filemover.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class PropertiesReader {

	@Value("${spring.jpa.properties.hibernate.dialect}")
	public String dialect;
	
	@Value("${source_path}")
	public String sourcePath;
	
	@Value("${target_path}")
	public String targetPath;
	
	@Value("${cdrRecdServer}")
	public String cdrRecdServer;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropertiesReader [dialect=");
		builder.append(dialect);
		builder.append(", sourcePath=");
		builder.append(sourcePath);
		builder.append(", targetPath=");
		builder.append(targetPath);
		builder.append(", cdrRecdServer=");
		builder.append(cdrRecdServer);
		builder.append("]");
		return builder.toString();
	}
				
}
