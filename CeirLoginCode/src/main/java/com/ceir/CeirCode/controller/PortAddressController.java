package com.ceir.CeirCode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.filtermodel.PortAddressFilter;
import com.ceir.CeirCode.model.AllRequest;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.ModemInfoTable;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.StateMgmtDb;
import com.ceir.CeirCode.model.StatesInterpretationDb;
import com.ceir.CeirCode.model.WebActionTbl;
import com.ceir.CeirCode.repoService.SystemConfigurationDbRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.service.PortAddressService;
import com.ceir.CeirCode.service.StateMgmtServiceImpl;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/portAddress")
public class PortAddressController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	PortAddressService portAddressService;
	
	@Autowired
	SystemConfigurationDbRepoService systemConfigDbRepoService;
	
	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;
	
	@ApiOperation(value = "get data By Port", response = HttpResponse.class)
	@PostMapping("/getByPort/{arrivalPort}")
	public ResponseEntity<?> findDataByPort(@PathVariable("arrivalPort") String port){
		return portAddressService.getDataByPort(port);
	}
	
	@ApiOperation(value="save port Address")
	@PostMapping("/save")
	public ResponseEntity<?> saveAddressPort(@RequestBody ModemInfoTable portAddress){
		return portAddressService.saveAddressPort(portAddress);
	}
	
	@ApiOperation(value="run port Address")
	@PostMapping("/run")
	public ResponseEntity<?> runPort(@RequestBody WebActionTbl webAction){
		log.info("request for Run the Port "+webAction);
		GenricResponse response  = portAddressService.portAction(webAction.getModemId(),webAction.getStatus(),webAction.getAction(),webAction.getClientId(),"Run");
		log.info("exit from  Start port controller");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@ApiOperation(value="Stop port Address")
	@PostMapping("/stop")
	public ResponseEntity<?> stopPort(@RequestBody WebActionTbl webAction){
		log.info("request for Stop the Port "+webAction);
		GenricResponse response  = portAddressService.portAction(webAction.getModemId(),webAction.getStatus(),webAction.getAction(),webAction.getClientId(),"Stop");
		log.info("exit from  stop port controller");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@ApiOperation(value="update port Address")
	@PostMapping("/update")
	public ResponseEntity<?> updateAddressPort(@RequestBody ModemInfoTable portAddress){
		return portAddressService.updateAddressPort(portAddress);
	}
	
	@ApiOperation(value="delete port Address")
	@PostMapping("/delete")
	public ResponseEntity<?> deleteAddressPort(@RequestBody AllRequest request){
		return portAddressService.deleteAddressPort(request);
	}
	
	@ApiOperation(value = "port address data.", response = PortAddress.class)
	@PostMapping("/view")
	public MappingJacksonValue view(@RequestBody PortAddressFilter filter,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file){
		MappingJacksonValue mapping = null;
		log.info("portAddressRequest : "+filter);
		if (file == 0) {
			Page<ModemInfoTable> portAddressData  = portAddressService.portAddressInfo(filter, pageNo, pageSize,"View");
			mapping = new MappingJacksonValue(portAddressData);
			
			List<StatesInterpretationDb> states =  stateMgmtServiceImpl.findByFeatureId(2);
			for(ModemInfoTable modemInfoTable:portAddressData.getContent()) {
				for(StatesInterpretationDb list:states) {
					Integer value = list.getState();
					if(modemInfoTable.getStatusInt()==value) {
						modemInfoTable.setPortInterp(list.getInterp());
					}
				}
			}
			
			log.info("portAddressData--->"+portAddressData);
			mapping = new MappingJacksonValue(portAddressData);
		}else {
			FileDetails fileDetails = portAddressService.getFile(filter);
			mapping = new MappingJacksonValue(fileDetails);
		}	
		
		
			return mapping;		
	}
	
	@ApiOperation(value="view port Address data  by id")
	@PostMapping("/viewDataById")
	public ResponseEntity<?> viewByPortId(@RequestBody PortAddressFilter request){
		return portAddressService.viewPortById(request);
	}
	
	@ApiOperation(value="view port Address by id , without audi trail entry")
	@PostMapping("/viewById/{id}")
	public ResponseEntity<?> viewById(@PathVariable("id")long id){
		return portAddressService.viewById(id);
	}

}
