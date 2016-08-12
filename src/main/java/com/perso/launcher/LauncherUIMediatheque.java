package com.perso.launcher;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
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
@ComponentScan(basePackageClasses = {ParametersService.class}, basePackages= {"com.perso.service.*" , "com.perso.config"})
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@ImportResource({"classpath:config.xml"})
@SpringBootApplication
public class LauncherUIMediatheque extends SpringBootServletInitializer {

	static Logger log = Logger.getLogger(LauncherUIMediatheque.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(LauncherUIMediatheque.class, args);
		log.info( "Start application successfully" );
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LauncherUIMediatheque.class);
    }
	
}
