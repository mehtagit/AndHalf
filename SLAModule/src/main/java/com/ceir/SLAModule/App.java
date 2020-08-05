package com.ceir.SLAModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.ceir.SLAModule.service.SLAService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.ceir.SLAModule")
public class App 
{
	private final static Logger log =LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {
	    	ConfigurableApplicationContext	ctx =SpringApplication.run(App.class, args);
	    	SLAService sLAService=ctx.getBean(SLAService.class);
			new Thread(sLAService).start();	 
			}
	    }


