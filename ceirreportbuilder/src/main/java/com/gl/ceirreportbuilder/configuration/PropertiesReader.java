package com.gl.ceirreportbuilder.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:application.properties")
//@PropertySource("classpath:application.properties")
@PropertySources({
@PropertySource(value = {"file:${application_daily.properties}"}, ignoreResourceNotFound = true),
@PropertySource(value = {"file:${application_monthly.properties}"}, ignoreResourceNotFound = true),
@PropertySource(value = {"file:${application_tilldate.properties}"}, ignoreResourceNotFound = true),
@PropertySource(value = {"classpath:application.properties"})
})
public class PropertiesReader {

	@Value("${spring.jpa.properties.hibernate.dialect}")
	public String dialect;
	
	@Value("${date.view.format}")
	public String dateViewFormat;
	
	@Value("${thread-core-pool-size}")
	public int threadCorePoolSize;
	
	@Value("${thread-max-pool-size}")
	public int threadMaxPoolSize;
	
	@Value("${scheduler-name}")
	public String schedulerName;
	
	@Value("${process-sleep-time}")
	public long processSleepTime;
	
	@Value("${retryCount}")
	public int retryCount;
}
