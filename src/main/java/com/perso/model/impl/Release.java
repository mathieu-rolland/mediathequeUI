package com.perso.model.impl;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.allocine.model.IRelease;

@Entity
@Table(name="RELEASE_DATE")
public class Release implements IRelease {
	
	@Id
	private int id;
	private String releaseDate;
	
	public String toString(){
		return "releaseDate : " +  releaseDate;
	}
	
}
