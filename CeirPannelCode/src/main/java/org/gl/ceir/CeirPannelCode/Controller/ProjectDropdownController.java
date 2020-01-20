package org.gl.ceir.CeirPannelCode.Controller;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectDropdownController {
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	@ResponseBody
	@GetMapping("getDropdownList/{featureId}/{userTypeId}")
	public List<Dropdown> getConsignmentStatus(@PathVariable("featureId") Integer featureId,@PathVariable("userTypeId") Integer userTypeId) {
		List<Dropdown> dropdown = feignCleintImplementation.consignmentStatusList(featureId, userTypeId);
		return dropdown;
	}
	
	@ResponseBody
	@GetMapping("getDropdownList/{tag}")
	public List<Dropdown> getTaxPaidStatus(@PathVariable("tag") String tag) {
		List<Dropdown> dropdown = feignCleintImplementation.taxPaidStatusList(tag);
		log.info("DROPDOWN::::::::"+dropdown);
		return dropdown;
	}
	
	
	@ResponseBody
	@GetMapping("getTypeDropdownList/{tagId}/{userTypeId}")
	public List<Dropdown> asTypeDropdown(@PathVariable("tagId") String tag, @PathVariable("userTypeId") Integer userTypeId ) {
		List<Dropdown> dropdown = feignCleintImplementation.asTypeList(tag, userTypeId);
		return dropdown;
	}
	
	@ResponseBody
	@GetMapping("dataByTag/{tag}/")
	public Dropdown dataByTag(@PathVariable("tag") String tag) {
		log.info("inside data by tag controller");
		Tag tagData=new Tag(tag);
		log.info("tag from form: "+tag);
		Dropdown dropdown = feignCleintImplementation.dataByTag(tagData);
		log.info("data by tag from api =  "+dropdown);
		log.info("exit from data by tag controller");
		return dropdown;
	}
}
