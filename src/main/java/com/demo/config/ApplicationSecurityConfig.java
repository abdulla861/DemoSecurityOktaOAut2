package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
			.antMatchers("/login/**","/oauth2/**","/oauth2**")
			.permitAll()
			.anyRequest().authenticated()
			.and().oauth2Login();
		
		return http.build();
	}
	
}
