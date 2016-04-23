package com.perso.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.perso.model.impl.Parameter;
import com.perso.repository.ParametersRepository;

@RestController
@RequestMapping("/parameters")
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST })
public class ParametersService {

	@Autowired
	private ParametersRepository repo;
	private Logger logger = Logger.getLogger(ParametersService.class);
	
	@RequestMapping("/")
	public List<Parameter> readParameters(){
		logger.info("Start listing all parameters");
		return repo.findAll();
	}
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public List<Parameter> addParameter(@RequestBody Parameter param){
		logger.info("Create new parameter " + param);
		repo.save(param);
		return readParameters();
	}
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public List<Parameter> deleteParameter(@RequestBody Parameter param){
		logger.info("Create new parameter " + param);
		repo.delete(param);
		return readParameters();
	}
	
}
