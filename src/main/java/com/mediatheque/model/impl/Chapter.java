package com.mediatheque.model.impl;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.api.allocine.model.ICasting;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.ISeason;

@Entity
public class Chapter implements IChapter{

	@Id
	private int code;
	
	private int number;
	private String title;
	private String synopsis;
	private String date;
	
	
	@OneToOne(targetEntity=Casting.class, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private ICasting actors;
	private int parentSeasonCode;
	
	@OneToOne(targetEntity=Poster.class, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private IPoster poster;
	
	@ManyToOne(fetch = FetchType.LAZY , targetEntity = Season.class)
	private ISeason serie;

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public ICasting getActors() {
		return actors;
	}
	public void setActors(ICasting actors) {
		this.actors = actors;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public int getParentSeason() {
		return parentSeasonCode;
	}
	@Override
	public void setParentSeason(int code) {
		this.parentSeasonCode = code;
	}
	
	public int getParentSeasonCode() {
		return parentSeasonCode;
	}
	public void setParentSeasonCode(int parentSeasonCode) {
		this.parentSeasonCode = parentSeasonCode;
	}
	public IPoster getPoster() {
		return poster;
	}
	public void setPoster(IPoster poster) {
		this.poster = poster;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean equals(Chapter obj) {
		System.out.println("Compare chapter : " + obj.getCode() + " / " + this.getCode());
		return obj == null ? false : obj.getCode() == this.code;
	}
	
	/*public boolean equals( Object chapter ){
		return chapter == null ? false : this.code == chapter.getCode();
	}*/
	
	@Override
	public String toString() {
		return "Chapter [number=" + number + ", title=" + title + ", synopsis=" + synopsis + ", actors=" + actors
				+ ", code=" + code + ", parentSeasonCode=" + parentSeasonCode + ", poster=" + poster + ", date=" + date
				+ "]";
	}
	
}
