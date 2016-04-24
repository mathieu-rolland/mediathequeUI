package com.perso.manager.movies;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.perso.factory.IMediathequeFactory;
import com.perso.model.ILocalMovie;
import com.perso.model.IRegexParameter;
import com.perso.model.impl.Parameter;

public class MoviesLoader {

	private static Logger logger = Logger.getLogger(MoviesLoader.class);
	
	public static enum MOVIES_FILTER
	{
		KIND, YEAR
	}
	
	public static List<ILocalMovie> findMyMovies()
	{
		List<ILocalMovie> movies = new ArrayList<ILocalMovie>();
		return movies;
	}
	
	public static List<ILocalMovie> findMyMovies(MOVIES_FILTER filter)
	{
		List<ILocalMovie> movies = new ArrayList<ILocalMovie>();
		
		return movies;
	}
	
	public static List<IRegexParameter> generateRegexFromParameter(IMediathequeFactory factory, List<Parameter> allRegexParam ){
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
	
	public static List<ILocalMovie> loadFromDisk(String path , IMediathequeFactory factory, List<Parameter> include, List<Parameter> regex) throws IOException
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
					System.out.println(dir.getAbsolutePath() + "/" + name);
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
	
	public static void preformateMovieName( ILocalMovie movie, List<IRegexParameter> allRegex ){
		
		for( IRegexParameter regex : allRegex ){
			String originalTitle = movie.getTitle();
			movie.setTitle( regex.applyRegex( originalTitle ) );
		}
		
	}
	
	private static List<ILocalMovie> searchFile( File directory, IMediathequeFactory factory , FilenameFilter filter){
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
	
}
