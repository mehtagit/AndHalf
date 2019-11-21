package org.gl.ceir.CeirPannelCode.Controller;

import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlockUnblock {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	ModelAndView mv = new ModelAndView();
	GenricResponse response= new GenricResponse();
	
	@RequestMapping(value="/blockUnblockDevices",method = {RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView blockUnblockPage()
	{
		log.info("entry point in  view block or unblock page.");
		mv.setViewName("blockUnblockView");
		log.info("entry point in  view block or unblock page.");
		return mv;
	}
	
	@RequestMapping(value="/selectblockUnblockPage",method = {RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView selectblockUnblockPage()
	{
		log.info("entry point in  select block or unblock option  page.");
		mv.setViewName("reportBlockUnblock");
		log.info("entry point in  select block or unblock option  page.");
		return mv;
		
	}
	@RequestMapping(value="/openBlockUnblockPage",method = {RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView openBlockUnblockPage(@RequestParam(name="pageType",required = false) String pageType)
	{
		log.info("entry point in  open block or unblock   page."+pageType);
		if(pageType.equals("block"))
		{
			log.info("block page");
			mv.setViewName("reportBlock");
		}else {
			log.info("unBlock page");
			mv.setViewName("reportUnblock");
		}
		log.info("entry point in  open block or unblock   page.");
		return mv;
	}
	
	@RequestMapping(value="/blockSingleDevices",method = {RequestMethod.GET,RequestMethod.POST} )
	public @ResponseBody GenricResponse blockSingleDevices(@RequestParam(name="",required = false) String deviceType )
	{
		log.info("entry point in  select block or unblock option  page.");
		
		log.info("entry point in  select block or unblock option  page.");
		response.setErrorCode("0");
		response.setMessage("sucess");
		return response;
		
	}
	
}
