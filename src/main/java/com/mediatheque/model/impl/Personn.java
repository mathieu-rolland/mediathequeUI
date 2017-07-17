package com.mediatheque.model.impl;

import com.mediatheque.model.IPersonn;

public class Personn implements IPersonn {

	private String name = "UNKNOWN";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
