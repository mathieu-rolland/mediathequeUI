package com.perso.repository;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perso.model.impl.Parameter;

@Configuration
@EnableJpaRepositories
@Repository
public interface ParametersRepository extends JpaRepository<Parameter, Integer>{
	
	@Query("select p from Parameter p")
	public List<Parameter> findAll();
	
	public List<Parameter> findByName(String name);
	
}