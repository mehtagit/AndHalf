package com.gl.ceir.evaluator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gl.ceir.evaluator.config.Container;
import com.gl.ceir.evaluator.services.impl.PolicyEvaluator;

@SpringBootApplication
// @EnableJpaAuditing
@EnableAutoConfiguration
// @ComponentScan({ "com.gl.ceir.config.repository",
// "com.gl.ceir.config.service", "com.gl.ceir.config.model"
// ,"com.gl.ceir.evaluator"})
@ComponentScan({ "com.gl.ceir.config", "com.gl.ceir.evaluator" })
public class PolicyEvaluatorApplication {

	public static void main(String[] args) {
		Container.context = SpringApplication.run(PolicyEvaluatorApplication.class, args);

		PolicyEvaluator policyEvaluator = Container.context.getBean(PolicyEvaluator.class);
		policyEvaluator.run();
	}

}
