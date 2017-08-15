package com.mediatheque.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mediatheque.db.repository.ParametersRepository;
import com.mediatheque.model.impl.Parameter;

@Repository
public class ParameterDao {

	@Autowired
	private ParametersRepository parameterRepository;
	
	public List<Parameter> findByName(String name){
		return parameterRepository.findByName(name);
	}
	
	public List<Parameter> findAll(){
		return parameterRepository.findAll();
	}
	
	public void save(Parameter parameter){
		parameterRepository.saveAndFlush(parameter);
	}

	public void delete(Parameter parameter) {
		parameterRepository.delete(parameter);
	}
	
}
