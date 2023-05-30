package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Department;
import com.bway.springproject.service.IDepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private IDepartmentService  service;
	
	@GetMapping("/department")
	public String getDept() {
		
		return "DepartmentForm";
	}
	
	@PostMapping("/department")
	public String postDept(@ModelAttribute Department  dept) {
		
		service.addDepartment(dept);
		
		return "DepartmentForm";
	}
	
	@GetMapping("/deptList")
	public String getDepartments(Model model) {
		
		model.addAttribute("dlist",service.getAllDepartments());
		
		return "DepartmentList";
	}

	@GetMapping("/delete/{id}")
	public String deleteDept(@PathVariable int id) {
		
		service.deleteDepartment(id);
		
		return "redirect:/deptList";
	}
	
	@GetMapping("/edit")
	public String editDept(@RequestParam int id, Model model) {
		
		model.addAttribute("dmodel", service.getDeptById(id));
		
		return "EditDepartmentForm";
	}
	
	@PostMapping("/update")
	public String updateDept(@ModelAttribute Department  dept) {
		
		service.updateDepartment(dept);
		
		return "redirect:/deptList";
	}
	
	
}






