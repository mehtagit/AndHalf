package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.GrievanceGenricResponse;
import com.gl.ceir.config.model.TypeApproveFilter;
import com.gl.ceir.config.model.TypeApprovedDb;
import com.gl.ceir.config.service.impl.TypeApproveServiceImpl;
import com.gl.ceir.config.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/TypeApproved")
public class TypeApprovedController {

	private static final Logger log = LogManager.getLogger(TypeApprovedController.class);
	
	@Autowired
	TypeApproveServiceImpl  typeApproveService;
	
	@ApiOperation(value = "type approve data", response = TypeApprovedDb.class)
	@PostMapping("/view") 
	public MappingJacksonValue viewTypeApproveData(@RequestBody TypeApproveFilter filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file){
		MappingJacksonValue mapping=null;
		if( file == 0) {
			Page<TypeApprovedDb> typeApprovedData  = typeApproveService.viewTypeApprovdeData(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(typeApprovedData);
		}
		else { 
			FileDetails fileDetails = typeApproveService.getFilterTACInFile(filterRequest, pageNo,pageSize); 
			mapping = new MappingJacksonValue(fileDetails); 
		}
		 
		 
		 return mapping;
	}
	
	@PostMapping("/add")
	@ApiOperation( value = "Add type approve data",response =HttpResponse.class)
	public GrievanceGenricResponse addTypeApproveData(@RequestBody TypeApprovedDb typeApprovedDb) {
		log.info("Going to add new TypeApprovedDb["+typeApprovedDb+"]");
		return typeApproveService.saveTypeApprove(typeApprovedDb);
	}

	@ApiOperation( value = "View Approve data by Id",response =HttpResponse.class)
	@PostMapping("viewById/{id}")
	public ResponseEntity<?> addTypeApproveData(@PathVariable long id) {
		return typeApproveService.viewTypeApproveById(id);
	}
	
	@PostMapping("/delete")
	@ApiOperation( value = "delete type approve data",response =HttpResponse.class)
	public HttpResponse deleteTypeApproveData(long id) {
		return typeApproveService.deleteTypeApprove(id);  
	}
	
	@PostMapping("/update")
	@ApiOperation( value = "update type approve data",response =HttpResponse.class)
	public GrievanceGenricResponse  updateTypeApproveData(@RequestBody TypeApprovedDb typeApprovedDb) {
		log.info("Going to update TypeApprovedDb["+typeApprovedDb+"]");
		return typeApproveService.updateTypeApprove(typeApprovedDb);
	}
	
	@ApiOperation(value = "Download TAC File.", response = FileDetails.class)
	@RequestMapping(path = "/downloadFile", method = RequestMethod.GET)
	public MappingJacksonValue downloadOperatorFile(@RequestParam(value = "fileName") String fileName) {

		log.info("Operator File DownloadRequest FileName="+fileName);

		FileDetails fileDetails = typeApproveService.getFile(fileName);
		
		return new MappingJacksonValue(fileDetails);
	}
	
}
