package com.perso.data;

import com.perso.model.impl.Machine;
import com.perso.security.entity.User;

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
