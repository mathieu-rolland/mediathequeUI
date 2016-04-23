package com.perso.model.impl;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.api.allocine.model.IPoster;

@Entity
public class Poster implements IPoster {

	@Id
	private int id;
	private String path;
	private String href;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return "Poster [path=" + path + ", href=" + href + "]";
	}
	
}
