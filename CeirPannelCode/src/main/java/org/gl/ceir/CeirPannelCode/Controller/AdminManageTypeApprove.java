package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.TypeApprovedFeignImpl;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminManageTypeApprove {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TypeApprovedFeignImpl typeApprovedFeignImpl;
	@Autowired
	UtilDownload utildownload;
	
	@RequestMapping(value=
		{"./manageTypeDevices2"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	public ModelAndView viewManageTypeAdmin(HttpSession session,@RequestParam(name="txnID",required = false) String txnID) {
		ModelAndView mv = new ModelAndView();
		log.info(" view ManageType entry point."); 
		mv.setViewName("viewManageTypeAdmin");
		log.info(" view ManageType  exit point."); 
		return mv; 
	}
}
