package org.gl.ceir.CeirPannelCode.Service;

import java.util.ArrayList;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Feignclient.GraphFeign;
import org.gl.ceir.CeirPannelCode.Model.LoginGraphFilter;
import org.gl.ceir.CeirPannelCode.Model.UserLoginReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	GraphFeign graphFeign;
	
	public List<UserLoginReport> userLoginGraph(LoginGraphFilter filter) {
	log.info("inside login graph service");
	log.info("filter data: "+filter);
	try {
		List<UserLoginReport> data=graphFeign.userLoginGraph(filter);
		log.info("list data size in response: "+data.size());
		return data;
	}
	catch(Exception e) {
		log.info("error in get user login graph data");
		log.info(e.toString());
		return new ArrayList<UserLoginReport>();
	}
	}
}
