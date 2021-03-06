package com.mediatheque.external.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mediatheque.db.repository.MachineRepository;
import com.mediatheque.model.impl.Machine;

import org.slf4j.Logger;

@RestController
@RequestMapping("/machine")
public class MachineController {

	Logger logger = LoggerFactory.getLogger( MachineController.class );
	
	@Autowired
	private MachineRepository repo;
	
	@RequestMapping("/list")
	public List<Machine> getMachines(){
		return repo.findAll();
	}
	
	@RequestMapping( value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean addMachine(@RequestBody Machine machine){
		try{
			logger.debug( "Insert machine : " + machine );
			repo.saveAndFlush(machine);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
