package com.bway.springproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Department;
import com.bway.springproject.repository.DepartmentRepository;
import com.bway.springproject.service.IDepartmentService;
@Service
public class DepartmentServiceImpl implements IDepartmentService{
	
	@Autowired
	private DepartmentRepository  deptRepo;

	@Override
	public void addDepartment(Department dept) {
		deptRepo.save(dept);
	}

	@Override
	public void deleteDepartment(int id) {
		 deptRepo.deleteById(id);
	}

	@Override
	public List<Department> getAllDepartments() {
		 
		return deptRepo.findAll();
	}

	@Override
	public Department getDeptById(int id) {
		 
		return deptRepo.findById(id).get();
	}

	@Override
	public void updateDepartment(Department dept) {
		deptRepo.save(dept);
	}

}
