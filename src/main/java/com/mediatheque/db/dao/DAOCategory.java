package com.mediatheque.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mediatheque.db.repository.CategoryRepository;
import com.mediatheque.model.impl.Genre;

@Repository
public class DAOCategory {

	@Autowired
	private CategoryRepository categoryrepository;
	
	public List<Genre> getAllCategory(){
		return categoryrepository.findAll();
	}
	
}
