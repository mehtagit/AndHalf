package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.DBTableModel;
import org.gl.ceir.CeirPannelCode.Model.DBrowDataModel;
import org.gl.ceir.CeirPannelCode.Model.ReportResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Service
@FeignClient(url="${dashBoardfeignClientPath}",value = "profileUrls")
public interface DBTablesFeignClient {
	
	@RequestMapping(value="/db/tables" ,method=RequestMethod.POST) 
	public DBTableModel getAlltables(@RequestParam(name="dbName") String dbName);
	
	
	/*
	 * @RequestMapping(value= "/db/table/data/V2" , method=RequestMethod.POST)
	 * public Object DBRowDetailsFeign(@RequestBody DBrowDataModel filterRequest);
	 */
	
		
	
	@RequestMapping(value= "/db/table/data/V2" , method=RequestMethod.POST) 
	public Object historyConsignmentFeign(@RequestBody DBrowDataModel filterRequest);
	
	
	@RequestMapping(value="/report/list",method=RequestMethod.POST) 
	public List<ReportResponse> getAllReports();
	
	
	@RequestMapping(value= "/report/data" , method=RequestMethod.POST) 
	public Object ReportDetailsFeign(@RequestBody DBrowDataModel filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);
}