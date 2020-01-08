package org.gl.ceir.CeirPannelCode.Controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.DashboardFeignClient;
import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.NumberOfBox;
import org.gl.ceir.CeirPannelCode.Model.RequestCountAndQuantity;
import org.gl.ceir.CeirPannelCode.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Dashboard {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	
	@Autowired
	DashboardFeignClient dashboardFeignClient;
	@Autowired
	DashboardFeignClient dashBoardclient;
	
	@Autowired
	LoginService loginService;
	ModelAndView mv = new ModelAndView();
	
	
	  @GetMapping("/importerDashboard") public ModelAndView
	  openUserRegisterPage(HttpSession session) { return
	  loginService.dashBoard(session); }
	 
	@RequestMapping(value={"/Home"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView Home(HttpSession session) { 
	ModelAndView mv =new ModelAndView();
	mv.setViewName("Home");
	return mv;
	}
	
	
	
	@GetMapping("dashboard/box")
	@ResponseBody
	public ResponseEntity<?> initialDashBoard(@RequestParam(value = "userTypeId") Integer userTypeId) {
	List<NumberOfBox> response= dashboardFeignClient.dashBoardDBConf(userTypeId);
	return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@GetMapping("/consignment/countAndQuantity")
	public ResponseEntity<?> getConsignmetnCountAndQuantity(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId,@RequestParam(value = "userType") String userType) {
		RequestCountAndQuantity response = dashboardFeignClient.consignmentNotification(userId, featureId, userTypeId,userType);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/stock/countAndQuantity")
	public ResponseEntity<?> getStockCountAndQuantity(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId,@RequestParam(value = "userType") String userType) {
		RequestCountAndQuantity response = dashboardFeignClient.stockNotification(userId, featureId, userTypeId,userType);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/stakeholder/count")
	public ResponseEntity<?> getStolen_RecoveryCountAndQuantity(@RequestParam(value = "requestType") String requestType,@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId,@RequestParam(value = "userType") String userType) {
		RequestCountAndQuantity response = dashboardFeignClient.stolenRecoveryNotification(requestType, userId, featureId, userTypeId,userType);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/grievance/count")
	public ResponseEntity<?> getGrievanceNotificationCountAndQuantity(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId,@RequestParam(value = "userType") String userType) {
		RequestCountAndQuantity response = dashboardFeignClient.grievanceNotification(userId, featureId, userTypeId,userType);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
} 
