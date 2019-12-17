package com.gl.CEIR.FileProcess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.gl.CEIR.FileProcess.Controller.FileActionControlling;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.gl.CEIR.FileProcess", "com.gl.ceir.config"})
public class App {

	public static ConfigurableApplicationContext  ctx;
	
	public static void main( String[] args ){
		
		ctx = SpringApplication.run(App.class, args);
		
		FileActionControlling fetch = ctx.getBean(FileActionControlling.class);
		new Thread(fetch).start();

	}
}
