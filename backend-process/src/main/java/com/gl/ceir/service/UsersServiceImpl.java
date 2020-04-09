package com.gl.ceir.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.configuration.PropertiesReader;
import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.entity.User;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.UserRepository;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;
import com.gl.ceir.util.Utility;

@Service
public class UsersServiceImpl {

	private static final Logger logger = LogManager.getLogger(UsersServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	public List<User> getUserWithStatusPendingOtp(int day) {
		try {
			return userRepository.findAll(buildSpecification(DateUtil.nextDate(day)).build());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>(1);
		}
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	private GenericSpecificationBuilder<User> buildSpecification(String date){
		GenericSpecificationBuilder<User> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		cmsb.with(new SearchCriteria("createdOn", date , SearchOperation.GREATER_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("createdOn", date , SearchOperation.LESS_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("status", 1, SearchOperation.GREATER_THAN, Datatype.STRING));

		return cmsb;
	}

}
