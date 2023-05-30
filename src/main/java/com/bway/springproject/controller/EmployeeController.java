package com.bway.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bway.springproject.model.Employee;
import com.bway.springproject.service.IDepartmentService;
import com.bway.springproject.service.IEmployeeService;
import com.bway.springproject.utils.EmployeeExcelView;
import com.bway.springproject.utils.EmployeePdfView;

@Controller
public class EmployeeController {
	
	@Autowired
	private IEmployeeService  service;
	
	@Autowired
	private IDepartmentService  deptService;
	
	@GetMapping("/employee")
	public String getEmployee(Model model) {
		
		model.addAttribute("dptList",deptService.getAllDepartments());
		return  "EmployeeForm";
	}
	
	@PostMapping("/employee")
	public String postEmployee(@ModelAttribute Employee  employee) {
		
		service.addEmp(employee);
		return "redirect:/employee";
	}
	
	@GetMapping("/employeeList")
	public String getAll(Model model) {
		
		model.addAttribute("empList", service.getAllEmps());
		
		return "EmployeeList";
	}
	
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m =  new ModelAndView();
		m.setView(new EmployeeExcelView());

		//read data from DB
		List<Employee> list = service.getAllEmps();
		//send to Excel Impl class
		m.addObject("list", list);

		return m;
	}

	/***
	 * 9. export data to PDF file
	 */
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView m = new ModelAndView();
		m.setView(new EmployeePdfView());
		
		//read data from DB
		List<Employee> list = service.getAllEmps();
		//send to Excel Impl class
		m.addObject("list", list);

		return m;
	}

	
}
