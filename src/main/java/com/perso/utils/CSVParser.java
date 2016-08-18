package com.perso.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IMovie;
import com.perso.factory.IMediathequeFactory;
import com.perso.model.ILocalMovie;

public class CSVParser {

	private static final int FILE_LOCATION_INDEX = 0;
	private static final int TITLE_MOVIE_INDEX = 1;
	
	public static List<IMovie> generateMovieFromFile( String file ,IMediathequeFactory factory ) throws FileNotFoundException, UnsupportedDataTypeException, IOException{
		
		File inputFile = new File( file );
		
		if( !inputFile.exists() ){
			throw new FileNotFoundException( file );
		}
		
		if( !inputFile.isFile() ) throw new UnsupportedDataTypeException("File " + file + " is not a file");
		
		return parseFile( inputFile , factory );
	}
	
	private static List<IMovie> parseFile( File file , IMediathequeFactory factory ) throws IOException{
		
		List<IMovie> movies = new ArrayList<IMovie>();
		BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream(file) ) );
		
		boolean asHeader = true;
		if( asHeader ) reader.readLine();
		
		while(reader.ready()){
			
			String[] parsedLine = reader.readLine().split(";");
			if( parsedLine.length > 1 ){
				String movieLocation = parsedLine[ FILE_LOCATION_INDEX ];
				String title = parsedLine[ TITLE_MOVIE_INDEX ];
				
				ILocalMovie movie = factory.createLocalMovie();
				movie.setPath( movieLocation );
				movie.setTitle( title );
				
				movies.add(movie);
			}
		}
		
		return movies;
	}
	
}
