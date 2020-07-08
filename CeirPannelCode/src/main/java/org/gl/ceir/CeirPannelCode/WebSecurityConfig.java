package org.gl.ceir.CeirPannelCode;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http){
	try {
	http
	.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	.and()
	.formLogin().disable()
	.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").and().
	headers().frameOptions().sameOrigin().cacheControl().disable();
	}catch( Exception ex) {
	ex.printStackTrace();
	}
	}
}