package com.perso.model.impl;

import java.util.ArrayList;

import com.api.allocine.model.IFeed;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IResult;

public class Feed implements IFeed{

	private int page;
	private int count;
	private ArrayList<IResult> results;
	private int totalResults;
	private ArrayList<IMovie> movie;
	
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

	public ArrayList<IResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<IResult> results) {
		this.results = results;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public ArrayList<IMovie> getMovies() {
		return movie;
	}

	public void setMovies(ArrayList<IMovie> movie) {
		this.movie = movie;
	}

	public String toString(){
		return "Page : " + page  + " nb movie(s) : " + (movie == null ? "null" : movie.size());
	}
	
}
