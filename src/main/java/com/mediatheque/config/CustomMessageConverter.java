package com.mediatheque.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.api.allocine.decod.IDecoder;

@Configuration 
public class CustomMessageConverter  {
	
	Logger logger = Logger.getLogger(CORSFilter.class);
	
    @Bean
    public GsonHttpMessageConverter configureMessageConverters(IDecoder allocineDecoder) {
    	logger.debug("***************** configureMessageConverters *********************");
    	GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
    	msgConverter.setGson( allocineDecoder.getGson() );
    	return msgConverter;
    }
    
} 
