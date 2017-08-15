package com.mediatheque.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.api.allocine.model.IChapter;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;

@Entity
public class Season implements ISeason{

	@Id
	private int code;
	
	@ManyToOne(fetch = FetchType.LAZY , targetEntity = Serie.class)
	private ISerie serie;
	
	@OneToMany(fetch = FetchType.EAGER , targetEntity = Chapter.class)
	private Collection<IChapter> chapters;
	
	private String link;
	
	@OneToOne(fetch = FetchType.EAGER , targetEntity = Stats.class)
	private IStats stats;
	
	private int episodeCount;
	private int seasonNumber;
	
	public Season(){
		chapters = new ArrayList<IChapter>();
	}
	
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getLink() {
		return link;
	}

	@Override
	public IStats getStats() {
		return stats;
	}

	@Override
	public Collection<IChapter> getChapters() {
		return chapters;
	}

	@Override
	public void setChapters(List<IChapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public void addChapter(IChapter chapter) {
		this.chapters.add(chapter);
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setStats(IStats stats) {
		this.stats = stats;
	}

	public void setChapters(Collection<IChapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public String toString() {
		return "Season [code=" + code + ", link=" + link + ", stats=" + stats + ", chapters=" + chapters
				+ ", episodeCount=" + episodeCount + ", seasonNumber=" + seasonNumber + "]";
	}

}
