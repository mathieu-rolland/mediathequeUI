package com.mediatheque.model;


import java.util.Date;

import com.api.allocine.model.IMovie;

public interface ILocalMovie extends IMovie {

	public String getPath();
	public void setPath(String path);
	public void setSynchronized(boolean b);
	public boolean isSynchronized();
	public Date getLastSynchronizedDate();
	public void setLastSynchronizedDate(Date addedDate);
}
