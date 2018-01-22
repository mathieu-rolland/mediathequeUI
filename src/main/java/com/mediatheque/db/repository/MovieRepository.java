package com.mediatheque.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mediatheque.model.impl.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	@Query (value = "select m from Movie m")
	List<Movie> findByType(String type);

	@Query (value = "select m from Movie m")
	List<Movie> findByName(String name);
	
	@Query (value = "select m from Movie m where m.path = ?")
	Movie findByPath(String path);
	
}
