package com.bway.springproject.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

 
@Data
@Entity
@Table(name="employee_tbl")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fname;
	private String lname;
	private String gender;
	private String phone;
	private String email;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate  dob;
	private int age;
	private String company;
	private String post;
	private int salary;
	@OneToOne
	@JoinColumn(name="deptId")
	private Department department;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate joinDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addressId")
	private Address address;
	
	@ElementCollection
	@CollectionTable
	private List<String> projects;
	
}
