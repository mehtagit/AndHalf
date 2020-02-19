package org.gl.ceir.CeirPannelCode.Controller;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Feignclient.GsmaFeignClient;
import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.Tag;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.GrievanceDropdown;
import org.gl.ceir.CeirPannelCode.Model.Tag;
import org.gl.ceir.pagination.model.MessageContentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectDropdownController {
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	
	@Autowired
	GsmaFeignClient gsmaFeignClient;
	
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
	
	@ResponseBody
	@GetMapping("getSourceTypeDropdown/{tagId}/{featureId}")
	public List<Dropdown> asRequestType(@PathVariable("tagId") String tagId, @PathVariable("featureId") Integer featureId ) {
		List<Dropdown> dropdown = feignCleintImplementation.modeType(tagId, featureId);
		return dropdown;
	}
	@ResponseBody
	@GetMapping("productList")
	public List<Dropdown> productList() {
		List<Dropdown> dropdown = gsmaFeignClient.viewAllProductList();
		return dropdown;
	}
	
	@RequestMapping(value="/productModelList",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public @ResponseBody List<Dropdown> productModelList(@RequestParam(name="brand_id") Integer brand_id){
		List<Dropdown> productModelList = gsmaFeignClient.viewAllmodel(brand_id);
		
		return productModelList;
		
	}
	
	@PostMapping("/get/tags-mapping") 
	public @ResponseBody List<GrievanceDropdown> catagoryDropdownList (@RequestBody FilterRequest filterRequest)  {
		log.info("request send to the catagoryDropdownList api="+filterRequest);
		List<GrievanceDropdown> response= feignCleintImplementation.catagoryDropdownListFeign(filterRequest);
		log.info("response from catagoryDropdownList api "+response);
		return response;

		}
	
	@PostMapping("/getSystemTags") 
	public @ResponseBody GenricResponse getAllTagsDropdown (@RequestBody FilterRequest filterRequest)  {
		log.info("request send to the getAllTagsDropdown api="+filterRequest);
		GenricResponse response= feignCleintImplementation.getAllTagsDropdowntFeign(filterRequest);
		log.info("response from getAllTagsDropdown api "+response);
		return response;

		}

	
}
