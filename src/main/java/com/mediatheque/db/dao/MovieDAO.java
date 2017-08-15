package com.mediatheque.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.allocine.model.IMovie;
import com.mediatheque.db.repository.MovieRepository;
import com.mediatheque.model.ILocalMovie;
import com.mediatheque.model.impl.Movie;

@Repository
public class MovieDAO {

	@Autowired
	private MovieRepository movieRepository;
	
	public void saveMovie(ILocalMovie movie){
		if( movie != null ){
			ILocalMovie movieResponse = movie;
			movieResponse.setLastSynchronizedDate( new Date() );
			movieResponse.setPath( movie.getPath() );
			movieRepository.saveAndFlush( (Movie) movieResponse );
		}
	}
	
	public List<Movie> findAll(){
		return movieRepository.findAll();
	}

	public IMovie findByPath(String path) {
		return movieRepository.findByPath(path);
	}
	
}
