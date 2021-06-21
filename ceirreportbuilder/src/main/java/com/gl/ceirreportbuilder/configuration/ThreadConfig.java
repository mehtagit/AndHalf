package com.gl.ceirreportbuilder.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ThreadConfig {
	private final Logger logger = LogManager.getLogger(ThreadConfig.class);
	@Autowired
	PropertiesReader propertiesReader;
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		logger.info("Core pool size:["+propertiesReader.threadCorePoolSize+"] and Max pool size:["+propertiesReader.threadMaxPoolSize+"]");
		pool.setCorePoolSize(propertiesReader.threadCorePoolSize);
		pool.setMaxPoolSize(propertiesReader.threadMaxPoolSize);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}
}
