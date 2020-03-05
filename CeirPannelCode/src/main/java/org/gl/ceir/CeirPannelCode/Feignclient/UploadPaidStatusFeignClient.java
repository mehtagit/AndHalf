package org.gl.ceir.CeirPannelCode.Feignclient;

import org.gl.ceir.CeirPannelCode.Model.EndUserVisaInfo;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest_UserPaidStatus;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.LawfulStolenRecovey;
import org.gl.ceir.pagination.model.UserPaidStatusContent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@FeignClient(url = "${feignClientPath}",value = "dsj" )
public interface UploadPaidStatusFeignClient {

	@GetMapping("/end-user/{nid}")
	public GenricResponse respone(@PathVariable("nid") String nid);
	
	
	@PostMapping("/filter/end-user-device-info")
	public Object view(@RequestBody FilterRequest_UserPaidStatus filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file);
	
	

	@DeleteMapping("/end-user-device-info/{imei}")
	public @ResponseBody GenricResponse delete(@PathVariable("imei") Long imei);
	
	
	
	
	
	// ********************************************** open register page or edit popup *****************************
	@GetMapping("/end-user-device-info/{imei}")
	public @ResponseBody UserPaidStatusContent viewByImei(@PathVariable("imei") Long imei);
	

	@PostMapping("/end-user-device-info/count-by-nid/{nid}")
	public @ResponseBody GenricResponse countByNid(@PathVariable("nid") String  nid);
	
	
	@PutMapping("/accept-reject/end-user-device")
	public @ResponseBody GenricResponse approveRejectFeign(FilterRequest_UserPaidStatus filterRequest);
	
	@GetMapping("/end-user/{nid}")
	public @ResponseBody GenricResponse fetchVisaDetailsbyPassport(@PathVariable("nid") String  nid);
	
	@PutMapping("visa/end-user")
	public @ResponseBody GenricResponse updateEndUSerVisaDetailsby(EndUserVisaInfo visaInfo);
	
	@PostMapping("/end-user")
	public @ResponseBody GenricResponse RegisterEndUserDevice(EndUserVisaInfo visaInfo);
	
	@PostMapping("/stakeholder/Stolen")
	public @ResponseBody GenricResponse lawfulIndivisualStolen(LawfulStolenRecovey lawfulStolen);
	
	@PostMapping("/stakeholder/Recovery")
	public @ResponseBody GenricResponse lawfulIndivisualAndOraganisationRecovery(LawfulStolenRecovey lawfulStolen);

	@RequestMapping(value="/stolen-and-recovery/by-txnId" ,method=RequestMethod.POST) 
	public LawfulStolenRecovey fetchSingleDevicebyTxnId(LawfulStolenRecovey txnId );

	@RequestMapping(value="/stakeholder/update",method=RequestMethod.PUT) 
	public GenricResponse updateIndivisualStolen(LawfulStolenRecovey lawfulStolen );


}