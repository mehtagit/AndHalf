package com.gl.ceir.config;

import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.Action;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.SystemPolicyMapping;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;
import com.gl.ceir.config.service.SystemPolicyMappingService;
import com.gl.ceir.config.service.impl.DeviceSnapShotServiceImpl;
import com.gl.ceir.config.service.impl.ImsieSeriesServiceImpl;
import com.gl.ceir.config.service.impl.SystemPolicyMappingServiceImpl;
import com.gl.ceir.config.system.request.Request;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
@EnableJpaAuditing
@EnableAutoConfiguration
@EnableCaching
public class ConfigApplication {
	private static final Logger logger = LogManager.getLogger(ImsieSeriesServiceImpl.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ConfigApplication.class, args);
//		DeviceSnapShot deviceSnapShot = context.getBean(DeviceSnapShotServiceImpl.class).get(13329001992380L);
//		logger.info("FIRST: " + deviceSnapShot.toString());
//
//		deviceSnapShot = context.getBean(DeviceSnapShotServiceImpl.class).get(13329001992380L);
//		logger.info("SECOND: "+deviceSnapShot.toString());

	}

	@Autowired
	private DeviceSnapShotService service;

	private static DeviceSnapShot convertRequestToDeviceSnapShot() {
		DeviceSnapShot deviceSnapShot = new DeviceSnapShot();
		deviceSnapShot.setAction(ActionNames.AUTO_REGULARIZED.toString());
		deviceSnapShot.setMobileOperatorId(1L);
		deviceSnapShot.setImei(898989L);
		deviceSnapShot.setMsisdn(9090909L);

		deviceSnapShot.setImeiStatus(ImeiStatus.AUTO_REGULARIZED);
		// deviceSnapShot.setFailedRuleId(request.getFailRule().getId().toString());
		// deviceSnapShot.setFailedRuleName(request.getFailRule().getName());
		deviceSnapShot.setDuplicateImeiMsisdns(new ArrayList<>());
		deviceSnapShot.getDuplicateImeiMsisdns().add(convertToDuplicateImeiMsisdn());
		return deviceSnapShot;
	}

	private static DuplicateImeiMsisdn convertToDuplicateImeiMsisdn() {
		DuplicateImeiMsisdn duplicateImeiMsisdn = new DuplicateImeiMsisdn();
		duplicateImeiMsisdn.setImeiMsisdnIdentity(new ImeiMsisdnIdentity(898989L, 9090909L));
		duplicateImeiMsisdn.setFileName("file");

		duplicateImeiMsisdn.setImeiStatus(ImeiStatus.AUTO_REGULARIZED);
		duplicateImeiMsisdn.setImsi(3232L);
		duplicateImeiMsisdn.setRegulizedByUser(Boolean.FALSE);
		return duplicateImeiMsisdn;

	}

}
