package com.perso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.perso.security.entity.AccessToken;
import com.perso.security.entity.User;
import com.perso.security.service.DaoUserService;

@RestController
@RequestMapping("/user")
public class UserService {
	
	@Autowired 
	DaoUserService userService;
	
	@RequestMapping( value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins="*", maxAge=3600)
	public @ResponseBody ResponseEntity<AccessToken> login(@RequestBody User user){
		AccessToken token = userService.loginUser(user);
		
		if( token == null ){
			return new ResponseEntity<AccessToken>(org.springframework.http.HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccessToken>( token , org.springframework.http.HttpStatus.ACCEPTED );
	}
	
	@RequestMapping("/logout")
	public AccessToken logout(User user){
		//TODO
		return null;
	}
	
}
