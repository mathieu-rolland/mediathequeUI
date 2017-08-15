package com.mediatheque.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class MailBean {

	Logger logger = LoggerFactory.getLogger( MailBean.class );
	
	@Autowired
	private CustomApplicationProperties properties;
	
	@Bean
	public Session createMailService( EmailConfiguration config ){
		
		logger.info("Starting creating mail session");
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		try {
			mailSender.getSession().getTransport("smtp");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		
		if( config.getDebug() ){
			logger.info("Host : " + config.getHost() );
			logger.info("Port : " + config.getPort() );
			logger.info("User/password : " + config.getUsername()  +"/"+ config.getPassword() );
		}
		
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.host", config.getHost() );
		mailProperties.put("mail.smtp.port", config.getPort());
		mailProperties.put("mail.smtp.user", config.getUsername() );
		mailProperties.put("mail.smtp.password", config.getPassword());
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable","true"); 
		mailProperties.put("mail.smtp.EnableSSL.enable","true");
		
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.getSession().setDebug( config.getDebug() );
	        
        Session session = Session.getDefaultInstance( mailProperties ,
      		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication( config.getUsername()  ,  config.getPassword() );
			}
		  });

        //        session.setDebug( config.getDebug() );
        
        logger.info("Mail session created successfully");
        
        return session;
	}
	
	@Bean(name = "mail-properties")
	public Properties readProperties() throws IOException{
		
		logger.info("Starting to read mail properties from file {}" ,  properties.getMailSettings());
		
		Properties props = new Properties();
		BufferedReader propertiesReader = new BufferedReader( new FileReader( new File( properties.getMailSettings() ) ) );
		props.load( propertiesReader );
		
		//Load template (mail for new user) : 
		String fileTemplate = props.getProperty("mail.template");
		
		logger.info("Loading mail template from {}" , fileTemplate);
		BufferedReader reader = new BufferedReader( new FileReader( new File( fileTemplate ) ) );
		
		String mailTemplate = "";
		
		while(reader.ready()){
			mailTemplate += reader.readLine();
		}
		
		reader.close();

		props.put( "mail-content", mailTemplate );
		
		//Load template (mail for admin) : 
		fileTemplate = props.getProperty("mail.copy.template");
		
		logger.info("Loading mail copy template from {}" , fileTemplate);
		reader = new BufferedReader( new FileReader( new File( fileTemplate ) ) );
		
		mailTemplate = "";
		
		while(reader.ready()){
			mailTemplate += reader.readLine();
		}
		
		reader.close();

		props.put( "mail-content.copy", mailTemplate );
		
		logger.info("Readinf mail properties ended successfully");
		return props;
	}
	
}
