package com.mediatheque.model.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.allocine.model.IAllocineLink;

@Entity
public class AllocineLink implements IAllocineLink{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int id;
	private String rel;
	private String href;
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return "AllocineLink [id=" + id + ", rel=" + rel + ", href=" + href + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
