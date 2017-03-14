package com.perso.security.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.hibernate.mapping.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.perso.repository.AccessTokenRepository;
import com.perso.repository.UserRepository;
import com.perso.security.entity.AccessToken;
import com.perso.security.entity.Role;
import com.perso.security.entity.User;

public class DaoUserService implements UserService {

	private UserRepository userRepository;
	private AccessTokenRepository accessTokenRepository;
	
	protected DaoUserService(){}
	private static final int TOKEN_VALIDITY = 1; //en jour
	
	
	private Logger logger = LoggerFactory.getLogger( DaoUserService.class );
	
	public DaoUserService( UserRepository userRepo, AccessTokenRepository accessTokenRepo ){
		this.userRepository = userRepo;
		this.accessTokenRepository = accessTokenRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return this.userRepository.loadUserByUsername(name);
	}

	@Override
	public User findUserByAccessToken(String token) {
		logger.debug("Access user by : " + token);
		AccessToken accessToken = this.accessTokenRepository.findAccessToken( token );
		if( accessToken == null ){
			return null;
		}
		
		if( !accessToken.isValid() ){
			logger.info("Token " + accessToken.getToken() + " is not valid anymore");
			//accessTokenRepository.delete(accessToken);
			return null;
		}
		
		return accessToken.getUser();
	}

	@Override
	public AccessToken createAccessToken(User user) {
		AccessToken accessToken = new AccessToken(user, UUID.randomUUID().toString());

		Calendar gc = GregorianCalendar.getInstance();
		gc.add( Calendar.DATE , TOKEN_VALIDITY);
		accessToken.setExpirationDate( gc.getTime() );
        
		accessToken.setActivated( true );
		
		return this.accessTokenRepository.saveAndFlush(accessToken);
	}
	
	public java.util.Set<Role> findUserInAuthorization(User user){
		return userRepository.findUser( user.getName() , user.getPassword() ).getAuthorities();
	}
	
	public AccessToken loginUser(User user){
		logger.debug("Login user by : " + user );
		User userInDataBase = userRepository.loginUser( user.getName() , user.getPassword() );
		if( userInDataBase != null ){
			AccessToken token = accessTokenRepository.findUserToken( userInDataBase.getId() );
			if( token != null && token.isValid() ){
				logger.info("User " + user.getName() + " use an existing token " + token.getToken() );
				return token;
			}
			logger.info("User " + user.getName() + " need new token." );
			return createAccessToken( userInDataBase );
		}
		return null;
	}
	
}
