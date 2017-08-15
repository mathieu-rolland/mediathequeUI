package com.perso.data;

import com.mediatheque.model.impl.Machine;
import com.mediatheque.model.impl.User;

public class SecureData {

	public static User cleaningUser(User user){
		user.setPassword("");
		return user;
	}
	
	public static Machine cleaningMachine(Machine machine){
		machine.setPassword("");
		return machine;
	}
		
}
