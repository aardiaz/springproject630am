package com.bway.springproject.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springproject.model.Employee;
import com.bway.springproject.model.Product;
import com.bway.springproject.repository.ProductRepository;
import com.bway.springproject.service.IEmployeeService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeRestController {
	@Autowired
	private ProductRepository  prodRepo;
	
	@Autowired
	private IEmployeeService  service;
	
	@GetMapping("/list")
	public List<Employee> getAllEmp() {
		
		return service.getAllEmps();
	}
	
	@GetMapping("/{id}")
	public Employee getOneEmp(@PathVariable Long id) {
		
		return service.getEmpById(id);
	}
	
	@PostMapping("/add")
	public String add(@RequestBody  Employee emp) {
		
		service.addEmp(emp);
		
		return "success";
	}

	@PutMapping("/update")
	public String update(@RequestBody Employee emp) {
		
		service.updateEmp(emp);
		
		return "update success";
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable  Long id) {
		
		service.deleteEmp(id);
		
		return "success";
	}
	
	@GetMapping("/j2o")
	public String jsonToObjMapping() {
		
		RestTemplate  restTemp = new RestTemplate();
		Employee emp = restTemp.getForObject("http://localhost/api/emp/3", Employee.class);
		
		return "First Name = "+emp.getFname();
	}
	
	@GetMapping("/ja2oa")
	public String jsonArray2ObjArray() {
		
		RestTemplate  temp = new RestTemplate();
		Employee[]  empList = temp.getForObject("http://localhost/api/emp/list", Employee[].class);
		
		return "FirstName = "+empList[0].getFname();
	}
	
	@GetMapping("/addProduct")
	public String addProducts() {
		RestTemplate  temp  = new RestTemplate();
		Product[]  products = temp.getForObject("https://fakestoreapi.com/products", Product[].class);
		
		prodRepo.saveAll(Arrays.asList(products));
		//prodRepo.save(products);
		return "success";
	}
	
	
}
