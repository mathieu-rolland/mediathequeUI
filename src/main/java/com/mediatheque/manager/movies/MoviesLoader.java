package com.mediatheque.manager.movies;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.api.allocine.model.IMovie;
import com.mediatheque.factory.IMediathequeFactory;
import com.mediatheque.model.ILocalMovie;
import com.mediatheque.model.IRegexParameter;
import com.mediatheque.model.impl.Parameter;
import com.mediatheque.repository.MovieRepository;

@Service
public class MoviesLoader {

	private static Logger logger = Logger.getLogger(MoviesLoader.class);
	
	public static enum MOVIES_FILTER
	{
		KIND, YEAR
	}
	
	public List<ILocalMovie> findMyMovies()
	{
		List<ILocalMovie> movies = new ArrayList<ILocalMovie>();
		return movies;
	}
	
	public List<ILocalMovie> findMyMovies(MOVIES_FILTER filter)
	{
		List<ILocalMovie> movies = new ArrayList<ILocalMovie>();
		
		return movies;
	}
	
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
	
	public List<ILocalMovie> loadFromDisk(String path , IMediathequeFactory factory, List<Parameter> include, List<Parameter> regex) throws IOException
	{
		
		File directory = new File(path);
		List<IRegexParameter> allRegex = generateRegexFromParameter(factory, regex);
		
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
		
		List<ILocalMovie> movies = searchFile(directory, factory, filter);
		
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
	
	private List<ILocalMovie> searchFile( File directory, IMediathequeFactory factory , FilenameFilter filter){
		List<ILocalMovie> movies = new ArrayList<ILocalMovie>();
		
		File[] files = directory.listFiles( filter );
		for(File f : files){
			if( f.isDirectory() ){
				movies.addAll( searchFile(f , factory, filter ) );
			}else{
				movies.add( factory.createLocalMovie( f ) );
			}
		}
		return movies;
	}

	public static List<ILocalMovie> findSynchronizedMovies( MovieRepository repository , List<ILocalMovie> movies )
	{
		for(ILocalMovie movie : movies ){
			IMovie synchronizedMovie = repository.findByPath( movie.getPath() );
			if (synchronizedMovie != null) movie.setSynchronized(true);
		}
		
		return movies;
	}
	
}
