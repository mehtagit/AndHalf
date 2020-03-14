package com.ceir.CeirCode.repoService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.Currency;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.repo.CurrencyRepo;

@Service
public class CurrencyRepoService {

	@Autowired
	CurrencyRepo currencyRepo;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public Currency getById(long id){
		try {
			return currencyRepo.findById(id);
		}
		catch(Exception e) {
			log.info("error occurs when fetching data by id");
			log.info(e.toString());
			return null;
		}
	}
	
	public Currency  save(Currency currency) {
		
		try {
			log.info("going to save  currency data");
			return currencyRepo.save(currency);
			
		}
		catch(Exception e) {
			log.info("error occurs while adding  currency data");
			log.info(e.toString());
			return  null;
		}
	}
	
public Currency  topDataByDate(Date date) {
		
		try {
			log.info("going to fetch data by top date");
			return currencyRepo.findTopByDateOrderByIdDesc(date);
			
		}
		catch(Exception e) {
			log.info("error occurs while adding  currency data");
			log.info(e.toString());
			return  null;
		}
	}
	
}
