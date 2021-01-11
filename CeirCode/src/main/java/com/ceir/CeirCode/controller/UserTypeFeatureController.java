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

import com.ceir.CeirCode.filtermodel.UserTypeFeatureFilter;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.othermodel.ChangePeriod;
import com.ceir.CeirCode.othermodel.ChangeUsertypeStatus;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.service.UserTypeFeatureService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserTypeFeatureController {

	@Autowired
	UserTypeFeatureService userTypeFeatureService;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	
	@ApiOperation(value = "user type feature  data.", response = UserToStakehoderfeatureMapping.class)
	@PostMapping("/userTypeFeatureData") 
	public MappingJacksonValue viewRecord(@RequestBody UserTypeFeatureFilter filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file){
		MappingJacksonValue mapping = null;
		if (file == 0) {
			Page<UserToStakehoderfeatureMapping> userTypeFeatureData  =userTypeFeatureService.viewAllUserTypeFeatures(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(userTypeFeatureData);
			if(userTypeFeatureData!=null) {
				List<SystemConfigListDb> periodList=systemConfigRepo.getByTag("Period");
				for(UserToStakehoderfeatureMapping feature:userTypeFeatureData.getContent()) {
					feature.setUsertypeInterp(feature.getUserTypeFeature().getUsertypeName());
					feature.setFeatureInterp(feature.getStakeholderFeature().getName());
					for(SystemConfigListDb data:periodList) {
						Integer value=data.getValue();
						if(feature.getPeriod()==value) {
							feature.setPeriodInterp(data.getInterp());
						}
					}
				}
				}
			mapping = new MappingJacksonValue(userTypeFeatureData);
		}else {
			FileDetails fileDetails = userTypeFeatureService.getFile(filterRequest);
			mapping = new MappingJacksonValue(fileDetails);
		}
			
		return mapping;
	}

	@ApiOperation(value = "change usertype feature period", response = HttpResponse.class)
	@PostMapping("/updatePeriod")
	public ResponseEntity<?> updateUserTypeFeaturePeriod(@RequestBody ChangePeriod period){
		return userTypeFeatureService.changePeriod(period);  
	}   
}