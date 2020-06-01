package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.User;
import com.gl.ceir.config.repository.UserRepository;

@Service
public class UserStaticServiceImpl {

	@Autowired
	UserRepository userRepository;

	public User getCeirAdmin() {
		return userRepository.getByUsername("CEIRAdmin");
	}
	
	public List<User> getUserbyUsertypeId(long usertypeId) {
		return userRepository.getByUsertype_Id(usertypeId);
	}
	
}