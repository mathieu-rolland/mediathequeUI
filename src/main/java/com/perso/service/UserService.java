package com.perso.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.perso.data.SecureData;
import com.perso.security.entity.AccessToken;
import com.perso.security.entity.Role;
import com.perso.security.entity.User;
import com.perso.security.service.DaoUserService;
import com.perso.security.service.MailService;

@RestController
@RequestMapping("/user")
public class UserService {
	
	@Autowired 
	DaoUserService userService;
	
	@Autowired
	MailService mailService;
	
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
	
	@RequestMapping("create-account")
	public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user){
		
		User createdUser = userService.createUser(user);
		if( createdUser == null ){
			logger.warn("Failed to create user " + user );
			return new ResponseEntity<User>(org.springframework.http.HttpStatus.OK);
		}
		mailService.sendActivationEmail( user );
		return new ResponseEntity<User>( SecureData.cleaningUser(createdUser) , org.springframework.http.HttpStatus.OK);
	}
	
	@RequestMapping("active-account")
	public @ResponseBody ResponseEntity<Boolean> activeAccount(@RequestParam(name = "q") String query){
		logger.info( "Request for activation user " );
		User u = userService.activateAccount( query );
		if( u != null){
			return new ResponseEntity<Boolean>( (Boolean) u.isActivated() , org.springframework.http.HttpStatus.OK ); 
		}
		return new ResponseEntity<Boolean>( (Boolean) false , org.springframework.http.HttpStatus.OK );
	}
	
	@RequestMapping("check-user")
	public @ResponseBody int checUserToCreate( @RequestBody User user ){
		return userService.checkUsers(user);
	}
	
	@RequestMapping("list-users")
	public @ResponseBody List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "ban-user" , method = RequestMethod.POST)
	public @ResponseBody User banUser( @RequestBody User u){
		return userService.toggleBanUser(u); 
	}
	
	@RequestMapping(value = "force-activation" , method = RequestMethod.POST)
	public @ResponseBody User forceActivation(@RequestBody User u){
		return userService.forceUserActivation(u); 
	}
	
	@RequestMapping( value = "revoke-authority" , method = RequestMethod.POST)
	public @ResponseBody User revokeAuthority(@RequestBody User u ,@RequestBody Role role){
		return userService.revokeAuthority(u, role); 
	}
	
}
