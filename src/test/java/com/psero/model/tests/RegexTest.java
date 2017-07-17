package com.psero.model.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mediatheque.factory.IMediathequeFactory;
import com.mediatheque.factory.impl.MediathequeFactory;
import com.mediatheque.model.ILocalMovie;
import com.mediatheque.model.IParameter;
import com.mediatheque.model.IRegexParameter;
import com.mediatheque.model.impl.Movie;
import com.mediatheque.model.impl.Parameter;
import com.mediatheque.services.MoviesLoaderService;

import junit.framework.TestCase;

public class RegexTest extends TestCase {

	private IMediathequeFactory factory = new MediathequeFactory();
	private String regex = "([a-zA-Z0-9_ ]*)([\\.])*([a-zA-Z0-9_ ]*)";
	private String movieTitle = "The.Walking.Dead.S06E15.VOSTFR WEB-DL.DD5.1.H.264-Visual";
	private String keepValue = "$1 $3";
	
	@Autowired
	private MoviesLoaderService movieLoader;
	
	@Test
	public void testParameterRegex(){
		
		IRegexParameter param = factory.createRegexParameter();
		param.setRegex(regex);
		param.setReplace(keepValue);
		
		String output = param.applyRegex(movieTitle);
		assertNotSame(movieTitle	, output);
		assertEquals("The Walking Dead S06E15 VOSTFR WEB -DL DD5 1 H 264 -Visual", output);
	}
	
	@Test
	public void testParsingParam(){
		
		IParameter param = new Parameter();
		param.setName("movie.regex");
		param.setValue( regex + ", " + keepValue );
		
		List<Parameter> params = new ArrayList<Parameter>();
		params.add( (Parameter) param );
		
		List<IRegexParameter> regex = movieLoader.generateRegexFromParameter(factory, params);
		String output = regex.get(0).applyRegex(movieTitle);
		
		assertNotSame(movieTitle	, output);
		assertEquals("The Walking Dead S06E15 VOSTFR WEB -DL DD5 1 H 264 -Visual", output);
	}

	@Test
	public void testIterationOnTitle(){
		
		IParameter param = new Parameter();
		param.setName("movie.regex");
		param.setValue( regex + ", " + keepValue );
	
		ILocalMovie movie = new Movie();
		movie.setTitle( movieTitle );
		List<Parameter> params = new ArrayList<Parameter>();
		params.add( (Parameter) param );
		
		List<IRegexParameter> regex = movieLoader.generateRegexFromParameter(factory, params);
		movieLoader.preformateMovieName(movie, regex);
		
		assertNotSame(movieTitle , movie.getTitle());
		assertEquals("The Walking Dead S06E15 VOSTFR WEB -DL DD5 1 H 264 -Visual", movie.getTitle());
	}
	
}
