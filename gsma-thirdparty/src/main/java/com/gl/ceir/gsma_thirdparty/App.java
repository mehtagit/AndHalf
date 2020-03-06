package com.gl.ceir.gsma_thirdparty;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.gl.ceir.gsma_thirdparty.service.EncriptonBlacklistService;

@SpringBootApplication(scanBasePackages= {"com.gl.ceir.gsma_thirdparty"})
public class App {
	
    public static void main( String[] args ){
    	ApplicationContext context = SpringApplication.run(App.class, args);
		try {
			context.getBean(EncriptonBlacklistService.class).run();
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
