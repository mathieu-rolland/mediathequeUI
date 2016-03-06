package com.perso.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.model.IMovie;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping("/movies")
public class MoviesService {

	@Autowired
	private IAllocineAPI api;
	
	@RequestMapping("/search")
	public @ResponseBody List<IMovie> searchMovie(@RequestParam(value="q", defaultValue="default") String search){
		try {
			return api.searchMovies( search ).getFeed().getMovies();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
