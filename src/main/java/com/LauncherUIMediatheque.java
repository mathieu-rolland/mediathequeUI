package com;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.perso.model.impl.Movie;
import com.perso.model.impl.Parameter;
import com.perso.repository.MovieRepository;
import com.perso.repository.ParametersRepository;
import com.perso.service.ParametersService;
import com.perso.spring.cors.CORSFilter;

@EntityScan(basePackageClasses = {Movie.class, Parameter.class})
@EnableJpaRepositories(basePackageClasses={ParametersRepository.class, MovieRepository.class})
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
	
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(LauncherUIMediatheque.class);
//    }
	
}
