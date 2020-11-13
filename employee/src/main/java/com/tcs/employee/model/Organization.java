package com.tcs.employee.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

	private long id;
	private String name;
	private String address;
	private List<Department> departments;
	private List<Employee> employees;
}
