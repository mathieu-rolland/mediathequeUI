package com.perso.security.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
	private static final int ACTIVATION_VALIDITY = 1;
	private static final String DATE_FORMAT = "yyyyMMddHHmmss";
	
	
	private SimpleDateFormat dateFormater;
	
	private Logger logger = LoggerFactory.getLogger( DaoUserService.class );
	
	public DaoUserService( UserRepository userRepo, AccessTokenRepository accessTokenRepo ){
		
		this.userRepository = userRepo;
		this.accessTokenRepository = accessTokenRepo;
		
		dateFormater = new SimpleDateFormat( DATE_FORMAT );
		
	}

	
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
	
	public User createUser(User user){
		
		if( user != null ){
			
			Set<Role> roles = new HashSet<Role>();
			roles.add(Role.USER);
			
			String activationKey = UUID.randomUUID().toString();
			user.setActivationKey( Base64.getEncoder().encodeToString( activationKey.getBytes() ) );
			user.setRoles( roles );
			return userRepository.saveAndFlush( user );
			
		}
		
		return null;
	}
	
	public User activateAccount( String userParam ){
		
		logger.info("Activation of key " + userParam);
		
		if( userParam != null ){
			
			String decodedValue = new String(Base64.getDecoder().decode( userParam.getBytes() ));
			
			logger.info("decoded key " + decodedValue);
			
			String[] splittedKey = decodedValue.split(";");
			
			if( splittedKey != null && splittedKey.length == 3 ){
				
				String activationKey = splittedKey[0];
				String email = splittedKey[1];
				String dateLimit = splittedKey[2];
				
				Date limit;
				try {
					limit = dateFormater.parse( dateLimit );
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
				
				logger.info("activationKey " + activationKey);
				logger.info("email " + email);
				logger.info("Date " + dateFormater.format(limit) + "/from : " + dateLimit);

				if( limit.getTime() < new Date().getTime() ){
					logger.warn("Activation date has expired");
					return null;
				}
				
				User user = userRepository.findUserByEmail( email );
				
				if( user != null && activationKey.equals( user.getActivationKey()) ){
					user.setActivated( true );
					return userRepository.save(user);
				}
			}
		}
		return null;
	}
	
	public String generateActivationKeyForUSer( User user ){
		
		if( user != null ){
			
			Calendar gc = GregorianCalendar.getInstance();
			gc.add( Calendar.DATE , ACTIVATION_VALIDITY);
			
			String generatedKey = user.getActivationKey();
			generatedKey += ";" + user.getEmail();
			generatedKey += ";" + dateFormater.format( gc.getTime() );
		
			return new String(Base64.getEncoder().encodeToString(generatedKey.getBytes()));
		
		}
		
		return null;
	}
	
}
