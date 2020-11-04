package com.gl.filemover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

import com.gl.filemover.controller.MainController;

@SpringBootApplication(scanBasePackages = { "com.gl.filemover" })
@EntityScan("com.gl.filemover.entity")
//@PropertySource("classpath:application.properties")
public class Application {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		MainController mainController = (MainController) context.getBean("mainController");
		String process_parameter = args[0];
		String operator_parameter = args[1];
		String source_parameter = args[2];
		if (process_parameter != null && operator_parameter != null && source_parameter != null) {
			mainController.processMethod(process_parameter, operator_parameter, source_parameter);
		} else {
			System.out.println("Error: pass correct argument to run application.");
		}
		context = null;
	}
}
