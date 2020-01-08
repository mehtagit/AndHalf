package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.service.impl.StockServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class StockController {

	private static final Logger logger = LogManager.getLogger(StockController.class);

	@Autowired
	StockServiceImpl stackholderServiceImpl;

	@ApiOperation(value = "Add Retailer And Distributer Info.", response = GenricResponse.class)
	@RequestMapping(path = "/Stock/upload", method = RequestMethod.POST)
	public GenricResponse uploadStock(@RequestBody StockMgmt stockMgmt){

		logger.info("Upload Stock Request =" + stockMgmt);

		GenricResponse genricResponse =	stackholderServiceImpl.uploadStock(stockMgmt);

		logger.info("Upload Stock Response ="+genricResponse.toString());

		return genricResponse;

	}

	@ApiOperation(value = "Update Retailer And Distributer Info.", response = GenricResponse.class)
	@RequestMapping(path = "/Stock/update", method = RequestMethod.POST)
	public GenricResponse updateStockInfo( @RequestBody StockMgmt stockMgmt){

		logger.info("Stock Update Request ="+stockMgmt.toString());

		GenricResponse genricResponse =	stackholderServiceImpl.updateStockInfo(stockMgmt);

		return genricResponse;

	}

	@ApiOperation(value = "View Retailer And Distributer All  Info.", response = StockMgmt.class)
	@RequestMapping(path = "v1/stock/record", method = RequestMethod.POST)
	public MappingJacksonValue findAll(@RequestBody StockMgmt stockMgmt) {

		logger.info("Stock View Details Request="+stockMgmt);

		List<StockMgmt> response =	stackholderServiceImpl.getAllData(stockMgmt);

		MappingJacksonValue mapping = new MappingJacksonValue(response);

		logger.info("Response Record Details="+mapping);
		return mapping;
	}

	@ApiOperation(value = "Filter View Retailer And Distributer All  Info.", response = StockMgmt.class)
	@RequestMapping(path = "/stock/record", method = RequestMethod.POST)
	public MappingJacksonValue findAllFilteredData(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) {

		MappingJacksonValue mapping = null;

		if(file == 0) {
			logger.info("Stock View filter Details Request= " + filterRequest);
			Page<StockMgmt> response = stackholderServiceImpl.getAllFilteredData(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(response);
		}else {
			logger.info("Request to export filtered Stocks = " + filterRequest);
			FileDetails fileDetails = stackholderServiceImpl.getFilteredStockInFile(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(fileDetails);
		}
		
		logger.info("Response Filtered Record Details = " + mapping);

		return mapping;

	}

	@ApiOperation(value = "View Retailer And Distributer Record of TxnId.", response = StockMgmt.class)
	@RequestMapping(path = "/stock/view", method = RequestMethod.POST)
	public MappingJacksonValue view(@RequestBody StockMgmt stockMgmt) {

		logger.info("Txn View Request ="+stockMgmt.toString());

		StockMgmt response	= stackholderServiceImpl.view(stockMgmt);

		MappingJacksonValue mapping = new MappingJacksonValue(response);

		logger.info("Resposne view  ");

		return mapping;

	}

	@ApiOperation(value = "Delete Retailer And Distributer Record of TxnId.", response = GenricResponse.class)
	@RequestMapping(path = "/stock/delete", method = RequestMethod.POST)
	public GenricResponse deleteStachHolderData(@RequestBody StockMgmt stockMgmt,
			@RequestParam(value = "userType", required = false) String userType) {

		logger.info("Stock Withdraw Request = " + stockMgmt + " for usertype : " + userType);
		GenricResponse genricResponse =	stackholderServiceImpl.deleteStockDetailes(stockMgmt, userType);

		logger.info("Response to Delete ="+genricResponse.toString());
		return genricResponse;

	}

	/*@ApiOperation(value = "Get total count and quantity.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/stock/countAndQuantity", method = RequestMethod.POST)
	public MappingJacksonValue getConsignmentCountAndQuantity( @RequestBody RequestCountAndQuantityWithLongUserId request ) {
		ResponseCountAndQuantity response = stackholderServiceImpl.getStockCountAndQuantity( request );
		return new MappingJacksonValue(response);
	}*/

	@ApiOperation(value = "Accept Reject Stock.", response = GenricResponse.class)
	@RequestMapping(path = "accept-reject/stock", method = RequestMethod.PUT)
	public GenricResponse updateConsigmentStatus(@RequestBody ConsignmentUpdateRequest acceptRejectRequest) {

		logger.info("Request to accept/reject the stock= " + acceptRejectRequest);

		GenricResponse genricResponse = stackholderServiceImpl.acceptReject(acceptRejectRequest);

		return genricResponse ;

	}

}
