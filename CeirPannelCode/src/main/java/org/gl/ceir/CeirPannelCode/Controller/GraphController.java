package org.gl.ceir.CeirPannelCode.Controller;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.LoginGraphFilter;
import org.gl.ceir.CeirPannelCode.Model.UserLoginReport;
import org.gl.ceir.CeirPannelCode.Service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GraphController {

	@Autowired
	GraphService graphService;

	@ResponseBody
	@RequestMapping(value = "/userLoginGraph",method = {RequestMethod.POST})
	public List<UserLoginReport> userLoginGraph(@RequestBody LoginGraphFilter filter){
		return graphService.userLoginGraph(filter);
	}


	@RequestMapping(value = "/userLoginReport",method = {RequestMethod.GET})
	public ModelAndView userLoginReport(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("UserLoginGraph");
		return  mv;
	}


}