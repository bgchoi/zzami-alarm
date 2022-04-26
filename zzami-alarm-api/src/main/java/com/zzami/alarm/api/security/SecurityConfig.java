package com.zzami.alarm.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import com.zzami.alarm.api.service.AuthUserDetailService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    EntryPointUnAuthorizedHandler authenticationEntryPoint;
    
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    AuthUserDetailService userDetailService;

    @Autowired
    UserAuthenticatioProvider userAuthenticatioProvider;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
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
					.antMatchers("/api/p/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
					.antMatchers("/api/m/**").access("hasAnyRole('ROLE_ADMIN')")
					.anyRequest().denyAll()
			.and()
				.formLogin()
					.disable();
		
		http
			.addFilterBefore( new JwtTokenFilter(jwtTokenProvider) , UsernamePasswordAuthenticationFilter.class);
		//@formatter:on
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
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(userAuthenticatioProvider) // 프로바이더 
            .userDetailsService(userDetailService) // 서비스 
            .passwordEncoder(passwordEncoder()); // 패스워드인코더 
    }
    
    @Bean
    public String test(PasswordEncoder passwordEncoder) {
        System.err.println(">>" + passwordEncoder.encode("1111aaaa"));
        return "OK";
    }
     
}
