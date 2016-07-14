package com.perso.factory;

import java.io.File;

import com.perso.model.ILocalMovie;
import com.perso.model.IRegexParameter;

public interface IMediathequeFactory {

	public ILocalMovie createLocalMovie(File file);
	public IRegexParameter createRegexParameter();
	public ILocalMovie createLocalMovie();
	
}
