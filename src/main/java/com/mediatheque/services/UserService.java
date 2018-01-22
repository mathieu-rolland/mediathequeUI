package com.mediatheque.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mediatheque.model.impl.AccessToken;
import com.mediatheque.model.impl.User;

public interface UserService extends UserDetailsService {

	public User findUserByAccessToken(String token);
	AccessToken createAccessToken( User user );
	public String generateActivationKeyForUSer(User u);
	
}
