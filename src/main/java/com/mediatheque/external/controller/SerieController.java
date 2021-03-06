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

@RestController
@RequestMapping("/series")
public class SerieController {

	private Logger logger = Logger.getLogger(SerieController.class);
	
	@Autowired
	private IAllocineAPI api;
	
	@RequestMapping("/search")
	public @ResponseBody Collection<ISerie> searchSerie(@RequestParam(value="q", defaultValue="default") String search){
		try {
			return api.searchSeries( search ).getFeed().getApiAllocineObject();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
