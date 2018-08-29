package com.javalec.paper.command;



import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.javalec.paper.command.SMTPAuthenticator;

public class SandMail implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		Random random = new Random();
		int verfiyCode = random.nextInt(99999 - 10000 + 1) + 10000; // 인증코드 생성 후
		String email = request.getParameter("email"); // 회원 가입 메일 
		
		// 세션에 인증코드를 담음. 
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("code", verfiyCode);
		
		try {
			String mail_from = "binarynum01@gmail.com";
			String mail_to =  email;
			request.setAttribute("email", email);
			String Mailtitle = "[논문 유통] 메일 인증";
			String contents = "<h1>This is your code : " + verfiyCode + "</h1>";
			
			mail_from = new String(mail_from.getBytes("UTF-8"),"UTF-8" );
			mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");
			
			Properties properties = new Properties();
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback", "false");
			properties.put("mail.smtp.auth", "true");
			
			Authenticator authenticator = new SMTPAuthenticator();
			
			Session session = Session.getDefaultInstance(properties, authenticator);
			
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(mail_from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
			message.setSubject(Mailtitle, "UTF-8");
			message.setContent(contents, "text/html; charset=UTF-8");
			message.setHeader("Content-type", "text/html; charset=UTF-8");
			
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}