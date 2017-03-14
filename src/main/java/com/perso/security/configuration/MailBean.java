package com.perso.security.configuration;

import java.util.Properties;

import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailBean {

	@Bean
	public Session createMailService( EmailConfiguration config ){
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		try {
			mailSender.getSession().getTransport("smtp");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		
		System.out.println("Host : " + config.getHost() );
		System.out.println("Port : " + config.getPort() );
		System.out.println("User/password : " + config.getUsername()  +"/"+ config.getPassword() );
		
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
        
        session.setDebug( true );
        
        return session;
	}
	
}
