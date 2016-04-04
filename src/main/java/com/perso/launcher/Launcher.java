package com.perso.launcher;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan({"com.perso.service", "com.perso.config, com.perso.spring.cors"})
@ImportResource({"classpath:config.xml"})
@SpringBootApplication
public class Launcher {

	static Logger log = Logger.getLogger(Launcher.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
		log.info( "Start application successfully" );
	}
	
}
