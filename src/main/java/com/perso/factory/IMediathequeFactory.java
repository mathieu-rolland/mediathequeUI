package com.perso.factory;

import java.io.File;

import com.perso.model.ILocalMovie;

public interface IMediathequeFactory {

	public ILocalMovie createLocalMovie(File file);

}
