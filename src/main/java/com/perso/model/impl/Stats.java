package com.perso.model.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.allocine.model.IStats;

@Entity
public class Stats implements IStats{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int id;
	private double userRating;
	private double pressRating;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getUserRating() {
		return userRating;
	}
	public void setUserRating(double userRating) {
		this.userRating = userRating;
	}
	public double getPressRating() {
		return pressRating;
	}
	public void setPressRating(double pressRating) {
		this.pressRating = pressRating;
	}
	
	@Override
	public String toString() {
		return "Stats [id=" + id + ", userRating=" + userRating + ", pressRating=" + pressRating + "]";
	}
	
}
