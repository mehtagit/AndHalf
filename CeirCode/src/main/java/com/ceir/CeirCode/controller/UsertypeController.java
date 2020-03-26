package com.ceir.CeirCode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.filtermodel.UsertypeFilter;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.UserStatusRequest;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.othermodel.ChangeUsertypeStatus;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.service.UserTypeService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class UsertypeController {

	@Autowired
	UserTypeService usertypeService;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	
	@ApiOperation(value = "user type  data.", response = Usertype.class)
	@PostMapping("/usertypeData") 
	public MappingJacksonValue viewRecord(@RequestBody UsertypeFilter filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
		MappingJacksonValue mapping = null;
			Page<Usertype> userTypeData  =usertypeService.viewAllUserytypes(filterRequest, pageNo, pageSize);
			List<SystemConfigListDb> statusList=systemConfigRepo.getByTag("UserType_Status");
			if(userTypeData!=null) {
			for(Usertype usertype:userTypeData.getContent()) {
				for(SystemConfigListDb status:statusList) {
					if(usertype.getStatus()==status.getValue()) {
						usertype.setStatusInterp(status.getInterp());
					}
				}
			}
			}
			mapping = new MappingJacksonValue(userTypeData);
		return mapping;
	}
	
	@ApiOperation(value = "change usertype status", response = HttpResponse.class)
	@PostMapping("/updateUserTypeStatus")
	public ResponseEntity<?> updateUserTypeStatus(@RequestBody ChangeUsertypeStatus userTypeStatus){
		return usertypeService.changeUserTypeStatus(userTypeStatus);  
	}                                                                                                                                     
	
}
