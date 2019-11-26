package com.gl.CEIR.CDRPicker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.gl.CEIR.CDRPicker.services.CDRService;
import com.gl.CEIR.CDRPicker.services.TestService;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = ("com.gl.CEIR.CDRPicker"))
public class App 
{
	public static ConfigurableApplicationContext ctx;
	
    public static void main( String[] args )
    {

    	ctx =SpringApplication.run(App.class, args);

    	CDRService fetch=ctx.getBean(CDRService.class);
		new Thread(fetch).start();

    
    }
}
