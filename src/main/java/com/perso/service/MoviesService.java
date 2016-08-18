package com.perso.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.multipart.MultipartFile;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.perso.config.CustomApplicationProperties;
import com.perso.factory.IMediathequeFactory;
import com.perso.manager.movies.MoviesLoader;
import com.perso.model.ILocalMovie;
import com.perso.model.impl.Movie;
import com.perso.repository.MovieRepository;
import com.perso.repository.ParametersRepository;
import com.perso.utils.CSVParser;

@RestController
@RequestMapping("/movies")
public class MoviesService {

	private Logger logger = Logger.getLogger(MoviesService.class);
	
	@Autowired
	private IAllocineAPI api;
	
	@Autowired
	private IMediathequeFactory mediathequeFactory;
	
	@Autowired
	private ParametersRepository parameterRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private CustomApplicationProperties properties;
	
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
		logger.info("Start listing data from disk");
		try {
			List<ILocalMovie> result = MoviesLoader.loadFromDisk(	search, 
												mediathequeFactory , 
												parameterRepository.findByName("movie.include") ,
												parameterRepository.findByName("movie.regex") );
			
			return MoviesLoader.findSynchronizedMovies( movieRepository ,  result );
			
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
		
		System.out.println( "Link movie " + movie );
		
		if( movie != null  ){
			try {
				IMovieResponse response = api.getMovieDetails(movie);
				if( response != null ){
					ILocalMovie movieResponse = (ILocalMovie) response.getMovie();
					movieResponse.setPath( movie.getPath() );
					movieRepository.saveAndFlush( (Movie) movieResponse );
				}
				System.out.println( " Response : " + response.getMovie().getClass() );
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return false;
			}
		}
					
		return true;
	}
	
	
	@RequestMapping("/my-movies/db/")
	public @ResponseBody List<Movie> getMyMovies(){
		return movieRepository.findAll();
	}
	
	@RequestMapping(value = "/csv", method = RequestMethod.POST )
	public @ResponseBody List<IMovie> handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
		if ( file != null ){
			logger.info("File " + file.getName() + " is not empty and download started in " + properties.getDownloadPath() + ".");
	        if (!file.isEmpty()) {
	        	
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File( properties.getDownloadPath() + File.separator + name )));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	            
	            try {
					return CSVParser.generateMovieFromFile( properties.getDownloadPath() + File.separator + name , mediathequeFactory);
				} catch (IOException e) {
					e.printStackTrace();
				}
	            
	        } else {
	        	logger.info("File " + file.getName() + " is empty.");
	        }
		}
		logger.info("End of file upload");
		return null;
    }
	
	
}
