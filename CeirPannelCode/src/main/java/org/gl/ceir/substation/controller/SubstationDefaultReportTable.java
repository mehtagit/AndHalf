package org.gl.ceir.substation.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.gl.ceir.CeirPannelCode.Feignclient.DashFeign;
import org.gl.ceir.CeirPannelCode.Model.SubstationDefaultRequestModel;
import org.gl.ceir.CeirPannelCode.Model.SubstationDefaultResponseModel;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class SubstationDefaultReportTable {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired DashFeign dashFeign;
	@Autowired DefaultReportResponse defaultReportResponse;
	@PostMapping("/defaultReport")
	public ResponseEntity<?> defaultReport(HttpServletRequest request, @RequestParam(value = "order", defaultValue = "DESC", required = false) Sort.Direction direction){
		List<List<Object>> finalList=new ArrayList<List<Object>>();

		String filter = request.getParameter("filter");
		Gson gsonObject=new Gson();
		Integer size = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / size ;
		
		SubstationDefaultRequestModel filterrequest = gsonObject.fromJson(filter, SubstationDefaultRequestModel.class);
		filterrequest.setSearchString(request.getParameter("search[value]"));
		log.info("filterrequest::::"+filterrequest);
		log.info("size::::"+size+"--------pageNo::::"+pageNo);
		Object response = dashFeign.getSubstationDefaultReport(filterrequest, pageNo, size, direction);
		log.info("response::::"+response);
		
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			defaultReportResponse = gson.fromJson(apiResponse, DefaultReportResponse.class);
			Optional<List<SubstationDefaultResponseModel>> paginationContentListResponse = Optional.ofNullable(defaultReportResponse.getContent());
			if(paginationContentListResponse.isPresent()) {
				datatableResponseModel.setData(Collections.emptyList());
			for(SubstationDefaultResponseModel paginationContentList : paginationContentListResponse.get()) {
				Long id=paginationContentList.getId();
				String unitID=paginationContentList.getUnitId();
				String district=paginationContentList.getDistrict();
				String substation=paginationContentList.getSubstation();
				String capacity=paginationContentList.getCapacity();
				String packetReciveDate=paginationContentList.getPacketReceivedDate();
				String ry=paginationContentList.getRyVoltage();
				String yb=paginationContentList.getYbVoltage();
				String br=paginationContentList.getBrVoltage();
				
				String rCurrent=paginationContentList.getRampereCurrent();
				String yCurrent=paginationContentList.getYampereCurrent();
				String bCurrent=paginationContentList.getBampereCurrent();
				
				String rcap=paginationContentList.getRcapCurrent();
				String ycap=paginationContentList.getYcapCurrent();
				String bcap=paginationContentList.getBcapCurrent();
				String pf=paginationContentList.getTotalPF();
				
				String apparentPower=paginationContentList.getOverallApparentPower();
				String activePower=paginationContentList.getOverallActivePower();
				String powerInd=paginationContentList.getOverallReactivePowerInd();
				String powerCap=paginationContentList.getOverallReactivePowerCap();
				String remark=paginationContentList.getRemark();
				Object[] finalData={unitID,district,substation,capacity,packetReciveDate,ry,yb,br,rCurrent,yCurrent,bCurrent,rcap,ycap,bcap,pf,apparentPower,activePower,powerInd,powerCap,remark}; 
				List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
				finalList.add(finalDataList);
				datatableResponseModel.setData(finalList);
			log.info("final response :::::::::: "+datatableResponseModel);
			}
			}
			
			else {
				datatableResponseModel.setData(Collections.emptyList());
			}
			datatableResponseModel.setRecordsTotal(defaultReportResponse.getNumberOfElements());
			datatableResponseModel.setRecordsFiltered(defaultReportResponse.getTotalElements());
			
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		
		
	}}
	
