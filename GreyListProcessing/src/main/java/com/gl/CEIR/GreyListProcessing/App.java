package com.gl.CEIR.GreyListProcessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.gl.CEIR.GreyListProcessing.service.BlackService;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = ("com.gl.CEIR.GreyListProcessing"))
public class App 
{
	public static void main( String[] args )
	{
		ConfigurableApplicationContext	ctx =SpringApplication.run(App.class, args);
		 
		BlackService fetch=ctx.getBean(BlackService.class);
		new Thread(fetch).start();

	}
}
