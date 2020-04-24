  package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gl.ceir.config.model.CheckImeiValuesEntity ;
 
import com.gl.ceir.config.model.CheckImeiMess;     
import com.gl.ceir.config.service.impl.CheckImeiServiceImpl;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class CheckImeiController {
	private static final Logger logger = LogManager.getLogger(GsmaValueController.class);

	@Autowired
	CheckImeiServiceImpl    checkImeiServiceImpl;

	@ApiOperation(value = "Validate Imei", response = CheckImeiMess.class)
	@PostMapping(path = "cc/CheckImeI")
	public MappingJacksonValue CheckImeiValues(@RequestBody CheckImeiValuesEntity checkImeiValuesEntity  ) {

              String user_type  = checkImeiValuesEntity.getUser_type(); 
              String feature  = checkImeiValuesEntity.getFeature().trim().replaceAll(" ", "");
              Long imei = checkImeiValuesEntity.getImei();
              Long imei_type  = checkImeiValuesEntity.getImei_type();
            
            
		CheckImeiMess cImsg = new CheckImeiMess();
		MappingJacksonValue mapping = null;
		String rulePass =  	checkImeiServiceImpl.getResult( user_type,feature ,  imei,  imei_type);
		if(rulePass.equalsIgnoreCase("true")) {
			cImsg.setImeiError("NA");
			cImsg.setImeiOutput("Pass");
		}else {
			cImsg.setImeiError(rulePass);
			 cImsg.setImeiOutput("Fail");	
		}
		mapping = new MappingJacksonValue(cImsg);
		logger.info("Response of View =" + mapping);
		return mapping;

	}

}