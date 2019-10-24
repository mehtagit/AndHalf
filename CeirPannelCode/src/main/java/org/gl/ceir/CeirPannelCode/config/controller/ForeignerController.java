package org.gl.ceir.CeirPannelCode.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.gl.ceir.CeirPannelCode.config.Model.ForeignerDetails;
import org.gl.ceir.CeirPannelCode.config.Model.ForeignerImeiDetails;
import org.gl.ceir.CeirPannelCode.config.Model.ForeignerRequest;
import org.gl.ceir.CeirPannelCode.config.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.config.Service.Impl.ForeignerServiceImpl;
import org.gl.ceir.CeirPannelCode.config.Service.Impl.SmsAccountServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class ForeignerController {

	private static final Logger logger = LogManager.getLogger(SmsAccountServiceImpl.class);


	@Autowired
	ForeignerServiceImpl foreignerServiceImpl;

	@ApiOperation(value = "Add foreigner Details ", response = GenricResponse.class)
	@RequestMapping(path = "/foreigner/add", method = RequestMethod.POST)

	public GenricResponse saveForeignerInfo(@RequestBody ForeignerRequest foreignerDetails  ) {

		logger.info("Add foreigner Info Request"+foreignerDetails.toString());

		GenricResponse genricResponse   =foreignerServiceImpl.addForeignerInfo(foreignerDetails);

		return genricResponse;

	}


	@ApiOperation(value = "View  foreigner Imei  Details using passport Number ", response = ForeignerImeiDetails.class)
	@RequestMapping(path = "/foreigner/view", method = RequestMethod.GET)


	public MappingJacksonValue viewImeiDetails(String passportNumber) {

		logger.info("Imei view Request PassportNumber="+passportNumber);

		List<ForeignerImeiDetails> imeiInfo =	foreignerServiceImpl.viewImeidetails(passportNumber);

		MappingJacksonValue mapping = new MappingJacksonValue(imeiInfo);
		return mapping;

	}

	@ApiOperation(value = "Update foreigner Details ", response = GenricResponse.class)
	@RequestMapping(path = "/foreigner/update", method = RequestMethod.POST)

	public GenricResponse updateForeignerInfo(@RequestBody ForeignerRequest foreignerDetails  ) {

		logger.info("update foreigner Info Request"+foreignerDetails.toString());

		return 	foreignerServiceImpl.updateInfo(foreignerDetails);

	}


	@ApiOperation(value = "View passportNumber Record", response = ForeignerRequest.class)
	@RequestMapping(path = "/foreigner/view/record", method = RequestMethod.GET)

	public MappingJacksonValue viewRecord(String passportNumber) {

		logger.info("Get record using passport Number="+passportNumber);

		ForeignerRequest response =	foreignerServiceImpl.getPassportNumberDetails(passportNumber);
		MappingJacksonValue mapping = new MappingJacksonValue(response);


		return mapping;
	}







}
