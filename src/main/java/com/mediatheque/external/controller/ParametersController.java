package com.mediatheque.external.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mediatheque.db.dao.ParameterDao;
import com.mediatheque.model.impl.Parameter;

@RestController
@RequestMapping("/parameters")
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST })
public class ParametersController {

	private Logger logger = Logger.getLogger(ParametersController.class);
	
	@Autowired
	private ParameterDao parameterDao;
	
	@RequestMapping("/")
	public List<Parameter> readParameters(){
		logger.info("Start listing all parameters");
		return parameterDao.findAll();
	}
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public List<Parameter> addParameter(@RequestBody Parameter param){
		logger.info("Create new parameter " + param);
		parameterDao.save(param);
		return readParameters();
	}
	
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public List<Parameter> updateParameter(@RequestBody Parameter param){
		logger.info("Update parameter " + param);
		parameterDao.save(param);
		return readParameters();
	}
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public List<Parameter> deleteParameter(@RequestBody Parameter param){
		logger.info("Delete parameter " + param);
		parameterDao.delete(param);
		return readParameters();
	}
	
}
