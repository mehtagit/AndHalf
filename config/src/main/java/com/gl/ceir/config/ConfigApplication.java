package com.gl.ceir.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.SystemPolicyMapping;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.SystemPolicyMappingService;
import com.gl.ceir.config.service.impl.SystemPolicyMappingServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
@EnableJpaAuditing
public class ConfigApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ConfigApplication.class, args);
		SystemPolicyMappingService f = context.getBean(SystemPolicyMappingServiceImpl.class);
		
	}

	@Autowired
	private DeviceSnapShotService service;

}
