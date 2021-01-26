package com.gym.shoppingcart.orderMail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OrderMail {
	
	public static void sendOrderFinishMail(String memberMail,int orderNo) {
		// Recipient's email ID needs to be mentioned.
	      //String to = "pantone110304@gmail.com";
		String to = memberMail;
	      // Sender's email ID needs to be mentioned
	      String from = "GymProject<gymproject121@gmail.com>";
	      final String username = "gymproject121@gmail.com";//change accordingly
	      final String password = "eeit1219453";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.ssl.trust", host);

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
		   }
	         });

	      try {
		   // Create a default MimeMessage object.
		   Message message = new MimeMessage(session);	
		   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));		
		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		
		   // Set Subject: header field
		   message.setSubject("GYM 感謝您的訂購~");		
		   // Now set the actual message
		   String content="<p>親愛的會員:</p>"
				+ "<div class=\"container\" style=\"margin-top:40px\">\r\n"
		   		+ "		<div class=\"row justify-content-center\">\r\n"
		   		+ "			<span>謝謝您！ </span>&nbsp; 您的訂單已經成立！\r\n"
		   		+ "		</div>"
		   		+ "		<div class=\"row justify-content-center\">\r\n"
		   		+ "			訂單編號：<span>"+orderNo+"</span>\r\n"
		   		+ "		</div>"
		   		+ "	</div>";
		   //message.setText("Hello, this is sample for to check send " +"email using JavaMailAPI ");
		   message.setContent(content,"text/html; charset=UTF-8" );

		   // Send message
		   Transport.send(message);
		   System.out.println("Sent message successfully....");
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
		
	}

}
