package com.perso.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesService {

	@RequestMapping("/")
	public String printMovie(){
		return "call printMovie";
	}
	
}
