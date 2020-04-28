package com.ceir.CeirCode.service;

import java.util.List;
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
import com.ceir.CeirCode.SpecificationBuilder.GenericSpecificationBuilder;
import com.ceir.CeirCode.SpecificationBuilder.SpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.filtermodel.PortAddressFilter;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.repo.PortAddressRepo;
import com.ceir.CeirCode.repoService.PortAddressRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.SystemConfigurationDbRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.tags.PortAddsTags;
import com.ceir.CeirCode.util.HttpResponse;

@Service
public class PortAddressService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PortAddressRepoService portService;
	
	@Autowired
	PropertiesReaders propertiesReader;
	
	@Autowired
	PortAddressRepo portAddressRepo;
	
	@Autowired
	SystemConfigurationDbRepoService systemConfigDbRepoService;

	
	
	public ResponseEntity<?> getDataByPort(Integer port){
		log.info("inside getDataByPort controller");

		List<PortAddress> portAddressList=portService.getByPort(port);
		if(portAddressList!=null) {
			log.info("exit from getDataByPort controller");
			return  new ResponseEntity<>(portAddressList,HttpStatus.OK);

		}
		else {
			HttpResponse response=new HttpResponse("Address data failed to find by port",204);
			log.info("exit from getDataByPort controller");
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	public ResponseEntity<?> saveAddressPort(PortAddress portAddress){
		log.info("inside save address port controller");
		PortAddress output=portService.save(portAddress);
		if(output!=null) {
			GenricResponse response=new GenricResponse(200,PortAddsTags.PAdd_Save_Sucess.getMessage(),PortAddsTags.PAdd_Save_Sucess.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Save_Fail.getMessage(),PortAddsTags.PAdd_Save_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}
	
	public ResponseEntity<?> viewById(long id){
		log.info("inside view by address port controller");
		PortAddress output=portService.getById(id);
		if(output!=null) {
			List<SystemConfigListDb> asTypeList=systemConfigDbRepoService.getDataByTag("CUSTOMS_PORT");	
					for(SystemConfigListDb asType:asTypeList) {
						Integer value=asType.getValue();
				if(output.getPort()==value) {
					output.setPortInterp(asType.getInterp());
				}
				
					}
					GenricResponse response=new GenricResponse(200,"","",output);		
		
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Data_By_Id_Fail.getMessage(),PortAddsTags.PAdd_Data_By_Id_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}
	
	
	public ResponseEntity<?> updateAddressPort(PortAddress portAddress){
		log.info("inside update address port controller");
		log.info("address port data "+portAddress);
		PortAddress data=portService.getById(portAddress.getId());
		if(data!=null) {
			portAddress.setCreatedOn(data.getCreatedOn());
		PortAddress output=portService.save(portAddress);
		
		if(output!=null) {
			GenricResponse response=new GenricResponse(200,PortAddsTags.PAdd_Update_Sucess.getMessage(),PortAddsTags.PAdd_Save_Sucess.getTag());
			log.info("exit from  update address port controller");
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Update_Fail.getMessage(),PortAddsTags.PAdd_Save_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Wrong_PORT_ID.getMessage(),PortAddsTags.PAdd_Wrong_PORT_ID.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);		
		}

	}
	
	public ResponseEntity<?> deleteAddressPort(long id){
		log.info("inside save address port controller");
		int output=portService.deleteById(id);
		if(output==1) {
			GenricResponse response=new GenricResponse(200,PortAddsTags.PAdd_Del_Sucess.getMessage(),PortAddsTags.PAdd_Save_Sucess.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Del_Fail.getMessage(),PortAddsTags.PAdd_Save_Fail.getTag());
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}
	
	
	public Page<PortAddress> portAddressInfo(PortAddressFilter filter, Integer pageNo, Integer pageSize){
		log.info("inside portAddress view  controller");
		log.info("portAddressInfo : "+filter);
		Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
		GenericSpecificationBuilder<PortAddress> specification=new GenericSpecificationBuilder<PortAddress>(propertiesReader.dialect) ;
		
		if(Objects.nonNull(filter.getPort()))
			specification.with(new SearchCriteria("port",filter.getPort(), SearchOperation.EQUALITY, Datatype.INTEGER));

		
		if(Objects.nonNull(filter.getStartDate()) && filter.getStartDate()!="")
			specification.with(new SearchCriteria("createdOn",filter.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filter.getEndDate()) && filter.getEndDate()!="")
			specification.with(new SearchCriteria("createdOn",filter.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
		if(Objects.nonNull(filter.getSearchString()) && !filter.getSearchString().isEmpty()){
			specification.orSearch(new SearchCriteria("address", filter.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}


		return portAddressRepo.findAll(specification.build(),pageable);
		//return userProfileRepo.findAll(specification.build());

	}
	
	
	


}
