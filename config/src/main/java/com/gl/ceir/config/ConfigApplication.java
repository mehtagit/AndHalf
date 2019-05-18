package com.gl.ceir.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gl.ceir.config.model.Action;
import com.gl.ceir.config.model.ActionNames;
import com.gl.ceir.config.service.impl.ActionServiceImpl;

@SpringBootApplication
public class ConfigApplication {

	@Autowired
	static ActionServiceImpl actionServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
		//insert();
	}

	public static void insert() {
		Action action = new Action();
		action.setName(ActionNames.SYSTEM_REGULARIZED);
		actionServiceImpl.save(action);

	}

}
