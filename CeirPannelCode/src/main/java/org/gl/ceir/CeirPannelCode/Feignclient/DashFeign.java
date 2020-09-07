package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CeirPannelCode.Model.SubstationName;

@Service
@FeignClient(url = "${dashfeignClientPath}", value = "dash")
 public interface DashFeign {


	@GetMapping("/station/get")
	public List<SubstationName> get(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "count", defaultValue = "10", required = false) int size,
			@RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction);
	
}