package com.perso.model.impl;

import com.api.allocine.model.IFeed;
import com.api.allocine.model.ISearchResponse;

public class SearchResponse implements ISearchResponse{

	private Feed feed;
	
	public IFeed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	
}
