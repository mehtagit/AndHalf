package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.DBTablesFeignClient;
import org.gl.ceir.CeirPannelCode.Model.DBTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DBTablesController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Value ("${filePathforUploadFile}")
	String filePathforUploadFile;

	@Value ("${filePathforMoveFile}")
	String filePathforMoveFile;
	
	@Autowired
	DBTablesFeignClient dBTablesFeignClient;
	
	@GetMapping("/dbTables")
	public ModelAndView pageView(@RequestParam(name="via", required = false) String via,
								@RequestParam(name="tableName", required = false) String tableName, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if("other".equals(via)) {
			modelAndView.setViewName("viewDbTables");
		}
		else {
			modelAndView.setViewName("selectTable");
		}
		return modelAndView;
	}

	
	/*---------------------------------------- Select Table Dropdown ----------------------------------------*/
	
	@RequestMapping(value="/getallTables",method ={org.springframework.web.bind.annotation.RequestMethod.POST})
	public @ResponseBody DBTableModel dbTableList(@RequestParam(name="dbName") String dbName){
		DBTableModel dbTableList = dBTablesFeignClient.getAlltables(dbName);
		return dbTableList;
	}

}
