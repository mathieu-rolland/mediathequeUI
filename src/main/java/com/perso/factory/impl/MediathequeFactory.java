package com.perso.factory.impl;

import java.io.File;
import java.lang.reflect.Type;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.IAllocineAPI.RESPONSE_FORMAT;
import com.api.allocine.decod.IDecoder;
import com.api.allocine.decod.impl.AllocineDecoder;
import com.api.allocine.factory.IFactory;
import com.api.allocine.impl.AllocineAPI;
import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IResult;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.IStats;
import com.perso.factory.IMediathequeFactory;
import com.perso.model.ILocalMovie;
import com.perso.model.IRegexParameter;
import com.perso.model.impl.AllocineLink;
import com.perso.model.impl.Casting;
import com.perso.model.impl.Feed;
import com.perso.model.impl.Movie;
import com.perso.model.impl.Poster;
import com.perso.model.impl.RegexParameter;
import com.perso.model.impl.Release;
import com.perso.model.impl.Result;
import com.perso.model.impl.SearchResponse;
import com.perso.model.impl.Stats;

public class MediathequeFactory implements IMediathequeFactory, IFactory {

	@Override
	public ILocalMovie createLocalMovie(File file) {
		String name = file.getName();
		ILocalMovie movie = new Movie();
		
		movie.setTitle( name );
		movie.setPath(file.getPath());
		
		return movie;
	}

	public static IMediathequeFactory createMediathequeFactory(){
		return new MediathequeFactory();
	}

	@Override
	public IMovie createMovie() {
		return new Movie();
	}

	@Override
	public ICasting createCasting() {
		return new Casting();
	}

	@Override
	public IPoster createPoster() {
		return new Poster();
	}

	@Override
	public IAllocineLink createLink() {
		return new AllocineLink();
	}

	@Override
	public IFeed createFeed() {
		return new Feed();
	}

	@Override
	public IJsonResponse createJsonResponse() {
		return new SearchResponse();
	}

	@Override
	public IRelease createRelease() {
		return new Release();
	}

	@Override
	public IResult createResult() {
		return new Result();
	}

	@Override
	public IStats createStats() {
		return new Stats();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T create(Type type) {
		if( IMovie.class.equals(type) ) return (T) createMovie();
		if( IPoster.class.equals(type)) return (T) createPoster();
		if( IAllocineLink.class.equals(type)) return (T) createLink();
		if( ICasting.class.equals(type)) return (T) createCasting();
		if( IFeed.class.equals(type)) return (T) createFeed();
		if( IJsonResponse.class.equals(type)) return (T) createJsonResponse();
		if( IRelease.class.equals(type)) return (T) createRelease();
		if( IResult.class.equals(type) ) return (T) createResult();
		if( IStats.class.equals(type)) return (T) createStats();
		if( ISearchResponse.class.equals(type)) return (T) createSearchResponse();
		return null;
	}

	@Override
	public IAllocineAPI createSimpleAllocineAPI() {
		return new AllocineAPI( createAllocineDecoder() , RESPONSE_FORMAT.JSON );
	}

	private IDecoder createAllocineDecoder() {
		return new AllocineDecoder(this);
	}

	@Override
	public ISearchResponse createSearchResponse() {
		return new SearchResponse();
	}

	@Override
	public IRegexParameter createRegexParameter() {
		return new RegexParameter();
	}
	
}
