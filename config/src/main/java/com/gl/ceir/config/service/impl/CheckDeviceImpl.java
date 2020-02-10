package com.gl.ceir.config.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.CheckDevice;
import com.gl.ceir.config.model.CheckDeviceReponse;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.repository.AuditTrailRepository;
@Service
public class CheckDeviceImpl {

	@Autowired
	AuditTrailRepository auditTrailRepository;
	
	public GenricResponse checkDevices( CheckDevice checkDevice) {
		   CheckDeviceReponse checkDeviceResponse=new CheckDeviceReponse();
			auditTrailRepository.save(new AuditTrail(0, null, 0L, "", 0L,"Check Device", "Check Device", ""));
	        checkDeviceResponse.setBrandName("Redmi");
	        checkDeviceResponse.setModelName("Ac11");
	        checkDeviceResponse.setTacNumber("476433753");
	        Object data=checkDeviceResponse;
	        GenricResponse response=new GenricResponse(200,"The IMEI number is valid","",data);
	        return response;
	}
}
