package org.gl.ceir.CeirPannelCode.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RuleListController {
	@GetMapping("ruleListMav")
	public ModelAndView view() {
		return new ModelAndView("ruleList");

	}
}
