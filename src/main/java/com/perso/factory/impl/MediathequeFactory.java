package com.perso.factory.impl;

import java.io.File;

import com.perso.factory.IMediathequeFactory;
import com.perso.model.ILocalMovie;
import com.perso.model.impl.LocalMovie;

public class MediathequeFactory implements IMediathequeFactory {

	@Override
	public ILocalMovie createLocalMovie(File file) {
		String name = file.getName();
		ILocalMovie movie = new LocalMovie();
		
		movie.setTitle( name );
		movie.setPath(file.getPath());
		
		return movie;
	}

	public static IMediathequeFactory createMediathequeFactory(){
		return new MediathequeFactory();
	}
	
}
