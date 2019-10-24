package org.gl.ceir.CeirPannelCode.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.gl.ceir.CeirPannelCode.config.Configuration.FileStorageProperties;
import org.gl.ceir.CeirPannelCode.config.Model.ListFileDetails;
import org.gl.ceir.CeirPannelCode.config.Service.Impl.ListFileDetailsImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class OperatorController {

	@Autowired
	ListFileDetailsImpl  listFileDetailsImpl;

	@Autowired
	FileStorageProperties fileStorageProperties;

	private static final Logger logger = LogManager.getLogger(OperatorController.class);



	@ApiOperation(value = "View blackList and Grey List FileDetails.", response = ListFileDetails.class)
	@RequestMapping(path = "/operator/view", method = RequestMethod.GET)

	public MappingJacksonValue getOperatorData(String listType) {

		logger.info("List View Details Request ="+listType);		
		List<ListFileDetails> fileDetails =	listFileDetailsImpl.getByListType(listType);

		MappingJacksonValue mapping = new MappingJacksonValue(fileDetails);

		return mapping;
	}



	@ApiOperation(value = "Download operator File.", response = String.class)
	@RequestMapping(path = "/operator/downloadFile", method = RequestMethod.GET)

	public String downloadOperatorFile(String fileName) {

		logger.info("Operator File DownloadRequest FileName="+fileName);

		String directoryPath =fileStorageProperties.getDownloadDir();

		directoryPath=directoryPath+"operator/"+fileName;

		return directoryPath;
	}
}
