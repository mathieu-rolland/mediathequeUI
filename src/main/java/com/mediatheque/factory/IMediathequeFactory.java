package com.mediatheque.factory;

import java.io.File;

import com.mediatheque.model.ILocalMovie;
import com.mediatheque.model.IRegexParameter;

public interface IMediathequeFactory {

	public ILocalMovie createLocalMovie(File file);
	public IRegexParameter createRegexParameter();
	public ILocalMovie createLocalMovie();
	
}
