package org.gl.ceir.CeirPannelCode.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RuleFeatureMappingController {
	@GetMapping("ruleFeatureMav")
	public ModelAndView view() {
		return new ModelAndView("ruleFeatureMapping");

	}

	@GetMapping("add_ruleFeatureMav")
	public ModelAndView save() {
		return new ModelAndView("add_RuleFeatureMapping");

	}
}
