package com.gl.CEIR.FileProcess.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "config")
public class AppConfig {
	
	@Value("${tps}")
	public int tps;
	
}
