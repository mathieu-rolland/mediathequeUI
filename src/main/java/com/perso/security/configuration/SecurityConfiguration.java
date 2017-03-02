package com.perso.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.perso.repository.AccessTokenRepository;
import com.perso.repository.UserRepository;
import com.perso.security.entity.Role;
import com.perso.security.entryPoint.AuthenticationTokenProcessingFilter;
import com.perso.security.service.DaoUserService;
import com.perso.spring.cors.CORSFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	public AccessTokenRepository accessTokenRepo;
	
	@Bean
	public DaoUserService createUserService(){
		return new DaoUserService(userRepo, accessTokenRepo);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.httpBasic().and().csrf().disable()
		.authorizeRequests()
			.antMatchers("/js","/user/login","/").permitAll()
			.antMatchers("/machine","/parameters").hasRole( Role.ADMIN.toString() )
			.anyRequest().authenticated();
		http
			.addFilterBefore( new CORSFilter() , BasicAuthenticationFilter.class)
			.addFilterBefore( new AuthenticationTokenProcessingFilter( new DaoUserService(userRepo, accessTokenRepo) ) , BasicAuthenticationFilter.class );
		super.configure(http);
	}
	
}
