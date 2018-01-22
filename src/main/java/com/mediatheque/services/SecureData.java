package com.mediatheque.services;

import org.springframework.stereotype.Service;

import com.mediatheque.model.impl.Machine;
import com.mediatheque.model.impl.User;

@Service
public class SecureData {

	public User cleaningUser(User user){
		user.setPassword("");
		return user;
	}
	
	public Machine cleaningMachine(Machine machine){
		machine.setPassword("");
		return machine;
	}
	
}
