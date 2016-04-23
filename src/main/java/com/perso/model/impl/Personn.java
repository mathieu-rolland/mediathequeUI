package com.perso.model.impl;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.perso.model.IPersonn;

@Entity
public class Personn implements IPersonn {

	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
