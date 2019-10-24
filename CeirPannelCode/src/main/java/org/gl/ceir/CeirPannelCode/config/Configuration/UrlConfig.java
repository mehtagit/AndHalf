package org.gl.ceir.CeirPannelCode.config.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
public class UrlConfig implements WebMvcConfigurer {

		

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/G:/**").addResourceLocations("file:/home/ubuntu/CEIR/G:/").setCachePeriod(0)
				.resourceChain(true).addResolver(new PathResourceResolver());
	}
	
	
}
