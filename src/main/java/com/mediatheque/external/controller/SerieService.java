package com.mediatheque.external.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.impl.Serie;

@RestController
@RequestMapping("/series")
public class SerieService {

	private Logger logger = Logger.getLogger(SerieService.class);
	
	@Autowired
	private IAllocineAPI api;
	
	/*@Autowired
	private IMediathequeFactory mediathequeFactory;
	
	@Autowired
	private ParametersRepository parameterRepository;
	
	@Autowired
	private CustomApplicationProperties properties;*/
	
	@RequestMapping("/search")
	public @ResponseBody Collection<ISerie> searchSerie(@RequestParam(value="q", defaultValue="default") String search){
		try {
			return api.searchSeries( search ).getFeed().getApiAllocineObject();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping( value = "/my-series/disk/synchronize" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean linkLocalSerieWithAlloCineSerie( 
			@RequestBody   Serie  serie
	){
		
		logger.debug( "Link serie " + serie );
			
		return false;
	}
	
	
	@RequestMapping("/my-series/db/")
	public @ResponseBody List<Serie> getMyMovies(){
		return null;
	}
	
}
