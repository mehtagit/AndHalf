package com.ceir.CeirCode.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.filtermodel.AlertDbFilter;
import com.ceir.CeirCode.filtermodel.SlaFilter;
import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.SlaReport;
import com.ceir.CeirCode.model.StakeholderFeature;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.service.AlertDbService;
import com.ceir.CeirCode.service.FeatureService;
import com.ceir.CeirCode.service.SlaService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/sla")
public class SlaReportController {

	

	@Autowired
	SlaService slaService;
	
	@Autowired
	FeatureService featureService;
	
	@ApiOperation(value = "sla report  data.", response = AlertDb.class)
	@PostMapping("/viewAll") 
	public MappingJacksonValue viewRecord(@RequestBody SlaFilter filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file){
		MappingJacksonValue mapping = null;
		if( file == 0) {
			Page<SlaReport> slaReponse  = slaService.viewAllSlaData(filterRequest, pageNo, pageSize);
			if(Objects.nonNull(slaReponse)) {
				List<StakeholderFeature> featureData=featureService.featureAllData();
				for(SlaReport sla:slaReponse.getContent()) {
                         if(Objects.nonNull(featureData)) {
                        	for(StakeholderFeature feature:featureData) {
                        		if(feature.getId()==sla.getFeatureId())
                        		sla.setFeatureName(feature.getName());	
                        	}
                         }
						if(Objects.nonNull(sla.getUserSlaReport())) {
							sla.setUsertype(sla.getUserSlaReport().getUsertype().getUsertypeName());
					}
				}
			}
			mapping = new MappingJacksonValue(slaReponse);
			
		}else {
			FileDetails fileDetails = slaService.getSlaInFile(filterRequest);
			mapping = new MappingJacksonValue(fileDetails);
		}
		return mapping;
	}
	
}
