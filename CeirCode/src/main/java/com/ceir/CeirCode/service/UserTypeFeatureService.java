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
import com.ceir.CeirCode.SpecificationBuilder.GenericSpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.filtermodel.UserTypeFeatureFilter;
import com.ceir.CeirCode.filtermodel.UsertypeFilter;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.othermodel.ChangePeriod;
import com.ceir.CeirCode.othermodel.ChangeUsertypeStatus;
import com.ceir.CeirCode.repo.FeatureRepo;
import com.ceir.CeirCode.repo.UserToStakehoderfeatureMappingRepo;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.response.tags.UserTypeFeatureTags;
import com.ceir.CeirCode.response.tags.UsertypeTags;
import com.ceir.CeirCode.util.HttpResponse;

@Service
public class UserTypeFeatureService {


	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PropertiesReaders propertiesReader;
	
	@Autowired
	UsertypeRepo userTypeRepo;
	
	@Autowired
	UserToStakehoderfeatureMappingRepo userTypeFeatureRepo;
	
	@Autowired
	ReqHeaderRepoService headerService;

	@Autowired
	UserService userService;

	
	public Page<UserToStakehoderfeatureMapping>  viewAllUserTypeFeatures(UserTypeFeatureFilter filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
//			RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
//			headerService.saveRequestHeader(header);
			userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
					filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.User_feature_mapping,SubFeatures.VIEW_ALL,filterRequest.getFeatureId());

			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
			GenericSpecificationBuilder<UserToStakehoderfeatureMapping> uPSB = new GenericSpecificationBuilder<UserToStakehoderfeatureMapping>(propertiesReader.dialect);	
			if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
				uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
				uPSB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getFeature()) && filterRequest.getFeature()!=-1)
				uPSB.with(new SearchCriteria("stakeholderFeature",filterRequest.getFeature(), SearchOperation.EQUALITY, Datatype.INTEGER));
			
			if(Objects.nonNull(filterRequest.getUsertypeId()) && filterRequest.getUsertypeId()!=-1)
				uPSB.with(new SearchCriteria("userTypeFeature",filterRequest.getUsertypeId(), SearchOperation.EQUALITY, Datatype.INTEGER));

			if(Objects.nonNull(filterRequest.getPeriod()) && filterRequest.getPeriod()!=-1)
				uPSB.with(new SearchCriteria("period",filterRequest.getPeriod(), SearchOperation.EQUALITY, Datatype.INTEGER));

			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
				uPSB.orSearch(new SearchCriteria("stakeholderFeature-name", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			    uPSB.orSearchUsertypeMapToFeature(new SearchCriteria("usertypeName", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
//			    uPSB.orSearchFeature(new SearchCriteria("name", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				}
			
			return  userTypeFeatureRepo.findAll(uPSB.build(),pageable);

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;

		}
	}
	 
	public ResponseEntity<?> changePeriod(ChangePeriod userPeriod){
		log.info("inside  change userPeriod   controller");  
		log.info(" userPeriod data:  "+userPeriod);      
		log.info("get userTypeFeature  data by  id below"); 
		UserToStakehoderfeatureMapping featureMapping=new UserToStakehoderfeatureMapping();
//		RequestHeaders header=new RequestHeaders(userPeriod.getUserAgent(),userPeriod.getPublicIp(),userPeriod.getUsername());
//		headerService.saveRequestHeader(header);
		userService.saveUserTrail(userPeriod.getUserId(),userPeriod.getUsername(),
				userPeriod.getUserType(),userPeriod.getUserTypeId(),Features.User_feature_mapping,SubFeatures.UPDATE,userPeriod.getFeatureId());

		try {
			featureMapping=userTypeFeatureRepo.findById(userPeriod.getDataId());		
		}
		catch(Exception e) {
			log.info(e.getMessage());
			log.info(e.toString());
		}

		if(featureMapping!=null) {
			featureMapping.setPeriod(userPeriod.getPeriod());
			UserToStakehoderfeatureMapping output=userTypeFeatureRepo.save(featureMapping); 
			if(output!=null) {
				HttpResponse response=new HttpResponse(UserTypeFeatureTags.UTFPeriod_Update_Success.getMessage(),
						200,UserTypeFeatureTags.UTFPeriod_Update_Success.getTag());
				log.info("response send:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse(UserTypeFeatureTags.UTFPeriod_Update_Fail.getMessage(),
						500,UserTypeFeatureTags.UTFPeriod_Update_Fail.getTag());
				log.info("response send"+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			} 
		}    
		else { 
			HttpResponse response=new HttpResponse(UserTypeFeatureTags.Wrong_userTypeFeatureId.getMessage(),
					409,UserTypeFeatureTags.Wrong_userTypeFeatureId.getTag());
			log.info("response send"+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}
}
