package org.gl.ceir.CeirPannelCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@EnableFeignClients
@EnableAutoConfiguration
@SpringBootConfiguration 
@ComponentScan(basePackages ="org.gl.ceir")
@PropertySource("classpath:awsConfiguration.properties") 
public class App extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
	public static void main( String[] args )
	{
		SpringApplication.run(App.class, args);
	}
}