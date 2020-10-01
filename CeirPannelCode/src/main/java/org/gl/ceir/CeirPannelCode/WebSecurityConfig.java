package org.gl.ceir.CeirPannelCode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http){
	try {
	http
	.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	.and()
	.sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.NEVER).invalidSessionUrl("/login").and()
	.formLogin().disable()
	.logout().logoutUrl("/logout").permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID").and().
	headers().frameOptions().sameOrigin().cacheControl().disable().and()
	.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}catch( Exception ex) {
	ex.printStackTrace();
	}
	}
	
	
	static class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl{
		@Override
		public void handle(HttpServletRequest request,
		HttpServletResponse response, AccessDeniedException accessDeniedException)
		throws IOException, ServletException {
		if (accessDeniedException instanceof MissingCsrfTokenException
		|| accessDeniedException instanceof InvalidCsrfTokenException) {

		response.sendRedirect(request.getContextPath()+"/login");
		}

		super.handle(request, response, accessDeniedException);

		}
		}


	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
	return new CustomAccessDeniedHandler();
	}
}