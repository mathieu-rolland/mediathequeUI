package com.mediatheque.external.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediatheque.db.dao.DAOCategory;
import com.mediatheque.model.impl.Genre;

@RestController
@RequestMapping("/genre")
public class CategoryController {

	@Autowired 
	private DAOCategory daoCategory;
	
	@RequestMapping("/all")
	public ResponseEntity<List<Genre>> getAllGenre(){
		
		List<Genre> allGenre = daoCategory.getAllCategory();
		if( allGenre != null ){
			return new ResponseEntity<List<Genre>>(allGenre , HttpStatus.OK);
		}else{
			return new ResponseEntity<List<Genre>>(new ArrayList<Genre>() , HttpStatus.OK);
		}
		
	}
	
}
