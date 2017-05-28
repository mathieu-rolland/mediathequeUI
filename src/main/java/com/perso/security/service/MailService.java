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
		
		String hardcodingBody = "<h3>Bonjour " + user.getName() + " ,</h3>";
		hardcodingBody += "Merci de vous êtes inscrit à la médiatheque.<br />";
		hardcodingBody += "<br />Pour valider votre compte, merci de l'activer en suivant le lien suivant ";
		hardcodingBody += "<a href=http://mathieu35700.ddns.net:9050/user/active-account?q="+generatedKey+">activer mon compte</a>.";
		hardcodingBody += "<br /><br />";
		hardcodingBody += "Cordialement,<br />Mathieu.";
		
		String hardcodingBodyCopy = "<h3>Bonjour Mathieu,</h3>";
		hardcodingBodyCopy += "L'utilisateur vient de s'inscrire sur la médiatheque avec l'adresse email "+generatedKey+".<br />";
		hardcodingBodyCopy += "<br />L'adresse d'activation du compte est la suivante : ";
		hardcodingBodyCopy += "http://mathieu35700.ddns.net:9050/user/active-account?q="+generatedKey;
		hardcodingBodyCopy += "<br /><br />";
		hardcodingBodyCopy += "Cordialement,<br />Mathieu.";
		
		logger.warn( hardcodingBody );
		
	    Message message = new MimeMessage(session);
			
		try {
			message.setFrom(new InternetAddress("mediatheque@no-replay"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			message.setSubject("Inscription sur la médiathèque");
			message.setContent( hardcodingBody , "text/html; charset=utf-8");
	        
			Message copy = new MimeMessage(session);
			copy.setFrom(new InternetAddress("mediatheque@no-replay"));
			copy.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mathieu.rolland2705@gmail.com"));
			copy.setSubject("Inscription de "+ user.getEmail() +" sur la médiathèque");
			copy.setContent( hardcodingBodyCopy , "text/html; charset=utf-8" );
			
			Transport.send(message);
			Transport.send(copy);
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
			return;
		}
			
	    logger.info( "Email sending successfully to " + user.getEmail() ) ;
	}
	
}
