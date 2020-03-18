package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.AssigneRequestType;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.UploadStockAssigneModal;
import org.gl.ceir.CeirPannelCode.Model.UserStatus;
import org.gl.ceir.CeirPannelCode.Response.UpdateProfileResponse;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Component
@Service
@FeignClient(url="${apiUrl1}",value = "profileUrls")
public interface UserProfileFeignImpl {

	@PostMapping("/userProfile/changePassword")
	public HttpResponse changePassword(Password password);
	
	@PostMapping("/userProfile/updateExpirePassword")
	public HttpResponse updateExpirePassword(Password password);
	

	@PostMapping("/userProfile/updateUserStatus")
	public HttpResponse updateUserStatus(UserStatus userStatus);


	@PostMapping("/userProfile/editProfile/{userid}") 
    public Registration editUserProfile(@PathVariable("userid") Integer userid);
	
	@PostMapping("/userProfile/updateProfile") 
    public UpdateProfileResponse updateUserProfile(Registration registration);
	
	//****************************************************************Admin Registration api starts from here ***************************************************************************************************	
	//View admin registration feign controller
	@RequestMapping(value="/userProfile/record" ,method=RequestMethod.POST) 
	public Object registrationRequest(@RequestBody FilterRequest filterRequest,
	@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
	@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
	@RequestParam(value = "file", defaultValue = "0") Integer file
	) ;

	
	@PostMapping("/userProfile/adminApproval")
	public HttpResponse adminUserApproval(UserStatus userStatus);
	
	@PostMapping("/userProfile/viewProfile/{id}")
	public Registration ViewAdminUser(@PathVariable("id") long id);
	
	
	@RequestMapping(value="/userProfile/searchAssignee" ,method=RequestMethod.POST)
	public Object asigneeDetailsFeign(@RequestBody FilterRequest filterRequest,
	@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
	@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
	@RequestParam(value = "file", defaultValue = "0") Integer file
	) ;
	
	@PostMapping("/userProfile/changeUserStatus")
	public HttpResponse changeUserStatusFeign(UserStatus userStatus);
	
	
	/*-------------------------- view Port Feign ------------------------------*/
	
	@RequestMapping(value="/portAddress/view" ,method=RequestMethod.POST) 
	public Object viewPortRequest(@RequestBody FilterRequest filterRequest,
	@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
	@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
	@RequestParam(value = "file", defaultValue = "0") Integer file);
	
	
	
	//***************************************************Add Port Address Feign********************************

	@RequestMapping(value= "/portAddress/save" , method=RequestMethod.POST) 
	public GenricResponse AddPortAddressFeign(@RequestBody FilterRequest filterRequest);
	
	
	
	//***************************************************View Port Management Feign********************************
	
	@RequestMapping(value="/portAddress/viewById/{id}" ,method=RequestMethod.POST) 
	public @ResponseBody GenricResponse viewPortFeign(@PathVariable("id") Integer id);
	
	
	
	//***************************************************Update Port Address Feign********************************

	@RequestMapping(value= "/portAddress/update" , method=RequestMethod.POST) 
	public GenricResponse updatePortAddressFeign(@RequestBody FilterRequest filterRequest);
	
	//***************************************************Delete Port Management Feign********************************
	
	@RequestMapping(value="/portAddress/delete/{id}" ,method=RequestMethod.POST) 
	public @ResponseBody GenricResponse deletePortFeign(@PathVariable("id") Integer id);
	
	
	/*-------------------------- view all Currency Feign ------------------------------*/
	
	@RequestMapping(value="/currency/view" ,method=RequestMethod.POST) 
	public Object viewCurrencyRequest(@RequestBody FilterRequest filterRequest,
	@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
	@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
	@RequestParam(value = "file", defaultValue = "0") Integer file);
	
	
	
	//***************************************************Add Currency Feign********************************

		@RequestMapping(value= "/currency/save" , method=RequestMethod.POST) 
		public GenricResponse AddCurrencyFeign(@RequestBody FilterRequest filterRequest);
		
		
		
		//***************************************************View Currency Feign********************************
		
		@RequestMapping(value="/currency/viewById/{id}" ,method=RequestMethod.POST) 
		public @ResponseBody GenricResponse viewCurrencyFeign(@PathVariable ("id") Integer id);
		
		
		
		//***************************************************Update Currency Feign********************************

		@RequestMapping(value= "/currency/update" , method=RequestMethod.POST) 
		public GenricResponse updateCurrencyFeign(@RequestBody FilterRequest filterRequest);
		
	
	
} 

