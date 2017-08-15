package com.mediatheque.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediatheque.model.impl.Genre;

@Repository
public interface CategoryRepository extends JpaRepository<Genre, Integer>{
	
}
