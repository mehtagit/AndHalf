package org.gl.ceir.CeirPannelCode.Controller;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.RequestCountAndQuantity;
import org.gl.ceir.CeirPannelCode.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Dashboard {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired

	FeignCleintImplementation feignCleintImplementation;
	
	@Autowired
	LoginService loginService;
	ModelAndView mv = new ModelAndView();
	
	@GetMapping("/importerDashboard")
	public ModelAndView openUserRegisterPage(HttpSession session) {
		return loginService.Dashboard(session);   
	} 
 
	@RequestMapping(value={"/Home"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView Home(HttpSession session) { 
		RequestCountAndQuantity consignmentNoticationDetails = new RequestCountAndQuantity();
		RequestCountAndQuantity stockDetails = new RequestCountAndQuantity();
		RequestCountAndQuantity stolenRecoveryDetails = new RequestCountAndQuantity();
		RequestCountAndQuantity grievanceDetails = new RequestCountAndQuantity();
		
	log.info(" home  page entry point");
	int userId= (int) session.getAttribute("userid");
	String roletype=(String) session.getAttribute("usertype");
	int consignmentStatus=1;
	int stockStatus=1;
	int fileStatus=0;
	String requestType="stolen";
	int grievanceStatus=0;
	log.info("request send to the  count api."+consignmentStatus);
	
	consignmentNoticationDetails=feignCleintImplementation.consignmentNotification(userId, consignmentStatus);
	stockDetails=feignCleintImplementation.stockNotification(userId, stockStatus);
	stolenRecoveryDetails=feignCleintImplementation.stolenRecoveryNotification(userId, requestType, fileStatus);
	grievanceDetails=feignCleintImplementation.grievanceNotification(userId, grievanceStatus);
	
	log.info("consignmentNoticationDetails="+consignmentNoticationDetails+"     stockDetails=="+stockDetails+"   stolenRecoveryDetails "+stolenRecoveryDetails+
			"  grievanceDetails=="+grievanceDetails);
	
	mv.addObject("consignmentNoticationDetails", consignmentNoticationDetails);
	mv.addObject("stockDetails", stockDetails);
	mv.addObject("stolenRecoveryDetails", stolenRecoveryDetails);
	mv.addObject("grievanceDetails", grievanceDetails);
	mv.addObject("roletype", roletype);
	mv.setViewName("Home");
	return mv;
	}
	
} 
