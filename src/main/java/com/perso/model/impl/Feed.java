package com.perso.model.impl;

import java.util.Collection;

import com.api.allocine.model.IFeed;
import com.api.allocine.model.IResult;

public class Feed<T> implements IFeed<T>{

	private int page;
	private int count;
	private Collection<IResult> results;
	private int totalResults;
	private Collection<T> apiAllocineObject;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Collection<IResult> getResults() {
		return results;
	}

	public void setResults(Collection<IResult> results) {
		this.results = results;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public String toString(){
		return "Page : " + page  + " nb movie(s) : " + (apiAllocineObject == null ? "null" : apiAllocineObject.size());
	}

	@Override
	public void setApiAllocineObject(Collection<T> apiAllocineObject) {
		this.apiAllocineObject = apiAllocineObject;
	}

	@Override
	public Collection<T> getApiAllocineObject() {
		return apiAllocineObject;
	}

}
