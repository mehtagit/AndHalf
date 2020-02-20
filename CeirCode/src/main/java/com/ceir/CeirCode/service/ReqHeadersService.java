package com.ceir.CeirCode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.util.HttpResponse;

@Service
public class ReqHeadersService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ReqHeaderRepoService headerService;
	

	
	public ResponseEntity<?> saveRequestHeaders(RequestHeaders requestHeaders){
      log.info("inside request headers controller");
	  log.info("Request headers data given: "+requestHeaders);
      RequestHeaders output=headerService.saveRequestHeader(requestHeaders);
      if(output!=null) {
             HttpResponse response=new HttpResponse("Request Headers data sucessfully save",200);
              return  new ResponseEntity<>(response,HttpStatus.OK);
      }
      else {
          HttpResponse response=new HttpResponse("Request Headers data fails to save",409);
          return  new ResponseEntity<>(response,HttpStatus.OK);
      }
	}
	
}
