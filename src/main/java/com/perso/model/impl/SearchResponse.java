package com.perso.model.impl;

import com.api.allocine.model.IAllocineObject;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.ISearchResponse;

public class SearchResponse implements ISearchResponse<IAllocineObject>{

	private IFeed<IAllocineObject> feed;
	
	public IFeed<IAllocineObject> getFeed() {
		return feed;
	}

	public void setFeed(IFeed<IAllocineObject> feed) {
		this.feed = feed;
	}
	
}
