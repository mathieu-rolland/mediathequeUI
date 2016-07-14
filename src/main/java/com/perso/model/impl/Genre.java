package com.perso.model.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.api.allocine.model.IMovie;

@Entity
public class Genre {

	@Override
	public String toString() {
		return "Genre [name=" + name + ", movies=" + movies + "]";
	}

	@Id
	private String name = "N/A";

	@ManyToMany(targetEntity=Movie.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<IMovie> movies;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
