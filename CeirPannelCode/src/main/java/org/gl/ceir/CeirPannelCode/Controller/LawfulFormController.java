package org.gl.ceir.CeirPannelCode.Controller;

import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LawfulFormController 
{

	@Value ("${filePathforUploadFile}")
	String filePathforUploadFile;

	@Value ("${filePathforMoveFile}")
	String filePathforMoveFile;
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	ModelAndView mv = new ModelAndView();
	GenricResponse response= new GenricResponse();
	
	
	
	@RequestMapping(value="/openlawfulStolenRecoveryPage",method = {RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView openStolenRecoveryPage(@RequestParam(name="pageType",required = false) String pageType)
	{
		log.info("entry point in  open stolen and recovery  page."+pageType);
		if(pageType.equals("stolen"))
		{
			log.info("block page");
			mv.setViewName("lawfulStolen");
		}else {
			log.info("recovery");
			mv.setViewName("lawfulRecovery");
		}
		log.info("exit point in  open stolen and recovery   page.");
		return mv;
	}
	
}
