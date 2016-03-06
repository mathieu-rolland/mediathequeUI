package com.perso.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan({"com.perso.service"})
@ImportResource("classpath:config.xml")
@SpringBootApplication
public class Launcher {

	public static void main(String[] args) {
		 SpringApplication.run(Launcher.class, args);
	}
	
}
