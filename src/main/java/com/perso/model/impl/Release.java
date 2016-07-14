package com.perso.model.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.allocine.model.IRelease;

@Entity
@Table(name="RELEASE_DATE")
public class Release implements IRelease {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int id;
	private String releaseDate;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Release [id=" + id + ", releaseDate=" + releaseDate + "]";
	}
	
}
