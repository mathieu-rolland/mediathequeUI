package com.perso.model.impl;

import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;

public class MovieResponse implements IMovieResponse {

	private IMovie movie;

	public IMovie getMovie() {
		return movie;
	}

	public void setMovie(IMovie movie) {
		this.movie = movie;
	}
	
}
