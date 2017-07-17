package com.mediatheque.external.entrypoint;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.mediatheque.model.impl.User;
import com.mediatheque.services.UserService;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean{

	private UserService userService;
	private Logger logger = LoggerFactory.getLogger( AuthenticationTokenProcessingFilter.class );
	
	public AuthenticationTokenProcessingFilter(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		logger.info( "Received request" );
		
		HttpServletRequest httpRequest = this.getAsHttpRequest(request);
		
        String accessToken = this.extractAuthTokenFromRequest(httpRequest);
        if (null != accessToken) {
            User user = this.userService.findUserByAccessToken(accessToken);
            logger.info( "User found in database : " + user );
            if (null != user ){ 
            	if ( user.isActivated() && !user.isSuspended()) {
	            	logger.info("User is not null : " + user);
	                UsernamePasswordAuthenticationToken authentication =
	                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(authentication);
            	}else{
            		logger.info( "User " + user.getEmail() + " is not activated" );
            	}
            }
            else{
            	logger.info( "No user found for token : " + accessToken );
            }
        }else{
        	logger.info( "No access token." );
        }
        chain.doFilter(request, response);
    }

	private HttpServletRequest getAsHttpRequest(ServletRequest request)
    {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }

        return (HttpServletRequest) request;
    }

	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest)
    {
        /* Get token from header */
        String authToken = httpRequest.getHeader("X-Access-Token");

		/* If token not found get it from request parameter */
        if (authToken == null) {
            authToken = httpRequest.getParameter("token");
        }
        logger.info("Extracted token : " + authToken);
        return authToken;
    }
	
}
