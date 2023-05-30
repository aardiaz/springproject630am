package com.bway.springproject.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.User;
import com.bway.springproject.service.IUserService;
import com.bway.springproject.utils.MailUtil;
import com.bway.springproject.utils.VerifyRecaptcha;

@Controller
public class UserController {
	
	@Autowired
	private MailUtil  mailUtil;
	
	private static final Logger  logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService service;
	
	 @GetMapping({"/login","/"})
     public String getLogin() {
    	 
    	 return "LoginForm";
     }
	 
	 @PostMapping("/login")
	 public String postLogin(@ModelAttribute User user, Model model, HttpSession session,@RequestParam("g-recaptcha-response")  String gRecaptchaCode) throws IOException {
		 
		
		 //call verify re-captcha
    
	if(VerifyRecaptcha.verify(gRecaptchaCode)) {
			   
		 user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		 User u = service.userLogin(user.getUserName(), user.getPassword());
		 
		  if(u != null) {
			  
			  logger.info("====== user login success =======");
			  //model.addAttribute("user",u);
			  session.setAttribute("validuser", u);
			  session.setMaxInactiveInterval(200);
			  
			  return "Home";
		  }else {
			      logger.info("====== user login failed =======");
				 model.addAttribute("message","user not found!!");
				 return "LoginForm";
		  }
	}	
		   
		 logger.info("====== user login failed =======");
		 model.addAttribute("message","you are robot");
		 return "LoginForm";
	 }
	 
	 @GetMapping("/signup")
	 public String getSignup() {
		 
		 return "SignupForm";
	 }
	 
	 @PostMapping("/signup")
	 public String postSignup(@ModelAttribute User user, Model model,@RequestParam String repass) {
		 
		 //check confirm password
		 
		 //check duplicate user
		 if((service.isUserExist(user.getUserName()) != null)) {
			 
			 model.addAttribute("message","user already exist !!");
			 return "SignupForm";
		 }
		 
		 user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		 service.userSignup(user);
		 
		 return "LoginForm";
	 }
	 
	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
		 logger.info("====== logout success =======");
		 session.invalidate();//session kill
		 return "LoginForm";
	 }
	 
	 @GetMapping("/profile")
	 public String getProfile() {
		 
		 return "ProfileForm";
	 }
	 
	 @GetMapping("/home")
	 public String home() {
		 
		 return "Home";
	 }
	 
	 @GetMapping("/forgotpass")
	 public String getForgotPasss() {
		 
		 return "ForgotPassword";
	 }
	 
	 
	 @PostMapping("/forgotpass")
	 public String postForgotPasss(@RequestParam String email) {
		 
		 mailUtil.sendEmail(email);

		 
		 return "ForgotPassword";
	 }
	 
	 @GetMapping("/reset")
	 public String resetPass() {
		 
		 return "ResetForm";
	 }
}
