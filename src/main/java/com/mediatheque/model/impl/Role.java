package com.mediatheque.model.impl;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{


	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
	
	private String authority;

	private Role( String authority ){
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return this.authority;
	}
	
}
