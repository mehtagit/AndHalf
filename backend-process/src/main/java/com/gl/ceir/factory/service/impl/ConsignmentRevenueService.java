package com.gl.ceir.factory.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.converter.CurrencyConverter;
import com.gl.ceir.entity.ConsignmentMgmt;
import com.gl.ceir.entity.ConsignmentRevenueDailyDb;
import com.gl.ceir.entity.CurrencyConversionDb;
import com.gl.ceir.entity.RegularizeDeviceDb;
import com.gl.ceir.factory.service.BaseService;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.ConsignmentRevenueDailyRepository;
import com.gl.ceir.repo.CurrencyConversionRepository;
import com.gl.ceir.repo.SystemConfigListRepository;
import com.gl.ceir.repo.SystemConfigurationDbRepository;
import com.gl.ceir.service.ConsignmentServiceImpl;
import com.gl.ceir.service.CurrencyConversionServiceImpl;
import com.gl.ceir.specification.GenericSpecificationBuilder;

@Component
public class ConsignmentRevenueService extends BaseService{

	private static final Logger logger = LogManager.getLogger(ConsignmentRevenueService.class);

	Map<Integer, CurrencyConversionDb> exchangeRateMap = null;

	@Autowired
	SystemConfigListRepository systemConfigListRepository;

	@Autowired
	CurrencyConversionRepository currencyConversionRepository;

	@Autowired
	ConsignmentRevenueDailyRepository consignmentRevenueDailyRepository;

	@Autowired
	ConsignmentServiceImpl consignmentServiceImpl;

	@Autowired
	CurrencyConversionServiceImpl currencyConversionServiceImpl;

	@Autowired
	CurrencyConverter currencyConverter;

	@Override
	public void fetch() {

		try {
			ConsignmentRevenueDailyDb consignmentRevenueDailyDb = new ConsignmentRevenueDailyDb();

			// Read all consignments registered today and having total price greater than Zero.
			List<ConsignmentMgmt> consignmentMgmts = consignmentServiceImpl.getConsignmentOfTodayWithTotalPriceGreaterThanZero();
			logger.info("consignmentMgmts : " + consignmentMgmts);
			
			// Convert in Dollar and Riel.
			if(consignmentMgmts.isEmpty()) {
				logger.info("No valid consignment of having total price greater than zero today is found.");
			}else {
				// Read Exchange Rate
				exchangeRateMap = currencyConversionServiceImpl.getCurrencyRateOfCurrentMonthMap();
				logger.info("exchangeRateMap : " + exchangeRateMap);
				
				for(ConsignmentMgmt consignmentMgmt : consignmentMgmts) {
					// Convert Revenue in Dollar and Riel and sum up.
					consignmentRevenueDailyDb.setTotalAmountInDollar(
							consignmentRevenueDailyDb.getTotalAmountInDollar() + currencyConverter.exchangeRate( 
									exchangeRateMap.get(consignmentMgmt.getCurrency()).getDollarRate(), 
									consignmentMgmt.getTotalPrice())
							);

					consignmentRevenueDailyDb.setTotalAmountInRiel(
							consignmentRevenueDailyDb.getTotalAmountInRiel() + currencyConverter.exchangeRate( 
									exchangeRateMap.get(consignmentMgmt.getCurrency()).getReilRate(), 
									consignmentMgmt.getTotalPrice())
							);
				}

				process(consignmentRevenueDailyDb);
			}

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void process(Object o) {

		ConsignmentRevenueDailyDb consignmentRevenueDailyDb = (ConsignmentRevenueDailyDb) o;

		if(Objects.isNull(consignmentRevenueDailyDb)) {
			logger.info("Skipping null consignmentRevenueDailyDb.");
		}else {
			consignmentRevenueDailyRepository.save(consignmentRevenueDailyDb);
		}
	}

}
