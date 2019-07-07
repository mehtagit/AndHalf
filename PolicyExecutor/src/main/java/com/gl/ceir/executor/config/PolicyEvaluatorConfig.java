package com.gl.ceir.executor.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ConfigurationProperties(prefix = "policy")
public class PolicyEvaluatorConfig {

	@NotBlank
	@NotNull
	private String inputRepositoryDirectory;

	@NotBlank
	@NotNull
	private String completedDirectory;

	private String period;

	public String getInputRepositoryDirectory() {
		return inputRepositoryDirectory;
	}

	public void setInputRepositoryDirectory(String inputRepositoryDirectory) {
		this.inputRepositoryDirectory = inputRepositoryDirectory;
	}

	public String getCompletedDirectory() {
		return completedDirectory;
	}

	public void setCompletedDirectory(String completedDirectory) {
		this.completedDirectory = completedDirectory;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	// @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.gl.ceir.config.controller"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo());
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("CEIR Configuration APIs Document")
				.description("Configuration Management REST APIs").license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0").build();
	}

	@Bean
	public Executor executor() {
		return Executors.newFixedThreadPool(5);
	}

}
