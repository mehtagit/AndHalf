package com.gl.ceir.config.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.User;
import com.gl.ceir.config.repository.UserRepository;

@Service
public class UserStaticServiceImpl {

	private static final Logger logger = LogManager.getLogger(UserStaticServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	public User getCeirAdmin() {
		return userRepository.getByUsername("CEIRAdmin");
	}
}