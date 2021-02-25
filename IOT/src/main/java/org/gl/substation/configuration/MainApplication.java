package org.gl.substation.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"org.gl.substation"})
@EnableCaching
@EnableJpaAuditing
@EnableJpaRepositories("org.gl.substation.repository") 
@EntityScan(basePackages= {"org.gl.substation.entity"})

public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	 public void onStartup(ServletContext servletContext) throws ServletException {
	        servletContext.setInitParameter("listings", "true");
	    }
// throw new NotImplementedException();
}
