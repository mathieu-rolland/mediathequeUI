package com.perso.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IGenre;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IStats;
import com.perso.model.ILocalMovie;

@Entity
@Table(name="MOVIE")
public class Movie implements IMovie, ILocalMovie{

	@Id
	private int code;
	private String originalTitle;
	private String title;
	
	@Transient
	private List<String> keywords;
	
	private int year;
	
	@OneToOne(targetEntity=Release.class, fetch=FetchType.LAZY)
	private IRelease releaseDate;
	
	@OneToOne(targetEntity=Casting.class)
	private ICasting casting;
	
	@OneToOne(targetEntity=Stats.class)
	private IStats statistiques;
	
	@OneToOne(targetEntity=Poster.class)
	private IPoster poster;
	
	@ManyToMany(targetEntity=AllocineLink.class)
	private Collection<IAllocineLink> links;
	
	@ManyToMany(targetEntity=Genre.class)
	private List<IGenre> genre;
	
	private String synospis;
	private int duration;
	private String path;
	
	public Movie(){
		links = new ArrayList<IAllocineLink>();
		releaseDate = new Release();
//		casting = new Casting();
		statistiques = new Stats();
		poster = new Poster();
		setKeywords(new ArrayList<String>());
	}
	
	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public IRelease getRelease() {
		return releaseDate;
	}
	
	public void setRelease(IRelease r) {
		this.releaseDate = r;
	}
	
	public ICasting getCasting() {
		return casting;
	}
	
	public void setCasting(ICasting casting) {
		this.casting = casting;
	}
	
	public IStats getStatistiques() {
		return statistiques;
	}
	
	public void setStatistiques(IStats s) {
		this.statistiques = s;
	}
	
	public IPoster getPoster() {
		return poster;
	}
	
	public void setPoster(IPoster p) {
		this.poster = p;
	}
	
	public Collection<IAllocineLink> getLink() {
		return links;
	}
	
	public void setLink(Collection<IAllocineLink> link) {
		this.links = link;
	}
	
	public List<IGenre> getGenre() {
		return genre;
	}

	public void setGenre(List<IGenre> genre) {
		this.genre = genre;
	}

	public String getSynospis() {
		return synospis;
	}

	public void setSynospis(String synospis) {
		this.synospis = synospis;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void addLink( IAllocineLink link ){
		this.links.add(link);
	}

	@Override
	public String toString() {
		return "Mediatheque Movie [code=" + code + ", originalTitle=" + originalTitle
				+ ", title=" + title + ", keywords=" + keywords + ", year="
				+ year + ", release=" + releaseDate + ", casting=" + casting
				+ ", statistiques=" + statistiques + ", poster=" + poster
				+ ", links=" + links + ", genre=" + genre + ", synospis="
				+ synospis + ", duration=" + duration + "]";
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}
	
}
