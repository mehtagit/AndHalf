package com.gl.ceir.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gl.ceir.executor.config.Container;
import com.gl.ceir.executor.services.impl.PolicyExecutor;

@SpringBootApplication
// @EnableJpaAuditing
@EnableAutoConfiguration
// @ComponentScan({ "com.gl.ceir.config.repository",
// "com.gl.ceir.config.service", "com.gl.ceir.config.model"
// ,"com.gl.ceir.evaluator"})
@ComponentScan({ "com.gl.ceir.config", "com.gl.ceir.executor" })
public class PolicyExecutorApplication {

	public static void main(String[] args) {
		Container.context = SpringApplication.run(PolicyExecutorApplication.class, args);

		PolicyExecutor policyEvaluator = Container.context.getBean(PolicyExecutor.class);
		policyEvaluator.run();
	}

}
