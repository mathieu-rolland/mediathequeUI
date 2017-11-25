package com.mediatheque.external.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IChapterResponse;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.ISerieResponse;
import com.api.allocine.model.impl.Series;

@RestController
@RequestMapping("/series")
public class SeriesController {

	private Logger logger = Logger.getLogger(SeriesController.class);
	
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
	
	@RequestMapping("/details")
	public @ResponseBody ISerie getSerieDetails( @RequestBody ISerie serie){
		
		ISerieResponse response;
		try {
			
			response = api.getSerieDetails(serie);
			Iterator<ISeason> stmp = response.getSerie().getSeasons().iterator();
			while( stmp.hasNext() ){
				ISeason s1 = stmp.next();
				System.out.println("Nb chapters : " + s1.getChapters().size() + ", code : " + s1.getCode() + ", season : " + s1.getSeasonNumber() );
			}
			
			Collection<ISeason> enhancedSeason = new ArrayList<ISeason>();
			if( response != null && response.getSerie().getSeasonCount() > 0 ){
				for(ISeason s : response.getSerie().getSeasons() ){
					ISeason tmp = api.getSeasonDetails( s ).getSeason();
					enhancedSeason.add( tmp );
				}
				response.getSerie().setSeasons( enhancedSeason );
			}
			
			return response != null ? response.getSerie() : null;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping( value = "/details/chapter" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody IChapter getSerieDetails( @RequestBody IChapter chapter){
		
		IChapterResponse response;
		try {
			response = api.getChapterDetails( chapter );
			System.out.println( response != null ? response.getChapter() : "null" );
			return response.getChapter();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping( value = "/my-series/disk/synchronize" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean linkLocalSerieWithAlloCineSerie( 
			@RequestBody   Series  serie
	){
		
		logger.debug( "Link serie " + serie );
			
		return false;
	}
	
	@RequestMapping("/my-series/db/")
	public @ResponseBody List<Series> getMySeries(){
		return null;
	}
	
}
