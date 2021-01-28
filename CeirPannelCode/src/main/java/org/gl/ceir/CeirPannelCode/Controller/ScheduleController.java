package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.GsmaFeignClient;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.NewRule;
import org.gl.ceir.CeirPannelCode.Model.NewSystemUser;
import org.gl.ceir.graph.model.ScheduleRequest;
import org.gl.ceir.pagination.model.AuditContentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScheduleController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	GsmaFeignClient gsmaFeignClient;

	@RequestMapping(value={"/scheduleManagement"},method={org.springframework.web.bind.annotation.
			RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	public ModelAndView viewManageType(HttpSession session,@RequestParam(name="txnID",required = false) String txnID) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewScheduleManagement");
		return mv; 
	}
	
	/*------------------------------------- Add Schedule ------------------------------------------ */

    @PostMapping("addSchedule") 
    public @ResponseBody ScheduleRequest saveSchedule (@RequestBody ScheduleRequest scheduleRequest)  {
	   log.info("request send to the add Schedule api="+scheduleRequest);
	   ScheduleRequest response= gsmaFeignClient.AddScheduleFeign(scheduleRequest);
	   log.info("response from add Schedule api "+response);
	   return response;
    }
    
    //------------------------------------- view Schedule ----------------------------------------							
	
  	
  	@GetMapping("viewBy/{id}")
	public ResponseEntity<?> viewUser(@PathVariable("id") Integer id) {
  		ScheduleRequest scheduleRequest = gsmaFeignClient.getScheduleByID(id);
		return new ResponseEntity<>(scheduleRequest, HttpStatus.OK);
	}
  	
}
