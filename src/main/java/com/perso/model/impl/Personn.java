package com.perso.model.impl;

import com.perso.model.IPersonn;

public class Personn implements IPersonn {

	private String name = "UNKNOWN";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
