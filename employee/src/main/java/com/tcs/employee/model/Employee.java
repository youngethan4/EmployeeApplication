package com.tcs.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@Column(name="employee_id")
	private long id;
	@Column(name="department_id")
	private long departmentId;
	@Column(name="organization_id")
	private long organizationId;
	private String name;
	private int age;
	private String position;
	
//	@ManyToOne
//	@JoinColumn(name="department_id")
//	private Department department;
//	@ManyToOne
//	@JoinColumn(name="organization_id")
//	private Organization organization;
}
