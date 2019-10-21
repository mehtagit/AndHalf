package org.gl.ceir.CeirPannelCode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(basePackages ="org.gl.ceir.CeirPannelCode")
public class App extends SpringBootServletInitializer
{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(App.class);
}

    public static void main( String[] args )
    {
    	System.out.println( "Hello World!" );
    	SpringApplication.run(App.class, args);
    	
    }
}
