package com.ceir.CeirCode.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.SpecificationBuilder.SpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.model.PeriodValidate;
import com.ceir.CeirCode.model.ShFeature;
import com.ceir.CeirCode.model.StakeholderFeature;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;
import com.ceir.CeirCode.model.Userrole;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UserRoleRepo;
import com.ceir.CeirCode.repo.UserToStakehoderfeatureMappingRepo;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.repoImpl.SystemConfigDbRepoImpl;
import com.ceir.CeirCode.repoImpl.UserFeatureRepoImpl;
import com.ceir.CeirCode.util.HttpResponse;
import com.ceir.CeirCode.util.Utility;
@Service
public class FeatureService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserRoleRepo userRoleRepo;
	@Autowired
	UserToStakehoderfeatureMappingRepo userFeatureRepo;

	@Autowired
	SystemConfigDbRepoImpl systemConfigurationDbRepoImpl;

	@Autowired
	Utility utility;

	@Autowired
	PropertiesReaders propertiesReader;

	@Autowired
	UserFeatureRepoImpl userFeatureRepoImpl;
	
	public ResponseEntity<?> featureData(Integer userId){
		try {  
			User userData=userRepo.findById(userId);
			List<StakeholderFeature> featureList=new ArrayList<StakeholderFeature>();
			Usertype usertypeData=userData.getUsertype();
			log.info("usertypeData : "+usertypeData);
			log.info("mappping data:  "+usertypeData.getUserTofeatureMapping());
			SystemConfigurationDb systemConfigData=systemConfigurationDbRepoImpl.getDataByTag("GRACE_PERIOD_END_DATE");
			String period=new String();
			List<UserToStakehoderfeatureMapping> data=new ArrayList<UserToStakehoderfeatureMapping>();
			if(systemConfigData!=null) {
				period=currentPeriod(systemConfigData);
				data=userFeatureRepo.findByUserTypeFeature_IdAndPeriodOrPeriodAndUserTypeFeature_IdOrderByCreatedOnAsc(usertypeData.getId(),"BOTH",period,usertypeData.getId());
			}
			else {
				data=userFeatureRepo.findByUserTypeFeature_IdOrderByCreatedOnAsc(usertypeData.getId());			
			}

			if(data!=null) {
				for(UserToStakehoderfeatureMapping userToFeatureData:data) {
					StakeholderFeature feature=new StakeholderFeature(); 
					feature.setId(userToFeatureData.getStakeholderFeature().getId()); 
					feature.setName(userToFeatureData.getStakeholderFeature().getName());
					feature.setLink(userToFeatureData.getStakeholderFeature().getLink());
					feature.setLogo(userToFeatureData.getStakeholderFeature().getLogo());
					feature.setCategory(userToFeatureData.getStakeholderFeature().getCategory());
					featureList.add(feature); 
				}   
			}
			log.info("feature data: "+featureList);
			if(!featureList.isEmpty()) {
				return new ResponseEntity<>(featureList,HttpStatus.OK);
			}
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(204);    
				response.setResponse("Feature Data not Found");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}   
		catch(Exception e) {
			e.printStackTrace();
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409); 
			response.setResponse("Oops something wrong happened");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}

	public String currentPeriod(SystemConfigurationDb systemConfigData) {
		Date currentDate=utility.currentOnlyDate();
		log.info("currentDate: "+currentDate);
		String period=new String();
		try {
			Date GracePeriodEndDate=utility.stringToDate(systemConfigData.getValue());
			log.info("GracePeriodEndDate: "+GracePeriodEndDate);
			if(currentDate.after(GracePeriodEndDate)) {
				period="POST_GRACE";
			}
			else {
				period="GRACE";
			}
			return period;
		}
		catch(Exception e) {
			log.info(e.toString());
			return null;
		}
	}
	public HttpResponse periodValidation(PeriodValidate periodValidate) {
		String currentPeriod=new String();
		SystemConfigurationDb systemConfigData=systemConfigurationDbRepoImpl.getDataByTag("GRACE_PERIOD_END_DATE");
		if(systemConfigData!=null) {
			currentPeriod=currentPeriod(systemConfigData);			
		}
		UserToStakehoderfeatureMapping userFeature=userFeatureRepoImpl.getByUsertypeIdAndFeatureId(periodValidate);
		if(userFeature!=null) 
		{
			if("Both".equalsIgnoreCase(userFeature.getPeriod())) 
			{
				return new HttpResponse("this functinality is supported ",200);									
			}
			else 
			{
				if(currentPeriod.equals(userFeature.getPeriod())) {
					return new HttpResponse("this functinality is supported ",200);									
				}
				else {
					return new HttpResponse("this functinality is not supported now",200);									
				}
			}
		}
		else 
		{
			return new HttpResponse("Oops something wrong happened",409);
		}

	}
}