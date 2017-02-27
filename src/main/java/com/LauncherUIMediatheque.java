package com;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan
@ImportResource({"classpath:config.xml"})
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@SpringBootApplication
public class LauncherUIMediatheque {

	static Logger log = Logger.getLogger(LauncherUIMediatheque.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(LauncherUIMediatheque.class, args);
		System.out.println("test");
		log.info( "Start application successfully" );
	}
	
}
