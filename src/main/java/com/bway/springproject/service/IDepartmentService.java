package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Department;

public interface IDepartmentService {

	void addDepartment(Department dept);

	void deleteDepartment(int id);

	List<Department> getAllDepartments();

	Department getDeptById(int id);
	
	void updateDepartment(Department dept);
}
