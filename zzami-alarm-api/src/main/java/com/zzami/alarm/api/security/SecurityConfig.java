package com.zzami.alarm.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  JwtAccessDeniedHandler jwtAccessDeniedHandler;
  
  @Autowired
  EntryPointUnAuthorizedHandler authenticationEntryPoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.exceptionHandling()
					.accessDeniedHandler(jwtAccessDeniedHandler)
					.authenticationEntryPoint(authenticationEntryPoint)
			.and()
				.antMatcher("/api/**")
				.authorizeRequests()
					.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
					.antMatchers("/api/o/**").permitAll()
					.antMatchers("/api/p/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MANAGER')")
					.anyRequest().denyAll()
			.and()
				.formLogin()
					.disable();
		
		http
			.addFilterBefore( new JwtTokenFilter() , UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/web/**");
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
