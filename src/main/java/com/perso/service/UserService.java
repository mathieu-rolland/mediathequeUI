package com.perso.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.perso.data.SecureData;
import com.perso.security.entity.AccessToken;
import com.perso.security.entity.Role;
import com.perso.security.entity.User;
import com.perso.security.service.DaoUserService;

@RestController
@RequestMapping("/user")
public class UserService {
	
	@Autowired 
	DaoUserService userService;
	
	Logger logger = LoggerFactory.getLogger(User.class);
	
	@RequestMapping( value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody ResponseEntity<AccessToken> login(@RequestBody User user){
		AccessToken token = userService.loginUser(user);
		
		if( token == null ){
			logger.warn("Fail to login user with empty credential");
			return new ResponseEntity<AccessToken>(org.springframework.http.HttpStatus.FORBIDDEN);
		}
		token.setUser( SecureData.cleaningUser( token.getUser() ) );
		return new ResponseEntity<AccessToken>( token , org.springframework.http.HttpStatus.OK );
	}
	
	@RequestMapping( value = "/authorization", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody ResponseEntity<Set<Role>> getUserAuthorization(@RequestBody User user){
		return new ResponseEntity<Set<Role>> ( userService.findUserInAuthorization( user ) , org.springframework.http.HttpStatus.OK );
	}
	
	@RequestMapping("/logout")
	public AccessToken logout(User user){
		//TODO
		return null;
	}
	
}
