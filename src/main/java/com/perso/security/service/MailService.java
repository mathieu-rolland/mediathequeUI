package com.perso.security.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.perso.security.entity.User;

@Service
public class MailService {

	@Autowired 
	DaoUserService userService;

//	@Autowired
//	public EmailService emailService;
	
	private Logger logger = LoggerFactory.getLogger( MailService.class );

	private JavaMailSenderImpl mailSender;
	
	public void sendActivationEmail( User user ){
		
		mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setUsername("mathieu.rolland2705@gmail.com");
		mailSender.setPassword( "27mai1988" );

		
		
		try {
			mailSender.getSession().getTransport("smtp");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.host", "smtp.gmail.com");
		mailProperties.put("mail.smtp.user", "");
		mailProperties.put("mail.smtp.password", "");
		mailProperties.put("mail.smtp.port", "587");
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable","true"); 
		mailProperties.put("mail.smtp.EnableSSL.enable","true");
		
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.getSession().setDebug( true );
		logger.info( "Start to send email" ) ;
		
		String generatedKey = userService.generateActivationKeyForUSer(user);
		
		String hardcodingBody = "Bonjour,\n";
		hardcodingBody += "Merci de vous êtes inscrit à la médiatheque.\n";
		hardcodingBody += "Pour valider votre compte, merci de l'activer en suivant le lien suivant ";
		hardcodingBody += " http://mathieu35700.ddns.net:9050/user/active-account?q="+generatedKey+"\n";
		hardcodingBody += "\n\n";
		hardcodingBody += "Cordialement,\nMathieu.";
		
		logger.warn( hardcodingBody );
		
		
	        
        Session session =  Session.getDefaultInstance( mailProperties ,
      		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mathieu.rolland2705@gmail.com", "27mai1988");
			}
		  });

        	
	        Message message = new MimeMessage(session);
			
			try {
				message.setFrom(new InternetAddress("mathieu.rolland2705@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
				message.setSubject("Inscription sur la médiathèque");
				message.setText( hardcodingBody );
		        
				session.getTransport("smtp");
				Transport.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
	   logger.info( "Email sending successfully to " + user.getEmail() ) ;
	}
	
}
