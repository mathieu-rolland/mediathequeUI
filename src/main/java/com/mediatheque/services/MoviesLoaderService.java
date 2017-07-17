package com.mediatheque.services;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.allocine.model.IMovie;
import com.mediatheque.db.dao.MovieDAO;
import com.mediatheque.db.dao.ParameterDao;
import com.mediatheque.factory.IMediathequeFactory;
import com.mediatheque.model.ILocalMovie;
import com.mediatheque.model.IRegexParameter;
import com.mediatheque.model.impl.Parameter;

@Service
public class MoviesLoaderService {

	@Autowired
	private MovieDAO movieDao;
	
	@Autowired
	private ParameterDao parameterDao;
	
	@Autowired
	private IMediathequeFactory mediathequeFactory;
	
	private static Logger logger = Logger.getLogger(MoviesLoaderService.class);
	
	public List<IRegexParameter> generateRegexFromParameter(IMediathequeFactory factory, List<Parameter> allRegexParam ){
		List<IRegexParameter> allRegex = new ArrayList<IRegexParameter>();
		
		for(Parameter param : allRegexParam){
			
			IRegexParameter regex = factory.createRegexParameter();
			String[] parsedParam = param.getValue().split(",");
			regex.setRegex( parsedParam[0].trim() );
			regex.setReplace( parsedParam[1].trim() );
			allRegex.add(regex);
			
		}
		
		return allRegex;
	}
	
	public List<ILocalMovie> loadFromDisk(String path ) throws IOException
	{
		
		List<Parameter> include = parameterDao.findByName( "movie.include" );
		List<Parameter> regex = parameterDao.findByName( "movie.regex" );
		
		File directory = new File(path);
		List<IRegexParameter> allRegex = generateRegexFromParameter(mediathequeFactory, regex);
		
		if( ! directory.exists() || ! directory.isDirectory() )
		{
			logger.warn("The given path (" + path + ") does not exist or is not a directory");
			return null;
		}
		
		final List<String> extensions = new ArrayList<String>();
		for(Parameter param : include){
			extensions.add(param.getValue());
		}
		
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				File f = new File( dir.getAbsoluteFile() + File.separator + name );
				if( f.isDirectory() ) return true;
				
				for(String extension : extensions ){
					logger.debug(dir.getAbsolutePath() + "/" + name);
					if( name.endsWith(extension) ){
						return true;
					}
				}
				return false;
			}
		};
		
		List<ILocalMovie> movies = searchFile(directory, filter);
		
		for(ILocalMovie movie : movies){
			preformateMovieName(movie, allRegex);
		}
		
		return movies;
	}
	
	public void preformateMovieName( ILocalMovie movie, List<IRegexParameter> allRegex ){
		
		for( IRegexParameter regex : allRegex ){
			String originalTitle = movie.getTitle();
			movie.setTitle( regex.applyRegex( originalTitle ) );
		}
		
	}
	
	private List<ILocalMovie> searchFile( File directory , FilenameFilter filter){
		List<ILocalMovie> movies = new ArrayList<ILocalMovie>();
		
		File[] files = directory.listFiles( filter );
		for(File f : files){
			if( f.isDirectory() ){
				movies.addAll( searchFile(f, filter ) );
			}else{
				movies.add( mediathequeFactory.createLocalMovie( f ) );
			}
		}
		return movies;
	}

	public List<ILocalMovie> findSynchronizedMovies( List<ILocalMovie> movies )
	{
		for(ILocalMovie movie : movies ){
			IMovie synchronizedMovie = movieDao.findByPath( movie.getPath() );
			if (synchronizedMovie != null) movie.setSynchronized(true);
		}
		
		return movies;
	}
	
}
