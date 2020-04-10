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
import com.gl.ceir.entity.ConsignmentMgmt;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.ConsignmentRepository;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;
import com.gl.ceir.util.Utility;

@Service
public class ConsignmentServiceImpl {

	private static final Logger logger = LogManager.getLogger(ConsignmentServiceImpl.class);

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	public List<ConsignmentMgmt> getConsignmentOfTodayWithTotalPriceGreaterThanZero() {
		try {
			String date = DateUtil.nextDate(-1);
			
			logger.info("Date in query : " + date);
			return consignmentRepository.findAll(buildSpecification(DateUtil.nextDate(0)).build());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>(1);
		}
	}

	private GenericSpecificationBuilder<ConsignmentMgmt> buildSpecification(String date){
		GenericSpecificationBuilder<ConsignmentMgmt> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		cmsb.with(new SearchCriteria("createdOn", date , SearchOperation.GREATER_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("createdOn", date , SearchOperation.LESS_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("totalPrice", 0, SearchOperation.GREATER_THAN, Datatype.STRING));

		return cmsb;
	}

}
