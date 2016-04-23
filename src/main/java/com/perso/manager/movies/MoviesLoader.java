package com.perso.manager.movies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.perso.factory.IMediathequeFactory;
import com.perso.model.ILocalMovie;

public class MoviesLoader {

	public static enum MOVIES_FILTER
	{
		KIND, YEAR
	}
	
	private static Logger logger = Logger.getLogger(MoviesLoader.class);
	
	
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
	
	public static List<ILocalMovie> loadFromDisk(String path , IMediathequeFactory factory) throws IOException
	{
		List<ILocalMovie> movies = new ArrayList<ILocalMovie>();
		
		File directory = new File(path);
		
		if( ! directory.exists() || ! directory.isDirectory() )
		{
			logger.warn("The given path (" + path + ") does not exist or is not a directory");
			return null;
		}
		
		File[] files = directory.listFiles();
		for(File f : files){
			if( f.isDirectory() ){
				movies.addAll( loadFromDisk(f.getPath() , factory ) );
			}else{
				movies.add( factory.createLocalMovie( f ) );
			}
		}
		
		return movies;
	}
	
}
