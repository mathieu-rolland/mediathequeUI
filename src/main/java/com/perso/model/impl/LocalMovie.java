package com.perso.model.impl;

import java.util.Collection;

import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IStats;
import com.perso.model.ILocalMovie;

public class LocalMovie implements ILocalMovie {

	private String title;
	private IRelease release;
	private int code;
	private IStats stats;
	private ICasting casting;
	private IPoster poster;
	private Collection<IAllocineLink> links;
	private String synapsys;
	private int year;
	private int duration;
	private String path;
	
	
	@Override
	public void setTitle(String asString) {
		this.title = asString ;
	}

	@Override
	public void setCode(int asInt) {
		this.code = asInt;
	}

	@Override
	public void setRelease(IRelease r) {
		this.release = r;
	}

	@Override
	public void setStatistiques(IStats s) {
		this.stats = s;
	}

	@Override
	public void setCasting(ICasting c) {
		this.casting = c;
	}

	@Override
	public void setPoster(IPoster p) {
		this.poster = p;
	}

	@Override
	public void setLink(Collection<IAllocineLink> links) {
		this.links = links;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public void setSynospis(String asString) {
		this.synapsys = asString;
	}

	@Override
	public int getDuration() {
		return duration;
	}

	@Override
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public void addLink(IAllocineLink link) {
		this.links.add( link );
	}

	@Override
	public String getSynospis() {
		return synapsys;
	}

	@Override
	public void setYear(int asInt) {
		this.year = year;
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
