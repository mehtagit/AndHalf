package org.gl.ceir.CeirPannelCode.config.controller;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.gl.ceir.CeirPannelCode.config.Model.ForeignerDetails;
import org.gl.ceir.CeirPannelCode.config.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.config.Model.ImmegreationFileDetails;
import org.gl.ceir.CeirPannelCode.config.Model.ImmegreationImeiDetails;
import org.gl.ceir.CeirPannelCode.config.Service.Impl.ForeignerServiceImpl;
import org.gl.ceir.CeirPannelCode.config.Service.Impl.ImmegreationServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class ImmegreationController {



	@Autowired
	ForeignerServiceImpl foreignerServiceImpl;

	@Autowired
	ImmegreationServiceImpl immegreationServiceImpl;


	@ApiOperation(value = "View All foreigner Details ", response = ForeignerDetails.class)
	@RequestMapping(path = "/immegreation/view", method = RequestMethod.GET)
	public MappingJacksonValue viewAllInfo() {

		List<ForeignerDetails>  response =	foreignerServiceImpl.fetchallData();

		MappingJacksonValue mapping = new MappingJacksonValue(response);

		return mapping;
	}


	@ApiOperation(value = "Update imei Action Status ", response = GenricResponse.class)
	@RequestMapping(path = "/immegreation/imeiAction", method = RequestMethod.POST)

	public GenricResponse updateActionStatus(@RequestBody ImmegreationImeiDetails immegreationImeiDetails)
	{
		GenricResponse response = 	foreignerServiceImpl.updateImeiActionInfo(immegreationImeiDetails);
		return response;
	}


	@ApiOperation(value = "Update File Action Status ", response = GenricResponse.class)
	@RequestMapping(path = "/immegreation/fileAction", method = RequestMethod.POST)

	public GenricResponse updatefileActionStatus(@RequestParam("file") MultipartFile file, String blockingType, String blockingTime ,String fileType)
	{

		ImmegreationFileDetails ImmegreationFileDetails = new ImmegreationFileDetails(); 
		ImmegreationFileDetails.setCreatedOn(new Date());
		ImmegreationFileDetails.setUpdatedOn(new Date());
		ImmegreationFileDetails.setFileStatus("INIT");
		ImmegreationFileDetails.setFileName(file.getOriginalFilename());
		ImmegreationFileDetails.setBlockingType(blockingType);
		ImmegreationFileDetails.setBlockingTime(blockingTime);
		ImmegreationFileDetails.setFileType(fileType);

		return 	immegreationServiceImpl.uploadFileStatus(file,ImmegreationFileDetails);

	}



}
