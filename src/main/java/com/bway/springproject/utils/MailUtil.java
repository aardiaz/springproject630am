package com.bway.springproject.utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class MailUtil {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(message);

        javaMailSender.send(msg);

    }
	
	public void sendEmail(String to) {

        SimpleMailMessage msg = new SimpleMailMessage();
        
        msg.setTo(to);
        msg.setSubject("Your New Password");
       // msg.setText("here is your new password :  "+UUID.randomUUID().toString().substring(0,8));
        String resetLink = "http://localhost/reset";
        msg.setText("to reset password plz. click here  :  "+resetLink);
        
        
        javaMailSender.send(msg);

    }

}
