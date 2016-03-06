package com.perso.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.perso.service"})
@SpringBootApplication
public class Launcher {

	public static void main(String[] args) {
		 SpringApplication.run(Launcher.class, args);
	}
	
}
