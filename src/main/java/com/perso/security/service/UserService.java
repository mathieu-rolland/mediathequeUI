package com.perso.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.perso.security.entity.AccessToken;
import com.perso.security.entity.User;

public interface UserService extends UserDetailsService {

	public User findUserByAccessToken(String token);
	AccessToken createAccessToken( User user );
	
}
