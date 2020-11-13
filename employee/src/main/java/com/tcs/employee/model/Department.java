package com.tcs.employee.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	private long id ;
	private long organizationId;
	private String name;
	private List<Employee> employees;
}
