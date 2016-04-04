package com.perso.model;

import com.api.allocine.model.IMovie;

public interface ILocalMovie extends IMovie {

	public String getPath();
	public void setPath(String path);
	
}
