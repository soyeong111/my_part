package com.study.bookspace.util;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service("mailService")
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	// 단순 문자 메일 보내기
	public void sendSimpleEmail(MailVO mailVO) {
		
		// 단순 문자 메일을 보낼 수 있는 객체 생성
	    SimpleMailMessage message = new SimpleMailMessage();
    	
	    // 메일 제목
    	message.setSubject(mailVO.getTitle());
    	
    	// 받는 사람 - 크기를 정하지 않게 리스트로 만들고, 배열로 변경
    	message.setTo(mailVO.getRecipientList().toArray(new String[mailVO.getRecipientList().size()]));
        
    	// 메일 내용
        message.setText(mailVO.getContent());
        
        // 보내기
        javaMailSender.send(message);
        
	}
	
	// n자리의 랜덤 비밀번호 생성
	public String createRandomPassword(int len) {
		SecureRandom random = new SecureRandom();
		StringBuilder pw = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(ConstVariable.CHARS.length());
			pw.append(ConstVariable.CHARS.charAt(randomIndex));
		}
		return pw.toString();
	}

}