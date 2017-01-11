package com.perso.model;


import java.util.Date;

import com.api.allocine.model.IMovie;

public interface ILocalMovie extends IMovie {

	public String getPath();
	public void setPath(String path);
	public void setSynchronized(boolean b);
	public boolean isSynchronized();
	public Date getAddedDate();
	public void setAddedDate(Date addedDate);
}
