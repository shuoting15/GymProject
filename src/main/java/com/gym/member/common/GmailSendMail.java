package com.gym.member.common;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GmailSendMail {

	// 寄臨時密碼信
	public String sendTempPassword(String member_id, String tempPwd) {
		String emailOrNot = null;
		// 收件者
		String to = member_id;
		// 寄信者
		String from = "GymProject<gymproject121@gmail.com>";// TODO change accordingly
		final String username = "gymproject121@gmail.com";// TODO change accordingly
		final String password = "eeit1219453";// TODO change accordingly
		// Assuming you are sending email through relay.
		int port = 587;// TLS/STARTTLS的Port:587
		String host = "smtp.gmail.com";// gmail

		// Setup properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Get the Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// 創建email內容
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// Set Subject: header field
			message.setSubject("GymProject 寄信給您");

			// Create the message part
			MimeBodyPart textPart = new MimeBodyPart();
			StringBuffer html = new StringBuffer();
			// 文字
			html.append("<p>親愛的會員,</p>");
			html.append("<p>您的臨時密碼為:" + tempPwd + "</P>");
			html.append("<p>溫馨提醒:請記得登入後更新密碼資訊!</p><br>");
			// 圖片和連結 (html中img src用cid:image)
			// TODO-----------專案mark---下面有可能要改連結路徑---------------------------------------------------------
			html.append("<a href='http://localhost:8080/GymProject/template01'><img src='cid:image'/></a><br>");
			textPart.setContent(html.toString(), "text/html; charset=UTF-8");

			// Create the picture part (html中img src用cid:image，則header要設<image>)
			MimeBodyPart picturePart = new MimeBodyPart();
			// TODO-----------------下面圖片位置可能要換----------------------------------------------------------------
			FileDataSource fds = new FileDataSource(
					"C:/GymSource/workspace/GymProject/src/main/webapp/WEB-INF/views/images/logo5.png");
			// ---------------------上面圖片位置可能要換----------------------------------------------------------------
			picturePart.setDataHandler(new DataHandler(fds));
			picturePart.setFileName(fds.getName());
			picturePart.setHeader("Content-ID", "<image>");

			// 將文字,圖片和連結加入emil中
			Multipart emailcontent = new MimeMultipart();
			emailcontent.addBodyPart(textPart);
			emailcontent.addBodyPart(picturePart);

			// 寄信:放入email內容和設定寄信需要資訊
			message.setContent(emailcontent);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			System.out.println("send email:寄出密碼信");
			emailOrNot = "已發送email到您的電子信箱";
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return emailOrNot;
	}

	// 寄註冊碼
	public String sendRegisterNumber(String member_id, String registerNum) {
		String emailOrNot = "發送認證信失敗";
		// 收件者
		String to = member_id;
		// 寄信者
		String from = "GymProject<gymproject121@gmail.com>";// TODO change accordingly
		final String username = "gymproject121@gmail.com";// TODO change accordingly
		final String password = "eeit1219453";// TODO change accordingly
		// Assuming you are sending email through relay.
		int port = 587;// TLS/STARTTLS的Port:587
		String host = "smtp.gmail.com";// gmail

		// Setup properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Get the Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// 創建email內容
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// Set Subject: header field
			message.setSubject("GymProject 認證信");

			// Create the message part
			MimeBodyPart textPart = new MimeBodyPart();
			StringBuffer html = new StringBuffer();
			// 文字
			html.append("<p>親愛的會員,</p>");
			html.append("<p>您的註冊尚未完成,請按下面圖示連結以利完成註冊認證</p>");
			html.append("<p>非常期待您的加入!</P><br>");
			// 圖片和連結 (html中img src用cid:image)
			// TODO-----------專案mark 連結帶參數(亂數registerNum)---下面有可能要改連結路徑---------------------------------------------
			html.append("<a href='http://localhost:8080/GymProject/memberActivate?mm=");
			html.append(registerNum);
			html.append("&who=");
			html.append(member_id);
			html.append("'>");
			html.append("<img src='cid:image'/></a><br>");
			textPart.setContent(html.toString(), "text/html; charset=UTF-8");

			// Create the picture part (html中img src用cid:image，則header要設<image>)
			MimeBodyPart picturePart = new MimeBodyPart();
			// TODO-----------------下面圖片位置可能要換----------------------------------------------------------------
			
			FileDataSource fds = new FileDataSource(
					"C:/Users/user/Documents/GitHub/GymProject/src/main/webapp/WEB-INF/views/images/logo5.png");
			// ---------------------上面圖片位置可能要換----------------------------------------------------------------
			picturePart.setDataHandler(new DataHandler(fds));
			picturePart.setFileName(fds.getName());
			picturePart.setHeader("Content-ID", "<image>");

			// 將文字,圖片和連結加入emil中
			Multipart emailcontent = new MimeMultipart();
			emailcontent.addBodyPart(textPart);
			emailcontent.addBodyPart(picturePart);

			// 寄信:放入email內容和設定寄信需要資訊
			message.setContent(emailcontent);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			System.out.println("send email:寄出認證信");
			emailOrNot = "註冊快完成囉!請至您填寫的帳號email中收驗證信，完成認證!";
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return emailOrNot;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		GmailSendMail gmailSendMail = new GmailSendMail();

		// 寄密碼信
//		String alreadySendEmail = gmailSendMail.sendTempPassword("打email", "打假的預設密碼");
//		System.out.println(alreadySendEmail);

		// 寄認證信
		String alreadySendEmail = gmailSendMail.sendRegisterNumber("gymproject121@gmail.com", "dfg453d");
		System.out.println(alreadySendEmail);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();

	}
}
