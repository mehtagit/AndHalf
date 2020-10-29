package org.file.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesReader {
	@Value("${source_path}")
	public String sourcePath;
	
	@Value("${target_path}")
	public String targetPath;
	
	@Value("${cdrRecdServer}")
	public String cdrRecdServer;
	
	@Value("${hostName}")
	public String hostName;
	
	@Value("${serverPort}")
	public int serverPort;
	
	@Value("${timeout}")
	public int timeout;
	
	
	
}
