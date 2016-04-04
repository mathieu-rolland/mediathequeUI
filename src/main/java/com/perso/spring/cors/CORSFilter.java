package com.perso.spring.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ch.qos.logback.core.filter.Filter;

/**
 * Enabling CORS support  - Access-Control-Allow-Origin
 * @author zeroows@gmail.com
 * 
 * <code>
 	<!-- Add this to your web.xml to enable "CORS" -->
	<filter>
	  <filter-name>cors</filter-name>
	  <filter-class>com.elm.mb.rest.filters.CORSFilter</filter-class>
	</filter>
	  
	<filter-mapping>
	  <filter-name>cors</filter-name>
	  <url-pattern>/*</url-pattern>
	</filter-mapping>
 * </code>
 */
@Configuration
@EnableWebMvc
public class CORSFilter extends WebMvcConfigurerAdapter {

	public CORSFilter() {
		System.out.println("LOAD Class OK\n\n");
	}
	
	@Bean
	public CommonsRequestLoggingFilter logFilter() {
	    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
	    filter.setIncludeQueryString(true);
	    filter.setIncludePayload(true);
	    filter.setMaxPayloadLength(5120);
	    return filter;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:9000")
			.allowedMethods("PUT", "DELETE", "GET", "POST")
			//.allowedHeaders("header1", "header2", "header3")
			//.exposedHeaders("header1", "header2")
			.allowCredentials(false).maxAge(3600);
	}
}
