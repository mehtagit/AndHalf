package com.ceir.CeirCode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.filtermodel.UserFeatureFilter;
import com.ceir.CeirCode.filtermodel.UsertypeFilter;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.service.UserFeatureService;
import com.ceir.CeirCode.service.UserTypeService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserFeatureController {

	@Autowired
	UserFeatureService userFeatureService;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	
	@ApiOperation(value = "user type  data.", response = Usertype.class)
	@PostMapping("/usertypeData") 
	public MappingJacksonValue viewRecord(@RequestBody UserFeatureFilter filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
		MappingJacksonValue mapping = null;
			Page<UserToStakehoderfeatureMapping> userTypeData  =userFeatureService.viewAllUSerFeatures(filterRequest, pageNo, pageSize);
			List<SystemConfigListDb> statusList=systemConfigRepo.getByTag("UserType_Status");
			if(userTypeData!=null) {
			for(UserToStakehoderfeatureMapping feature:userTypeData.getContent()) {
				for(SystemConfigListDb status:statusList) {
					if(feature.getPeriod()==status.getValue()) {
						feature.setStatusInterp(status.getInterp());
					}
				}
			}
			}
			mapping = new MappingJacksonValue(userTypeData);
		return mapping;
	}
}
