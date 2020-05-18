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
			String fromDate = DateUtil.nextDate(-1);
			String toDate = DateUtil.nextDate(0);
			return consignmentRepository.findAll(buildSpecification(fromDate, toDate).build());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>(1);
		}
	}

	private GenericSpecificationBuilder<ConsignmentMgmt> buildSpecification(String fromDate, String toDate){
		GenericSpecificationBuilder<ConsignmentMgmt> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		cmsb.with(new SearchCriteria("createdOn", fromDate , SearchOperation.GREATER_THAN_OR_EQUAL, Datatype.DATE));
		cmsb.with(new SearchCriteria("createdOn", toDate , SearchOperation.LESS_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("totalPrice", 0, SearchOperation.GREATER_THAN, Datatype.STRING));

		return cmsb;
	}

}
