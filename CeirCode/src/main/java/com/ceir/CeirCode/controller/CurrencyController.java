package com.ceir.CeirCode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ceir.CeirCode.filtermodel.CurrencyFilter;
import com.ceir.CeirCode.model.Currency;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.service.CurrencyService;
import com.ceir.CeirCode.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	Utility utility;

	
	@Autowired
	CurrencyService currencyService;
	
	
	@ApiOperation(value="save currency")
	@PostMapping("/save")
	public ResponseEntity<?> saveCurrency(@RequestBody Currency currency){
		return currencyService.saveCurrency(currency);
	}
	
	@ApiOperation(value="update currency")
	@PostMapping("/update")
	public ResponseEntity<?> updateCurrency(@RequestBody Currency currency){
		return currencyService.updateCurrency(currency);
	}
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;
	
	
	@ApiOperation(value = "port address data.", response = Currency.class)
	@PostMapping("/view") 
	public MappingJacksonValue view(@RequestBody CurrencyFilter filter,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file){
		MappingJacksonValue mapping = null;
			Page<Currency> portAddressData  = currencyService.currencyData(filter, pageNo, pageSize);
			List<SystemConfigListDb> currencyList=systemConfigRepo.getByTag("CURRENCY");
			for(Currency currency:portAddressData.getContent()) {
				for(SystemConfigListDb systemConfig:currencyList) {
					Integer value=systemConfig.getValue();
					if(currency.getCurrency()==value) {
						currency.setCurrencyInterp(systemConfig.getInterp());
					}
				}
				if(currency.getDate()!=null) {
				String month=utility.convertToMonth(currency.getDate());
				currency.setMonth(month);
				String year=utility.convertToYear(currency.getDate());
				currency.setYear(year);
				} 
			}
			
			mapping = new MappingJacksonValue(portAddressData);
			return mapping;
	}
	
	@ApiOperation(value="view currency by id")
	@PostMapping("/viewById/{id}")
	public ResponseEntity<?> viewById(@PathVariable("id")long id){
		return currencyService.viewById(id);
	}
}
