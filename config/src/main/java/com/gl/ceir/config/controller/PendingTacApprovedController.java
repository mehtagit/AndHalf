package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.PendingTacApprovedDb;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.service.impl.PendingTacApprovedImpl;
import com.gl.ceir.config.service.impl.SystemConfigListServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class PendingTacApprovedController {

	private static final Logger logger = LogManager.getLogger(PendingTacApprovedController.class);

	@Autowired
	PendingTacApprovedImpl pendingTacApprovedImpl;

	@ApiOperation(value = "Save || pending-tac-approved", response = SystemConfigListDb.class)
	@PostMapping("/save/pending-tac-approved")
	public MappingJacksonValue save(@RequestBody PendingTacApprovedDb pendingTacApprovedDb) {

		logger.info("Save pending-tac-approved request [" + pendingTacApprovedDb + "]");

		GenricResponse genricResponse = pendingTacApprovedImpl.saveSystemConfigList(pendingTacApprovedDb);

		MappingJacksonValue mapping = new MappingJacksonValue(pendingTacApprovedDb);

		logger.info("Response to send for save on pending-tac-approved [ " + genricResponse + "] = " + mapping);

		return mapping;
	}

	@ApiOperation(value = "View By Id || pending-tac-approved", response = SystemConfigListDb.class)
	@PostMapping("/pending-tac-approved")
	public MappingJacksonValue findPendingTacApproved(@RequestBody FilterRequest filterRequest) {

		logger.info("Get system-config-list request [" + filterRequest + "]");

		GenricResponse systemConfigListDb = pendingTacApprovedImpl.findByTxnId(filterRequest);

		MappingJacksonValue mapping = new MappingJacksonValue(systemConfigListDb);

		logger.info("Response to send on system-config-list [ " + filterRequest + "] = " + mapping);

		return mapping;
	}

	@ApiOperation(value = "All tags list || system-config-list", response = String.class)
	@DeleteMapping("/pending-tac-approved")
	public MappingJacksonValue deleteValue(@RequestBody FilterRequest filterRequest) {

		logger.info("Delete pending-tac-approved " + filterRequest);

		GenricResponse uniqueTags = pendingTacApprovedImpl.deletePendingApproval(filterRequest);

		MappingJacksonValue mapping = new MappingJacksonValue(uniqueTags);

		logger.info("Delete of pending-tac-approved [ " + mapping + "] for " + filterRequest);

		return mapping;
	}

}