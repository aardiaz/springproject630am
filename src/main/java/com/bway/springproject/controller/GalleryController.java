package com.bway.springproject.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bway.springproject.repository.ProductRepository;

@Controller
public class GalleryController {
	
	@Autowired
	private ProductRepository  prodRepository;
	
	@GetMapping("/gallery")
	public String getGallery(Model model, HttpSession session) {
		
		if(session.getAttribute("validuser") == null) {
			
			return "LoginForm";
		}
		
		//String[]  imgNameList = new File("src/main/resources/static/images").list();
		//model.addAttribute("imgNameList",imgNameList);
		model.addAttribute("productList",prodRepository.findAll());
		
		return "GalleryForm";
	}

}
