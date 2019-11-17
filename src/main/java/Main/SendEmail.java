package Main;



import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {


	public static void sendMail(final String from,String to,final String password,String objet,String text) {


		Properties properties = new Properties();
		//configurer les proprietés smtp
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");//serveur smtp de gmail
		properties.put("mail.smtp.port","587");

		//Création de la session
		Session session = Session.getInstance(properties,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {
			//Création de l'objet Message
			Message message = new MimeMessage(session);
			//l'adresse de l'emetteur
			message.setFrom(new InternetAddress(from));
			//l'adresse du destinataire
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			//objet du Mail
			message.setSubject(objet);
			//le corp du Mail
			message.setText(text);

			//Envoyer le message
			Transport.send(message);

		} catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		} }


}
