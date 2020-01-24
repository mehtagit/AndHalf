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
	public ModelAndView openStolenRecoveryPage(@RequestParam(name="pageType") String pageType,@RequestParam(name="pageView") String pageView)
	{
		log.info("entry point in  open stolen and recovery  page."+pageType);
		if(pageType.equals("stolen"))
		{
			log.info("block page");
			mv.setViewName("lawfulStolen");
		}else if(pageType.equals("recovery")) {
			log.info("recovery");
			mv.setViewName("lawfulRecovery");
		}
		else if(pageType.equals("editIndivisualsStolen"))
		{
			log.info("editIndivisualsStolen");
			mv.setViewName("editIndivisualStolen");
		}
		else if(pageType.equals("editCompanyStolen"))
		{
			log.info("editCompanyStolen");
			mv.setViewName("editCompanyStolen");
		}
		else if(pageType.equals("editIndivisualRecovery"))
		{
			log.info("editIndivisualRecovery");
			mv.setViewName("editIndivisualRecovery");
		}
		else if(pageType.equals("editCompanyRecovery"))
		{
			log.info("editCompanyRecovery");
			mv.setViewName("editCompanyRecovery");
		}
		
		log.info("exit point in  open stolen and recovery   page."+pageView);
		mv.addObject("viewType",pageView);
		return mv;
	}
	
}
