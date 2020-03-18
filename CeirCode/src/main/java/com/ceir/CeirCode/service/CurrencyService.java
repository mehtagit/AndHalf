package com.ceir.CeirCode.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.Constants.Datatype;
import com.ceir.CeirCode.Constants.SearchOperation;
import com.ceir.CeirCode.SpecificationBuilder.SpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.filtermodel.CurrencyFilter;
import com.ceir.CeirCode.filtermodel.PortAddressFilter;
import com.ceir.CeirCode.model.Currency;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.repo.CurrencyRepo;
import com.ceir.CeirCode.repoService.CurrencyRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.tags.CurrencyTags;
import com.ceir.CeirCode.response.tags.PortAddsTags;
import com.ceir.CeirCode.util.HttpResponse;
import com.ceir.CeirCode.util.Utility;

@Service
public class CurrencyService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	CurrencyRepo currencyrepo;

	@Autowired
	CurrencyRepoService currencyRepoService;

	@Autowired
	PropertiesReaders propertiesReader;

	@Autowired
	Utility utility;


	public ResponseEntity<?> saveCurrency(Currency currency){
		log.info("inside save Currency controller");
		log.info("currency data going to save:  "+currency);
		log.info("exist by data= "+currencyrepo.existsByDate(currency.getDate()));
		boolean monthExist=currencyrepo.existsByDate(currency.getDate());
		if(monthExist==true) {
			GenricResponse response=new GenricResponse(409,CurrencyTags.Curr_Already_Exist.getMessage(),CurrencyTags.Curr_Already_Exist.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);			
		}
		else {
			Currency output=currencyRepoService.save(currency);			
			if(output!=null) {
				GenricResponse response=new GenricResponse(200,CurrencyTags.Curr_Save_Sucess.getMessage(),CurrencyTags.Curr_Save_Sucess.getTag());
				return  new ResponseEntity<>(response,HttpStatus.OK);
			}
			else {
				GenricResponse response=new GenricResponse(500,CurrencyTags.Curr_Save_Fail.getMessage(),CurrencyTags.Curr_Save_Fail.getTag());
				return  new ResponseEntity<>(response,HttpStatus.OK);
			}
		}
		

	}

	public ResponseEntity<?> viewById(long id){
		log.info("inside view by Id Currency controller");
		Currency output=currencyRepoService.getById(id);

		if(output!=null) {
			if(output.getDate()!=null) {
				String month=utility.convertToMonth(output.getDate());
				output.setMonth(month);
			}
			GenricResponse response=new GenricResponse(200,"","",output);
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,CurrencyTags.Curr_Data_By_Id_Fail.getMessage(),CurrencyTags.Curr_Data_By_Id_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}


	public ResponseEntity<?> updateCurrency(Currency currency){
		log.info("inside update Currency controller");
		log.info("currency data going to update:  "+currency);
		Currency data=currencyRepoService.getById(currency.getId());
		if(data!=null) {
			currency.setCreatedOn(data.getCreatedOn());
			Currency output=currencyRepoService.save(currency);
			if(output!=null) {
				GenricResponse response=new GenricResponse(200,CurrencyTags.Curr_Update_Sucess.getMessage(),CurrencyTags.Curr_Update_Sucess.getTag());
				log.info("exit from  update  Currency controller");
				return  new ResponseEntity<>(response,HttpStatus.OK);
			}
			else {
				log.info("exit from  update  Currency controller");
				GenricResponse response=new GenricResponse(500,CurrencyTags.Curr_Update_Fail.getMessage(),CurrencyTags.Curr_Update_Fail.getTag());
				return  new ResponseEntity<>(response,HttpStatus.OK);
			}
		}
		else {
			GenricResponse response=new GenricResponse(500,CurrencyTags.Curr_Wrong_Id.getMessage(),CurrencyTags.Curr_Wrong_Id.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}


	public Page<Currency> currencyData(CurrencyFilter filter, Integer pageNo, Integer pageSize){
		log.info("inside currency view  controller");
		log.info("portAddressInfo : "+filter);
		Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
		SpecificationBuilder<Currency> specification=new SpecificationBuilder<Currency>(propertiesReader.dialect) ;
		if(Objects.nonNull(filter.getStartDate()) && filter.getStartDate()!="")
			specification.with(new SearchCriteria("createdOn",filter.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filter.getEndDate()) && filter.getEndDate()!="")
			specification.with(new SearchCriteria("createdOn",filter.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filter.getCurrency()))
			specification.with(new SearchCriteria("currency",filter.getCurrency(), SearchOperation.EQUALITY, Datatype.INTEGER));

		if(Objects.nonNull(filter.getSearchString()) && !filter.getSearchString().isEmpty()){
			specification.orSearch(new SearchCriteria("address", filter.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}
		
		return currencyrepo.findAll(specification.build(),pageable);
	}


}
