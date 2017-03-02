package com.perso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perso.security.entity.AccessToken;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

	@Query ("select at from AccessToken at where at.token = ?")
	public AccessToken findAccessToken( String accessToken );
	
}
