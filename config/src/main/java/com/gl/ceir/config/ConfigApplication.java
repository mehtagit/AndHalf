package com.gl.ceir.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.service.impl.ActionServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
@EnableJpaAuditing
public class ConfigApplication {

	@Autowired
	static ActionServiceImpl actionServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

}
