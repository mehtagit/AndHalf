package com.gl.ceir.evaluator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gl.ceir.evaluator.config.Container;

@SpringBootApplication
@EnableJpaAuditing
public class PolicyEvaluatorApplication {

	public static void main(String[] args) {
		Container.context = SpringApplication.run(PolicyEvaluatorApplication.class, args);
	}

}
