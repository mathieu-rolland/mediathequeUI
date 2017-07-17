package com.mediatheque.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mediatheque.model.impl.AccessToken;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

	@Query ("select at from AccessToken at where at.token = ?")
	public AccessToken findAccessToken( String accessToken );
	
	@Query ("select at from AccessToken at where at.user.id = ? and at.expirationDate > sysdate()")
	public AccessToken findUserToken( Long userId );

}
