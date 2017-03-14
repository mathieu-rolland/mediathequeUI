package com.perso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perso.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query ("select u from User u where u.name = ?")
	public User loadUserByUsername( String username );
	
	@Query ("select u from User u where u.name = ? and u.password = ?")
	public User loginUser( String username, String password );
	
	@Query ("select u from User u where u.name = ? and u.password = ?")
	public User findUser( String username, String password );
	
	@Query ("select u from User u where u.email = ?")
	public User findUserByEmail(String email);
	
}
