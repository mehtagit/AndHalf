package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class PendingTacController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	FeignCleintImplementation feignCleintImplementation;

	@RequestMapping(value = { "/pendingTacList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView viewMessageManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		log.info(" view Pending TAC entry point.");
		mv.setViewName("pendingTACList");
		log.info(" view Pending TAC exit point.");
		return mv;
	}

	// ------------------------------------- delete Pending TAC
	// ----------------------------------------

	@DeleteMapping("pending-tac-approved")
	public @ResponseBody GenricResponse deletePortAddress(@RequestBody FilterRequest filterRequest) {
		log.info("request send to the Delete pending api=" + filterRequest);
		GenricResponse response = feignCleintImplementation.deletePendingTac(filterRequest);
		log.info("response after delete TAC." + response);
		return response;

	}

	// ------------------------------------- Export Pending TAC
	// ----------------------------------------
	@PostMapping("exportPendingTacData")
	@ResponseBody
	public FileExportResponse exportToExcel(@RequestBody FilterRequest filterRequest, HttpSession session) {
		Gson gsonObject = new Gson();
		Object response;
		Integer file = 1;
		log.info("filterRequest:::::::::" + filterRequest);
		response = feignCleintImplementation.pendingTACFeign(filterRequest, filterRequest.getPageNo(),
				filterRequest.getPageSize(), file);
		FileExportResponse fileExportResponse;
		Gson gson = new Gson();
		String apiResponse = gson.toJson(response);
		fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
		log.info("response  from Pending Tac Export  api=" + fileExportResponse);

		return fileExportResponse;
	}

}
