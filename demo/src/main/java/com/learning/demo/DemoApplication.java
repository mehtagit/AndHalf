package com.learning.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.learning.demo.services.EmployeeService;

@SpringBootApplication
@Component
@EnableAutoConfiguration
@EnableCaching
public class DemoApplication {

	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		//context.getBean(EmployeeService.class).save();
		/*
		 * System.out.println(context.getBean(Employee.class).getId());
		 * System.out.println(context.getBean(Employee2.class).getId());
		 */
		
	}

}
