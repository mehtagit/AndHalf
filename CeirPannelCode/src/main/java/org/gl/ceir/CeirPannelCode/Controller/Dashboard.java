package org.gl.ceir.CeirPannelCode.Controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.DashboardFeignClient;
import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.NumberOfBox;
import org.gl.ceir.CeirPannelCode.Model.RequestCountAndQuantity;
import org.gl.ceir.CeirPannelCode.Service.LoginService;
import org.gl.ceir.pagination.model.ConsignmentPaginationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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
	
	@GetMapping("/importerDashboard")
	public ModelAndView openUserRegisterPage(HttpSession session) {
		return loginService.dashBoard(session);   
	} 
 
	@RequestMapping(value={"/Home"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView Home(HttpSession session) { 
		/*
		 * RequestCountAndQuantity consignmentNoticationDetails = new
		 * RequestCountAndQuantity(); RequestCountAndQuantity stockDetails = new
		 * RequestCountAndQuantity(); RequestCountAndQuantity stolenRecoveryDetails =
		 * new RequestCountAndQuantity(); RequestCountAndQuantity grievanceDetails = new
		 * RequestCountAndQuantity();
		 * 
		 * log.info(" home  page entry point"); int userId= (int)
		 * session.getAttribute("userid"); String roletype=(String)
		 * session.getAttribute("usertype"); int roletypeId=(Integer)
		 * session.getAttribute("usertypeId"); int consignmentStatus=1; int
		 * stockStatus=1; int fileStatus=0; String requestType="stolen"; int
		 * grievanceStatus=0; log.info("request send to the  count api."
		 * +consignmentStatus+"  roletypeId========="+roletypeId);
		 * 
		 * consignmentNoticationDetails=dashBoardclient.consignmentNotification(userId,
		 * consignmentStatus,roletypeId);
		 * stockDetails=dashBoardclient.stockNotification(userId,
		 * stockStatus,roletypeId);
		 * stolenRecoveryDetails=dashBoardclient.stolenRecoveryNotification(userId,
		 * requestType, fileStatus,roletypeId);
		 * grievanceDetails=dashBoardclient.grievanceNotification(userId,
		 * grievanceStatus,roletypeId);
		 * 
		 * log.info("consignmentNoticationDetails="+
		 * consignmentNoticationDetails+"     stockDetails=="
		 * +stockDetails+"   stolenRecoveryDetails "+stolenRecoveryDetails+
		 * "  grievanceDetails=="+grievanceDetails);
		 * 
		 * mv.addObject("consignmentNoticationDetails", consignmentNoticationDetails);
		 * mv.addObject("stockDetails", stockDetails);
		 * mv.addObject("stolenRecoveryDetails", stolenRecoveryDetails);
		 * mv.addObject("grievanceDetails", grievanceDetails); mv.addObject("roletype",
		 * roletype);
		 */
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
	
	@GetMapping("getConsignmetnCountAndQuantity")
	public ResponseEntity<?> getConsignmetnCountAndQuantity(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId ) {
		RequestCountAndQuantity response = dashboardFeignClient.consignmentNotification(userId, featureId, userTypeId);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("getStockCountAndQuantity")
	public ResponseEntity<?> getStockCountAndQuantity(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId ) {
		RequestCountAndQuantity response = dashboardFeignClient.stockNotification(userId, featureId, userTypeId);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("getStolen_RecoveryCountAndQuantity")
	public ResponseEntity<?> getStolen_RecoveryCountAndQuantity(@RequestParam(value = "requestType") String requestType,@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId ) {
		RequestCountAndQuantity response = dashboardFeignClient.stolenRecoveryNotification(requestType, userId, featureId, userTypeId);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("getGrievanceNotificationCountAndQuantity")
	public ResponseEntity<?> getGrievanceNotificationCountAndQuantity(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId ) {
		RequestCountAndQuantity response = dashboardFeignClient.grievanceNotification(userId, featureId, userTypeId);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
} 
