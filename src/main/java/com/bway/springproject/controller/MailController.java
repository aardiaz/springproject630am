package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.utils.MailUtil;

@Controller
public class MailController {
	
	  @Autowired
	  private MailUtil mailUtil;

	  @GetMapping("/contact")
	  public String getMail() {
		  
		  return "EMailForm";
	  }
	  
	  @PostMapping("/contact")
	  public String postMail(@RequestParam String to,@RequestParam String subject,@RequestParam String message) {
		  
		  mailUtil.sendEmail(to,subject,message);
		  
		  return "EmailForm";
	  }
}
