package com.mediatheque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mediatheque.db.dao.UserDAO;
import com.mediatheque.db.repository.AccessTokenRepository;
import com.mediatheque.db.repository.UserRepository;
import com.mediatheque.external.entrypoint.AuthenticationTokenProcessingFilter;
import com.mediatheque.model.impl.Role;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	public AccessTokenRepository accessTokenRepo;
	
	@Bean
	public UserDAO createUserService(){
		return new UserDAO(userRepo, accessTokenRepo);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic().and().csrf().disable()
		.authorizeRequests()
			.antMatchers("/js","/user/login","/",
					"/user/active-account", 
					"/user/create-account",
					"/user/check-user").permitAll()
			.antMatchers("/machine/**","/parameters/**", "/user/**").hasRole( Role.ADMIN.toString() )
			.anyRequest().authenticated();
		http
			.addFilterBefore( new CORSFilter() , BasicAuthenticationFilter.class)
			.addFilterBefore( new AuthenticationTokenProcessingFilter( new UserDAO(userRepo, accessTokenRepo) ) , BasicAuthenticationFilter.class );
		super.configure(http);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder( 10 );
	}
	
}
