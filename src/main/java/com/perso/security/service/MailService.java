package com.perso.security.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.security.entity.User;

@Service
public class MailService {

	@Autowired 
	DaoUserService userService;
	
	private Logger logger = LoggerFactory.getLogger( MailService.class );

	@Autowired
	private Session session;
	
	public void sendActivationEmail( User user ){
		
		logger.info( "Start to send email" ) ;
		
		String generatedKey = userService.generateActivationKeyForUSer(user);
		
		String hardcodingBody = "Bonjour " + user.getName() + " ,\n";
		hardcodingBody += "Merci de vous êtes inscrit à la médiatheque.\n";
		hardcodingBody += "Pour valider votre compte, merci de l'activer en suivant le lien suivant ";
		hardcodingBody += " http://mathieu35700.ddns.net:9050/user/active-account?q="+generatedKey+"\n";
		hardcodingBody += "\n\n";
		hardcodingBody += "Cordialement,\nMathieu.";
		
		logger.warn( hardcodingBody );
		
	    Message message = new MimeMessage(session);
			
		try {
			message.setFrom(new InternetAddress("mathieu.rolland2705@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			message.setSubject("Inscription sur la médiathèque");
			message.setText( hardcodingBody );
	        
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
			
	    logger.info( "Email sending successfully to " + user.getEmail() ) ;
	}
	
}
