package com.perso.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.allocine.IAllocineAPI;

@RestController
@RequestMapping("/movies")
public class MoviesService {

	@Autowired
	private IAllocineAPI api;
	
	@RequestMapping("/search")
	public String searchMovie(@RequestParam(value="q", defaultValue="test") String search){
		try {
			return api.searchMovies( search ).getFeed().toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "call printMovie";
	}
	
}
