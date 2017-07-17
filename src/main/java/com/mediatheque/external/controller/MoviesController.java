package com.mediatheque.external.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.mediatheque.db.dao.MovieDAO;
import com.mediatheque.model.ILocalMovie;
import com.mediatheque.model.impl.Machine;
import com.mediatheque.model.impl.Movie;
import com.mediatheque.services.FTPService;
import com.mediatheque.services.MoviesLoaderService;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	private Logger logger = Logger.getLogger(MoviesController.class);
	
	@Autowired
	private IAllocineAPI api;
	
	@Autowired
	private FTPService ftpService;

	@Autowired
	private MoviesLoaderService movieLoaderService;
	
	@Autowired
	private MovieDAO movieDao;
	
	@RequestMapping("/search")
	public @ResponseBody Collection<IMovie> searchMovie(@RequestParam(value="q", defaultValue="default") String search){
		try {
			return api.searchMovies( search ).getFeed().getApiAllocineObject();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/my-movies/disk")
	public @ResponseBody List<ILocalMovie> listFromDisk(@RequestParam(value="q", defaultValue="/dev/null") String search){
		
		logger.info("Start listing data from disk");
		
		try {
			List<ILocalMovie> result = movieLoaderService.loadFromDisk( search );
			if( result != null ){
				return movieLoaderService.findSynchronizedMovies( result );
			}
			
		} catch (IOException e) {
			logger.error("Failed to load movies from disk.");
			logger.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping( value = "/my-movies/disk/synchronize" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean linkLocalMovieWithAlloCineMovie( 
			@RequestBody   Movie  movie
	){
		
		logger.debug( "Link movie " + movie );
		
		if( movie != null  ){
			try {
				IMovieResponse response = api.getMovieDetails(movie);
				if( response != null ){
					movieDao.saveMovie( (ILocalMovie) response.getMovie() );
				}
				logger.debug( " Response : " + response.getMovie().getClass() );
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return false;
			}
		}
					
		return true;
	}
	
	
	@RequestMapping("/my-movies/db/")
	public @ResponseBody List<Movie> getMyMovies(){
		return movieDao.findAll();
	}
	
	@RequestMapping(value = "my-movie/machine/search", method = {RequestMethod.GET, RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ILocalMovie> getMoviesOnMachine( @RequestBody Machine machine ){
		logger.info( "Start to listing movies on machine " + machine + " located on " + machine.getPath() );
		return ftpService.listMovieOnFTPServer( machine );
	}
	
}
