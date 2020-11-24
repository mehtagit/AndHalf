package org.gl.ceir.CeirPannelCode.Controller;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Feignclient.DashFeign;
import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import CeirPannelCode.Model.SubstationName;

@Controller
public class DashBox {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	FeignCleintImplementation feignCleintImplementation;

	@Autowired
	DashFeign dashFeign;

	@GetMapping("/substation/get")
	@ResponseBody
	public ResponseEntity<?> get(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "count", defaultValue = "10", required = false) int size,
			@RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction){
		List<SubstationName> list = dashFeign.get(pageNo, size, direction);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}