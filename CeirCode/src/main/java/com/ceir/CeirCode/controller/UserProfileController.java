package com.ceir.CeirCode.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.filtermodel.SearchAssignee;
import com.ceir.CeirCode.model.ChangePassword;
import com.ceir.CeirCode.model.ChangeUserStatus;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.FilterRequest;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.UserStatusRequest;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.service.UserProfileService;
import com.ceir.CeirCode.service.UserService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/userProfile")
@CrossOrigin
public class UserProfileController {

	@Autowired                                  
	UserService userService;    

	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	@Autowired 
	UserProfileService userProService;
	
	@ApiOperation(value = "change password", response = HttpResponse.class)
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword password){
		return userService.changePassword(password);   
	} 
	@ApiOperation(value = "change expriry password", response = HttpResponse.class)
	@PostMapping("/updateExpirePassword")
	public ResponseEntity<?> changeExpiryPassword(@RequestBody ChangePassword password){
		return userService.updateExpirePassword(password);   
	}

	@ApiOperation(value = "change user status", response = HttpResponse.class)
	@PostMapping("/updateUserStatus")
	public ResponseEntity<?> updateUserStatus(@RequestBody UserStatusRequest userStatus){
		return userService.updateUserStatus(userStatus);   
	}                                                                                                                                     

	@ApiOperation(value = "user profile data by user id", response = HttpResponse.class)
	@PostMapping("/editProfile/{userid}") 
	public ResponseEntity<?> editProfile(@PathVariable("userid") long userid){
		return userService.editProfile(userid);  
	} 

	@ApiOperation(value = "user profile data", response = HttpResponse.class)
	@PostMapping("/updateProfile") 
	public ResponseEntity<?> updateProfile(@RequestBody UserProfile userProfile){
		return userService.updateProfile(userProfile);
	}

	@ApiOperation(value = "user profile record data.", response = UserProfile.class)
	@PostMapping("/record") 
	public MappingJacksonValue viewRecord(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file){
		MappingJacksonValue mapping = null;
		if( file == 0) {
			Page<UserProfile> userProfileResponse  = userProService.viewAllRecord(filterRequest, pageNo, pageSize);
			List<SystemConfigListDb> asTypeList=systemConfigRepo.getByTag("AS_TYPE");
			if(userProfileResponse!=null) {
			for(UserProfile profile:userProfileResponse.getContent()) {
				for(SystemConfigListDb asType:asTypeList) {
					Integer value=asType.getValue();
					if(profile.getType()==value) {
						profile.setAsTypeName(asType.getInterp());
					}
				}
			}
			}
			mapping = new MappingJacksonValue(userProfileResponse);
			
		}else {
			FileDetails fileDetails = userProService.getFilterUSerPRofileInFile(filterRequest);
			mapping = new MappingJacksonValue(fileDetails);
		}
		return mapping;
	}

	@ApiOperation(value = "search assignee.", response = UserProfile.class)
	@PostMapping("/searchAssignee")
	public MappingJacksonValue searchAssignee(@RequestBody SearchAssignee assignee,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) {
		Page<UserProfile> profileList=userProService.assigneeInfo(assignee,pageNo,pageSize);
		MappingJacksonValue mapping=new MappingJacksonValue(profileList);
		return mapping;
	}
	
	
	@ApiOperation(value = "user profile status ", response = HttpResponse.class)
	@PutMapping("/updateStatus")
	public HttpResponse updateUserStatus(@RequestBody User user){
		return userService.updateStatus(user);
	}

	@ApiOperation(value = "user profile data by  id", response = HttpResponse.class)
	@PostMapping("/viewProfile/{id}/{userId}") 
	public ResponseEntity<?> viewProfile(@PathVariable("id") long id,@PathVariable("userId") long userId){
		return userService.profileDataById(id,userId); 
	} 

	@ApiOperation(value = "admin approve user", response = HttpResponse.class)
	@PostMapping("/adminApproval") 
	public HttpResponse adminApproval(@RequestBody UserStatusRequest userData){
		return userService.AdminApproval(userData);
	} 
	@ApiOperation(value = "change user status", response = HttpResponse.class)
	@PostMapping("/changeUserStatus")
	public ResponseEntity<?> changeUserStatus(@RequestBody ChangeUserStatus userStatus){
		return userService.changeUserStatus(userStatus);   
	}
}
