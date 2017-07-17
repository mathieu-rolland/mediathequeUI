package com.mediatheque.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.api.allocine.model.ICasting;

@Entity
public class Casting implements ICasting {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int id;
	
//	@ManyToMany(targetEntity=Personn.class)
	@ElementCollection
	@CollectionTable( name = "personn" , joinColumns = @JoinColumn(name = "name"))
	private List<String> actors;
	
//	@ManyToMany(targetEntity=Personn.class)
	@ElementCollection
	@CollectionTable( name = "personn" , joinColumns = @JoinColumn(name = "name"))
	private List<String> directors;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Casting(){
		actors = new ArrayList<String>();
		directors = new ArrayList<String>();
	}

	public List<String> getActeurs() {
		return actors;
	}

	public void setActeurs(List<String> acteurs) {
		this.actors = acteurs;
	}

	public void addActor(String actor){
		actors.add( actor );
	}

	public void addDirector(String director){
		directors.add( director );
	}
	
	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	@Override
	public String toString() {
		return "Casting [id=" + id + ", actors=" + actors + ", directors=" + directors + "]";
	}
	
}
