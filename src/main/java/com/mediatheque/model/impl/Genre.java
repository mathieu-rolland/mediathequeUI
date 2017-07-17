package com.mediatheque.model.impl;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.api.allocine.model.IGenre;

@Entity
public class Genre implements IGenre {

	@Id
	private int code;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getCode() {
		return 0;
	}

	@Override
	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Genre [code = " + code + ", name=" + name + "]";
	}

	
}
