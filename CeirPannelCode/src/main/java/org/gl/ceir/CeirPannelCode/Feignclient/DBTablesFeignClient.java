package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.DBTableModel;
import org.gl.ceir.CeirPannelCode.Model.DBrowDataModel;
import org.gl.ceir.CeirPannelCode.Model.ReportResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@Service
@FeignClient(url="${dashBoardfeignClientPath}",value = "profileUrls")
public interface DBTablesFeignClient {
	
	@RequestMapping(value="/db/tables" ,method=RequestMethod.POST) 
	public DBTableModel getAlltables(@RequestParam(name="dbName") String dbName);
	
	@RequestMapping(value= "/db/table/data/V2" , method=RequestMethod.POST) 
	public Object historyConsignmentFeign(@RequestBody DBrowDataModel filterRequest);
	
	
	@RequestMapping(value="/report/list",method=RequestMethod.POST) 
	public List<ReportResponse> getAllReports(@RequestParam(name="reportCategory", required = false) Integer reportCategory);
	
	//----------------------------Report TableData Feign------------------------------
	
	@RequestMapping(value= "/report/data" , method=RequestMethod.POST) 
	public Object ReportDetailsFeign(@RequestBody DBrowDataModel filterRequest,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file);
	
	//----------------------------Report headers Feign------------------------------
	
	@RequestMapping(value="/report/columnList" ,method=RequestMethod.POST) 
	public @ResponseBody DBrowDataModel tableHeaders(@RequestParam("reportnameId") Integer reportnameId);
	
	
	//----------------------------DB TableData Feign----------------------------------
	
	  @RequestMapping(value= "/db/table/data/V3" , method=RequestMethod.POST)
	  public Object DBRowDetailsFeign(@RequestBody DBrowDataModel filterRequest,
			  @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			  @RequestParam(value = "file", defaultValue = "0") Integer file);
	
	//----------------------------DB headers Feign----------------------------------
	
	@RequestMapping(value="/db/table/details" ,method=RequestMethod.POST) 
	public @ResponseBody DBrowDataModel dbtableHeaders(@RequestParam("dbName") String dbName, @RequestParam("tableName") String tableName);
}
