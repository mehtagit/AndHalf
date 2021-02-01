/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.ceir.config.controller;

import com.gl.ceir.config.model.CheckImeiMess;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.ScheduleReportDb;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RestController;
import com.gl.ceir.config.service.ScheduleReportService;
import com.gl.ceir.config.service.impl.ScheduleReportControllerImpl;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.data.domain.Page; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author maverick
 */
@RestController

public class ScheduleReportController {

    private static final Logger logger = Logger.getLogger(ScheduleReportController.class);

    @Autowired
    ScheduleReportControllerImpl scheduleReportControllerImpl;

//    @Autowired
//    ScheduleReportService ScheduleReportService;
    @ApiOperation(value = "View All witout Pagenation  ", response = ScheduleReportDb.class, responseContainer = "list")
    @RequestMapping(path = "/ScheduleReport/getAllValues", method = RequestMethod.GET)
    public MappingJacksonValue getAll() {
        List<ScheduleReportDb> allActions = scheduleReportControllerImpl.getAll();
//		List<ScheduleReportDb> allActions = ScheduleReportService.getAll();      it was early
        MappingJacksonValue mapping = new MappingJacksonValue(allActions);
        return mapping;
    }

    @RequestMapping(path = "/ScheduleReport/{id}", method = RequestMethod.GET)
    public MappingJacksonValue get(@PathVariable(value = "id") Long id) {
//		ScheduleReportDb action = actionService.get(id);
        ScheduleReportDb action = scheduleReportControllerImpl.get(id);
        MappingJacksonValue mapping = new MappingJacksonValue(action);
        return mapping;
    }

    @RequestMapping(path = "/ScheduleReport/", method = RequestMethod.POST)
    public MappingJacksonValue save(@RequestBody ScheduleReportDb action) {
        ScheduleReportDb savedAction = scheduleReportControllerImpl.save(action);
        MappingJacksonValue mapping = new MappingJacksonValue(savedAction);
        return mapping;
    }

    @RequestMapping(path = "/ScheduleReport/{id}", method = RequestMethod.DELETE)
    public MappingJacksonValue delete(@PathVariable(value = "id") Long id) {
        scheduleReportControllerImpl.delete(id);
        CheckImeiMess cImsg = new CheckImeiMess();
        cImsg.setStatus("true");
        MappingJacksonValue mapping = new MappingJacksonValue(cImsg);
        return mapping;
    }

    @RequestMapping(path = "/ScheduleReport/", method = RequestMethod.PUT)
    public MappingJacksonValue update(@RequestBody ScheduleReportDb action) {
        ScheduleReportDb updatedAction = scheduleReportControllerImpl.update(action);
        MappingJacksonValue mapping = new MappingJacksonValue(updatedAction);
        return mapping;
    }

    @ApiOperation(value = "  View  By Pagination  ", response = ScheduleReportDb.class)
    @PostMapping("/ScheduleReport/getAll")
    public MappingJacksonValue getFilteredData(
            @RequestBody FilterRequest filterRequest,
            @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "file", defaultValue = "0") Integer file) {
        MappingJacksonValue mapping = null;
        file = 0;    //  
        if (file == 0) {
            logger.info(" filterRequest view   = " + filterRequest.toString());
            Page<ScheduleReportDb> ruleEngineMapping = scheduleReportControllerImpl.filterRuleEngineMapping(filterRequest, pageNo, pageSize, "view");
            mapping = new MappingJacksonValue(ruleEngineMapping);
            logger.info("Response of view Request = " + mapping.toString());
        } else {

//			ScheduleReportDb fileDetails = scheduleReportControllerImpl.getFile(filterRequest);
//			mapping = new MappingJacksonValue(fileDetails);		
        }

        return mapping;
    }

} 
