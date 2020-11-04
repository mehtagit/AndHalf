package com.gl.filemover.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class CP2PropertiesReader {
	@Value("${target_path}")
	public String sourcePath;
	
	@Value("${cp2.target_path}")
	public String targetPath;
	
	@Value("${cp2.hostName}")
	public String hostName;
	
	@Value("${cp2.serverPort}")
	public int serverPort;
	
	@Value("${cp2.timeout}")
	public int timeout;

	@Value("${cp2.copyLocation}")
	public String copyLocation;

	@Value("${cp2.userName}")
	public String userName;
	
	@Value("${cp2.password}")
	public String password;
	
	@Value("${cp2.session_timeout}")
	public int sessionTimeout;
	
	@Value("${cp2.channel_timeout}")
	public int channelTimeout;

	@Value("${cp2.remote.target_path}")
	public String remoteTargetPath;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CP2PropertiesReader [sourcePath=");
		builder.append(sourcePath);
		builder.append(", targetPath=");
		builder.append(targetPath);
		builder.append(", hostName=");
		builder.append(hostName);
		builder.append(", serverPort=");
		builder.append(serverPort);
		builder.append(", timeout=");
		builder.append(timeout);
		builder.append(", copyLocation=");
		builder.append(copyLocation);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", sessionTimeout=");
		builder.append(sessionTimeout);
		builder.append(", channelTimeout=");
		builder.append(channelTimeout);
		builder.append(", remoteTargetPath=");
		builder.append(remoteTargetPath);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
