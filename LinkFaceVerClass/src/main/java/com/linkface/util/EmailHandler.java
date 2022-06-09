package com.linkface.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailHandler {

	private static JavaMailSenderImpl emailSender;
	
	static {
		
		  emailSender = new JavaMailSenderImpl();
		  emailSender.setHost("smtp.gmail.com");
		  emailSender.setPort(587);
		  emailSender.setUsername("linkfacemanager@gmail.com");
		  emailSender.setPassword("eyuobxlyvzsfsild");

		  Properties props = emailSender.getJavaMailProperties();
		  props.setProperty("mail.transport.protocol", "smtp");
		  props.setProperty("mail.smtp.auth", "true");
		  props.setProperty("mail.smtp.starttls.enable", "true");
		  props.setProperty("mail.debug", "false");
		  props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		  props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		  emailSender.setJavaMailProperties(props);	  
	}
	// 이메일 전송
	public static void sendEmail(String to,String subject,String text) {
	
		 SimpleMailMessage message = new SimpleMailMessage(); 
	     message.setFrom("LinkFaceManager");
	     message.setTo(to); 
	     message.setSubject(subject); 
	     message.setText(text);
	     emailSender.send(message);
	}
	
	// 1. 타입 2. 유저키 3. 이메일   4. 토큰 ( 이메일 인증 및 패스워드 찾기 링크 전송)
	public static void authLinkSendEmail(String type,Long userKey,String email,String token) {
		// 인증할 서버 URL
		String authLink = "http://localhost:8080/certification/";
		// 문자열 합침
		String sendOriginalText = type + "_" + userKey + "_" + email + "_" + token;
		// 해당 데이터 암호화
		String enText = Aes256.encrypt(sendOriginalText);
		// url 전송이 가능하게끔 데이터 가공
		String urlText = textToUrl(enText);
		// 전송
		sendEmail(email,"인증 링크",authLink + urlText);
	}
	
	public static Map<String,String> analysisUrl(String urlText) {
		
		Map<String, String> data = new HashMap<>();
		
		String enText = urlToText(urlText);
		
		// 평문 데이터라면 return
		String originalTest = Aes256.decrypt(enText);
		if(originalTest == "") { return data; }

		// 올바르지 않은 값이라면 return
		String[] dataArr = originalTest.split("_");
		if(dataArr.length <= 1) { return data; }

		data.put("type", dataArr[0]);
		data.put("key", dataArr[1]);
		data.put("email", dataArr[2]);
		data.put("token", dataArr[3]);
	
		return data;
		
	}
	
	public static String textToUrl(String text) {
		
		String urlText = text.replace("/", "{{slash}}")
									 .replace(".", "{{period}}")
									 .replace("&", "{{and}}")
									 .replace("+", "{{plus}}");
		
		try { urlText = URLEncoder.encode(urlText, "UTF-8"); } 
		catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		
		return urlText;
	}
	
	public static String urlToText(String urlText) {
		
		String text = urlText.replace("{{slash}}", "/")
							 .replace("{{period}}", ".")
							 .replace("{{and}}", "&")
							 .replace("{{plus}}", "+");
		return text;
	}
	
	
}
