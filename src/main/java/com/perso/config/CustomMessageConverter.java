package com.perso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.api.allocine.decod.IDecoder;

@Configuration 
public class CustomMessageConverter  {
	
    @Bean
    public GsonHttpMessageConverter configureMessageConverters(IDecoder allocineDecoder) {
    	GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
    	msgConverter.setGson( allocineDecoder.getGson() );
    	return msgConverter;
    }
    
} 
