package com.gl.ceir.config.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.GsmaValueModel;
import com.gl.ceir.config.model.UsagesValueModel;
import com.gl.ceir.config.repository.GsmaValueRepository;
import com.gl.ceir.config.service.impl.GsmaValueServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class GsmaValueController {

	private static final Logger logger = LogManager.getLogger(GsmaValueController.class);

	@Autowired
	GsmaValueServiceImpl GsmaValueServiceImpl;

	@ApiOperation(value = "View All list of Values of Gsma", response = GsmaValueModel.class)
	@PostMapping(path = "gsma/GsmaValues")

	public MappingJacksonValue getAllValues(Long imei, Long msisdn, String identifierType) {

		MappingJacksonValue mapping = null;
		logger.info("Request TO view TO all imei of gsma=" + imei);
		logger.info("Request TO view TO all msisdn of gsma=" + msisdn);
		logger.info("Request TO view TO all identifierType =" + identifierType);

		Long ismi = null;
		UsagesValueModel usagesValueModel = null;
		if (imei == null) {
			usagesValueModel = GsmaValueServiceImpl.getimeiValbymsisdn(msisdn);
			if (usagesValueModel == null) {
				logger.info("  IMEI IS NOT FOUND");
				imei =(long) 000000000;
				ismi =  (long) 00;				 	
			} else {
				imei = usagesValueModel.getImei();
				ismi =  usagesValueModel.getImsi() == null ?(long) 00 : usagesValueModel.getImsi() ;		
			}

		} else {
			usagesValueModel = GsmaValueServiceImpl.getimeiValbyImei( imei);
			ismi =  usagesValueModel== null ?(long) 00: usagesValueModel.getImsi();
			msisdn =  usagesValueModel == null ? (long) 00 : usagesValueModel.getMsisdn();	
		}



		logger.info("  imei ISS ....." + imei);
		logger.info("  imsi ISS ....." + ismi);
		logger.info("  MSISDN ISS ....." + msisdn);
		int tac = 00;
		if(imei != 0) {
			tac = Integer.parseInt(imei.toString().trim().substring(0, 8));
		}

		logger.info("  tac  ....." + tac);

		GsmaValueModel getvals = GsmaValueServiceImpl.getAll(tac);


		System.out.println("rsult at cntrl " + getvals);


		if (getvals == null) {
			GsmaValueModel getvals1 = new GsmaValueModel();
			System.out.println("For Nulled");
			getvals1.setModelName("NA");
			getvals1.setBandName("NA");
			getvals1.setEquipmentType("NA");
			getvals1.setOperatingSystem("NA");
			getvals1.setImei(imei);
			getvals1.setImsi(ismi);
			getvals1.setMsisdn(msisdn);
			getvals1.setIdentifierType(identifierType);
			System.out.println("\\ Nulled  NA  FOR 66666");

			mapping = new MappingJacksonValue(getvals1);
		} else {
		
			if(usagesValueModel == null ) {
System.out.println("values null....");
				getvals.setImei((long) 00);
				getvals.setMsisdn( (long) 00 );
				getvals.setImsi( (long) 00);
			}else {
				getvals.setImei( usagesValueModel.getImei());
				getvals.setMsisdn(usagesValueModel.getMsisdn()  );
				getvals.setImsi( usagesValueModel.getImsi());
				}
			getvals.setIdentifierType(identifierType);
			mapping = new MappingJacksonValue(getvals);
		}

		logger.info("Response of View =" + mapping);
		return mapping;
	}

}

















//
//logger.info("Request TO view TO all identifierType =" + identifierType);
//
//String imeiVal = "";
//String iemi = "";
//String ismi = "";
//String msdn = "";
//UsagesValueModel usagesValueModel = null;
//if (imei == null) {
//	usagesValueModel = GsmaValueServiceImpl.getimeiValbymsisdn(msisdn);
//	if (usagesValueModel == null) {
//		logger.info("  IMEI IS NOT FOUND");
//		imeiVal = "0000000000000";
//		 iemi =  "00" ;
//		 ismi =  "00";
//		 msdn =  msisdn.toString();	
//	} else {
//		imeiVal = usagesValueModel.getImei().toString();
//		 ismi =  usagesValueModel.getImsi() == null ?"00" : usagesValueModel.getImsi().toString() ;
//		 msdn =  usagesValueModel.getMsisdn() == null ? "00" : usagesValueModel.getMsisdn().toString() ;	
//		logger.info("  IMEI IS  FOUND  WITH " + imeiVal);
//		msdn =  msisdn.toString();	
//		
//	}
//
//} else {
//	imeiVal = imei.toString();
//	usagesValueModel = GsmaValueServiceImpl.getimeiValbyImei(imei);
//	 iemi =  imeiVal;
////	 iemi =  usagesValueModel == null ?  "00" :usagesValueModel.getImei().toString() ;
//	 ismi =  usagesValueModel == null ?"00" : usagesValueModel.getImsi().toString() ;
//	 msdn =  usagesValueModel == null ? "00" : usagesValueModel.getMsisdn().toString() ;	
//}
//
//
//
//logger.info("  imeiVal ISS ....." + imeiVal);
//int tac = Integer.parseInt(imeiVal.trim().substring(0, 8));
//logger.info("  tac  ....." + tac);
//GsmaValueModel getvals = GsmaValueServiceImpl.getAll(tac);
//
//System.out.println("rsult at cntrl " + getvals);
//
//if (getvals == null) {
//	GsmaValueModel getvals1 = new GsmaValueModel();
//	System.out.println("For Nulled");
//	getvals1.setModelName("NA");
//	getvals1.setBandName("NA");
//	getvals1.setEquipmentType("NA");
//	getvals1.setOperatingSystem("NA");
//	getvals1.setImei(iemi);
//	getvals1.setImsi(ismi);
//	getvals1.setMsisdn(msdn);
//	System.out.println("\\ Nulled  NA  FOR 66666");
//	
//	mapping = new MappingJacksonValue(getvals1);
//} else {
//	getvals.setImei(usagesValueModel.getImei().toString());
//	getvals.setMsisdn(usagesValueModel.getMsisdn().toString());
//	getvals.setImsi(usagesValueModel.getImsi().toString());
//	mapping = new MappingJacksonValue(getvals);
//}




