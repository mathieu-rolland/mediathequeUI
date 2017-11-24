package com.mediatheque.model.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.api.allocine.model.ICasting;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;

@Entity
public class Serie implements ISerie{

	private int seasonCount;
	
	@OneToOne(targetEntity=Stats.class, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private IStats stats;
	
	@OneToOne(targetEntity=Poster.class, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private IPoster poster;
	
	@OneToOne(targetEntity=Casting.class, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private ICasting casting;
	private String title;
	private String description;

	private int year;
	
	@Id
	private int code;
	
	@OneToMany(fetch = FetchType.EAGER ,targetEntity=Season.class)
	@Fetch(FetchMode.JOIN)
	private Collection<ISeason> seasons;	
	
	public Serie(){
		this.seasons = new ArrayList<ISeason>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public IStats getIStats() {
		return stats;
	}

	public void setIStats(IStats stats) {
		this.stats = stats;
	}

	public IPoster getPoster() {
		return poster;
	}

	public void setPoster(IPoster poster) {
		this.poster = poster;
	}

	public ICasting getCasting() {
		return casting;
	}

	public void setCasting(ICasting casting) {
		this.casting = casting;
	}

	@Override
	public int getSeasonCount() {
		return seasonCount;
	}
	
	public void setSeasonCount(int seasonCount) {
		this.seasonCount = seasonCount;
	}

	@Override
	public String toString() {
		return "Serie [seasonCount=" + seasonCount + ", stats=" + stats + ", poster=" + poster + ", casting=" + casting
				+ ", title=" + title + ", description=" + description + ", year=" + year + ", code=" + code
				+ ", seasons=" + seasons + "]";
	}

	@Override
	public String getProductionStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProductionStatus(String productionStatus) {
		// TODO Auto-generated method stub
	}

	@Override
	public Collection<ISeason> getSeasons() {
		return seasons;
	}

	@Override
	public void setSeasons(Collection<ISeason> seasons) {
		this.seasons = seasons;
	}
	
}
