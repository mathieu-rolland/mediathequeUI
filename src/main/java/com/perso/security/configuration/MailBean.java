package com.perso.security.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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

import com.perso.config.CustomApplicationProperties;


@Configuration
public class MailBean {

	Logger logger = LoggerFactory.getLogger( MailBean.class );
	
	@Autowired
	private CustomApplicationProperties properties;
	
	@Bean
	public Session createMailService( EmailConfiguration config ){
		
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
        
        return session;
	}
	
	@Bean(name = "mail-properties")
	public Properties readProperties() throws IOException{
		
		Properties props = new Properties();
		props.load( ClassLoader.getSystemResourceAsStream( properties.getMailSettings() ) );
		
		//Load template (mail for new user) : 
		String fileTemplate = props.getProperty("mail.template");
		BufferedReader reader = new BufferedReader( new FileReader( new File(ClassLoader.getSystemResource( fileTemplate ).getFile() ) ) );
		
		String mailTemplate = "";
		
		while(reader.ready()){
			mailTemplate += reader.readLine();
		}
		
		reader.close();

		props.put( "mail-content", mailTemplate );
		
		//Load template (mail for admin) : 
		fileTemplate = props.getProperty("mail.copy.template");
		reader = new BufferedReader( new FileReader( new File(ClassLoader.getSystemResource( fileTemplate ).getFile() ) ) );
		
		mailTemplate = "";
		
		while(reader.ready()){
			mailTemplate += reader.readLine();
		}
		
		reader.close();

		props.put( "mail-content.copy", mailTemplate );
		
		return props;
	}
	
}
