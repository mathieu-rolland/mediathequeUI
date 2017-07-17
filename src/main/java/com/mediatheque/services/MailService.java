package com.mediatheque.services;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mediatheque.db.dao.DaoUserService;
import com.mediatheque.model.impl.User;

@Service
public class MailService {

	@Autowired 
	DaoUserService userService;
	
	private Logger logger = LoggerFactory.getLogger( MailService.class );

	@Autowired
	private Session session;
	
	@Autowired
	@Qualifier("mail-properties")
	private Properties mailProperties;
	
	public void sendActivationEmail( User user ){
		
		logger.debug( "Start to send email" ) ;
		
		String generatedKey = userService.generateActivationKeyForUSer(user);
		
		String mailContent = mailProperties.getProperty("mail-content");
		mailContent = mailContent.replaceAll("\\{user.name\\}", user.getName() );
		mailContent = mailContent.replaceAll( "\\{user.generatedKey\\}" , generatedKey );
		mailContent = mailContent.replaceAll( "\\{user.email\\}" , user.getEmail() );
		 
		String mailCopyContent = mailProperties.getProperty("mail-content.copy");
		mailCopyContent = mailCopyContent.replaceAll("\\{user.name\\}", user.getName() );
		mailCopyContent = mailCopyContent.replaceAll( "\\{user.generatedKey\\}" , generatedKey );
		mailCopyContent = mailCopyContent.replaceAll( "\\{user.email\\}" , user.getEmail() );
		
		String subject = mailProperties.getProperty("mail.subject");
		subject = subject.replaceAll( "\\{user.email\\}" , user.getEmail() );
		subject = subject.replaceAll("\\{user.name\\}", user.getName() );
		
		String subjectCopy = mailProperties.getProperty("mail.copy.subject");
		subjectCopy = subjectCopy.replaceAll( "\\{user.email\\}" , user.getEmail() );
		subjectCopy = subjectCopy.replaceAll("\\{user.name\\}", user.getName() );
		
		if( session.getDebug() ){
			logger.info( "Generated mail {}" , mailContent );
		}
		
	    Message message = new MimeMessage(session);
			
		try {
			
			//Email for user :
			message.setFrom(new InternetAddress( mailProperties.getProperty("mail.from") ));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			message.setSubject( subject );
			message.setContent( mailContent , mailProperties.getProperty("mail.format") );
	        
			//Email for admin
			Message copy = new MimeMessage(session);
			copy.setFrom(new InternetAddress( mailProperties.getProperty("mail.copy.from") ));
			copy.setRecipients(Message.RecipientType.TO, InternetAddress.parse( mailProperties.getProperty("mail.copy.to") ));
			copy.setSubject( subjectCopy );
			copy.setContent( mailCopyContent , mailProperties.getProperty("mail.copy.format") );
			
			//Send emails : 
			Transport.send(message);
			Transport.send(copy);
			
		} catch (MessagingException e) {
			e.printStackTrace();
			return;
		}
			
	    logger.info( "Email sending successfully to " + user.getEmail() ) ;
	}
	
}
