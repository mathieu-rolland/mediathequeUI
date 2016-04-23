package com.perso.launcher;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.perso.model.impl.Movie;
import com.perso.model.impl.Parameter;
import com.perso.repository.MovieRepository;
import com.perso.repository.ParametersRepository;
import com.perso.service.ParametersService;

@EntityScan(basePackageClasses = {Movie.class, Parameter.class})
@EnableJpaRepositories(basePackageClasses={ParametersRepository.class, MovieRepository.class})
@ComponentScan(basePackageClasses = {ParametersService.class}, basePackages="com.perso.service.*")
@ImportResource({"classpath:config.xml"})
@SpringBootApplication
public class Launcher {

	static Logger log = Logger.getLogger(Launcher.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
		log.info( "Start application successfully" );
	}
	
}
