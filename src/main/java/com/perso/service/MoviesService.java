package com.perso.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.allocine.IAllocineAPI;

@RestController
public class MoviesService {

	@Autowired
	private IAllocineAPI api;
	
	@RequestMapping("/")
	public String printMovie(){
		try {
			api.searchMovies("Harry potter");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "call printMovie";
	}
	
}
