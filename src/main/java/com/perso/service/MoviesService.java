package com.perso.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.model.IMovie;
import com.perso.factory.IMediathequeFactory;
import com.perso.manager.movies.MoviesLoader;
import com.perso.model.ILocalMovie;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*" , methods = RequestMethod.GET )
public class MoviesService {

	private Logger logger = Logger.getLogger(MoviesService.class);
	
	@Autowired
	private IAllocineAPI api;
	
	@Autowired
	private IMediathequeFactory mediathequeFactory;
	
	@RequestMapping("/search")
	public @ResponseBody List<IMovie> searchMovie(@RequestParam(value="q", defaultValue="default") String search){
		try {
			return api.searchMovies( search ).getFeed().getMovies();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/my-movies/disk")
	public @ResponseBody List<ILocalMovie> listFromDisk(@RequestParam(value="q", defaultValue="/dev/null") String search){
		try {
			return MoviesLoader.loadFromDisk(search, mediathequeFactory);
		} catch (IOException e) {
			logger.error("Failed to load movies from disk.");
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
}
