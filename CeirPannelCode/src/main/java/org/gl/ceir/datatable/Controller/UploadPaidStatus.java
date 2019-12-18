package org.gl.ceir.datatable.Controller;

import org.gl.ceir.CeirPannelCode.Feignclient.UploadPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UploadPaidStatus {

	@Autowired
	UploadPaidStatusFeignClient uploadPaidStatusFeignClient;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/paid-status/{nid}")
	public ResponseEntity<?> respone(@PathVariable("nid") String nid) {
	GenricResponse response = uploadPaidStatusFeignClient.respone(nid);
	return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
