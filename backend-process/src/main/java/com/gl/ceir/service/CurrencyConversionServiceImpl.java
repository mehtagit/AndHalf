package com.gl.ceir.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.configuration.PropertiesReader;
import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.entity.CurrencyConversionDb;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.CurrencyConversionRepository;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;
import com.gl.ceir.util.Utility;

@Service
public class CurrencyConversionServiceImpl {

	private static final Logger logger = LogManager.getLogger(CurrencyConversionServiceImpl.class);

	@Autowired
	CurrencyConversionRepository currencyConversionRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	public List<CurrencyConversionDb> getCurrencyRateOfCurrentMonthList() {
		try {
			return currencyConversionRepository.findAll(buildSpecification(DateUtil.nextDate(0).substring(0, 7)).build());
					
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>(1);
		}
	}
	
	public Map<Integer, CurrencyConversionDb> getCurrencyRateOfCurrentMonthMap() {
		try {
			return getCurrencyRateOfCurrentMonthList()
					.stream()
					.collect(Collectors.toMap(CurrencyConversionDb::getCurrency, c -> c));
					
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new HashMap<>(1);
		}
	}

	private GenericSpecificationBuilder<CurrencyConversionDb> buildSpecification(String date){
		GenericSpecificationBuilder<CurrencyConversionDb> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		cmsb.with(new SearchCriteria("monthAndYear", date , SearchOperation.GREATER_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("monthAndYear", date , SearchOperation.LESS_THAN, Datatype.DATE));
	
		return cmsb;
	}

}
