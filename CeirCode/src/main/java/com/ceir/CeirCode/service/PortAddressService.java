package com.ceir.CeirCode.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.repoService.PortAddressRepoService;
import com.ceir.CeirCode.util.HttpResponse;

@Service
public class PortAddressService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PortAddressRepoService portService;
	
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
	
	
}
