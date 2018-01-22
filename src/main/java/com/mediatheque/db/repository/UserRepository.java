package com.mediatheque.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mediatheque.model.impl.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query ("select u from User u where u.name = ?")
	public User loadUserByUsername( String username );
	
	@Query ("select u from User u where u.name = ? and u.password = ?")
	public User findUser( String username, String password );
	
	@Query ("select u from User u where u.email = ?")
	public User findUserByEmail(String email);
	
	@Query ("select count(1) from User u where u.email = ?")
	public int getNumberOfUserWithEmail(String email);
	
	@Query ("select count(1) from User u where u.name = ?")
	public int getNumberOfUserWithName(String name);

}
