package org.gl.ceir.CeirPannelCode.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Dashboard {
	private static final Logger log = LogManager.getLogger(Dashboard.class);
	public ModelAndView openUserRegisterPage() {
		ModelAndView mv = new ModelAndView();
		log.info("importer dashboard entry point..");
		mv.setViewName("dashboard");
		log.info("importer dashboard exit point..");
		return mv; 
	}
}
