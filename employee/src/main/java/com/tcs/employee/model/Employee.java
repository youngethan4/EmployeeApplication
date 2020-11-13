package com.tcs.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private long id;
	private long organizationId;
	private long departmentId;
	private String name;
	private int age;
	private String position;
}
