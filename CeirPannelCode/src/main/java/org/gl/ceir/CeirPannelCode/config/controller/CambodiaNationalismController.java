package org.gl.ceir.CeirPannelCode.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.gl.ceir.CeirPannelCode.config.Model.ForeignerRequest;
import org.gl.ceir.CeirPannelCode.config.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.config.Model.NationalismImeiDetails;
import org.gl.ceir.CeirPannelCode.config.Model.NationlismDetails;
import org.gl.ceir.CeirPannelCode.config.Service.Impl.NationalislmServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class CambodiaNationalismController {


	@Autowired
	NationalislmServiceImpl nationalislmServiceImpl;




	@ApiOperation(value = "Add Nationalism Details ", response = GenricResponse.class)
	@RequestMapping(path = "/nationallism/add", method = RequestMethod.POST)

	public GenricResponse saveNationlismInfo(@RequestBody ForeignerRequest foreignerDetails)

	{
		GenricResponse response = nationalislmServiceImpl.saveNationalismData(foreignerDetails);

		return response;
	}



	@ApiOperation(value = "Update Nationalism Details ", response = GenricResponse.class)
	@RequestMapping(path = "/nationallism/update", method = RequestMethod.POST)

	public GenricResponse updateNationlismInfo(@RequestBody ForeignerRequest foreignerDetails) {

		GenricResponse response = nationalislmServiceImpl.updateNationlismData(foreignerDetails);

		return response;

	}


	@ApiOperation(value = "View  Nationalism Details ", response = NationalismImeiDetails.class)
	@RequestMapping(path = "/nationallism/view", method = RequestMethod.GET)

	public MappingJacksonValue viewPassortDetails(String passportNumber) {

		List<NationalismImeiDetails> response =	nationalislmServiceImpl.getImeiDetails(passportNumber);

		MappingJacksonValue mapping = new MappingJacksonValue(response);

		return mapping;

	}

	@ApiOperation(value = "View Passport UserName Nationalism Details.", response = NationalismImeiDetails.class)
	@RequestMapping(path = "/nationallism/name", method = RequestMethod.GET)

	public MappingJacksonValue viewUserName(String passportNumber) {

		NationlismDetails nationlismDetails	= nationalislmServiceImpl.getUserName(passportNumber);

		MappingJacksonValue mapping = new MappingJacksonValue(nationlismDetails);

		return mapping;
	}



	@ApiOperation(value = "View Passport UserName Nationalism Record.", response = ForeignerRequest.class)
	@RequestMapping(path = "/nationallism/Record", method = RequestMethod.GET)

	public MappingJacksonValue getRecord(String passportNumber) {

		ForeignerRequest foreignerRequest = nationalislmServiceImpl.getRecord(passportNumber);

		MappingJacksonValue mapping = new MappingJacksonValue(foreignerRequest);

		return mapping;

	}


	@ApiOperation(value = "Update TaxPaid Status.", response = GenricResponse.class)
	@RequestMapping(path = "/nationallism/update/status", method = RequestMethod.POST)

	public GenricResponse updateStatus(@RequestBody NationalismImeiDetails nationalismImeiDetails) {


		return 	nationalislmServiceImpl.updateStatus(nationalismImeiDetails);

	}	



}
