package com.gl.ceir.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.configuration.PropertiesReader;
import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.entity.DeviceUsageDb;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.DeviceUsageDbRepository;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;
import com.gl.ceir.util.Utility;

@Service
public class DeviceUsageServiceImpl {

	private static final Logger logger = LogManager.getLogger(DeviceUsageServiceImpl.class);

	@Autowired
	PropertiesReader propertiesReader;
	
	@Autowired
	DeviceUsageDbRepository deviceUsageDbRepository;

	@Autowired
	Utility utility;

	public List<DeviceUsageDb> getDeviceUsageOfTodayHavingActionUserReg() {
		try {
			return deviceUsageDbRepository.findAll(buildSpecification(DateUtil.nextDate(0)).build());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>(1);
		}
	}

	private GenericSpecificationBuilder<DeviceUsageDb> buildSpecification(String date){
		GenericSpecificationBuilder<DeviceUsageDb> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		cmsb.with(new SearchCriteria("modifiedOn", date , SearchOperation.GREATER_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("modifiedOn", date , SearchOperation.LESS_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("action", "USER_REG", SearchOperation.GREATER_THAN, Datatype.STRING));

		return cmsb;
	}

}
