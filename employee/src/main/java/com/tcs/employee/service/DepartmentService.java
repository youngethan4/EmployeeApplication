package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Organization;

public interface DepartmentService {

	public String save(Department department);
	public String deleteDepartment(long id);
	public Optional<Department> findById(long id);
	public Optional<List<Department>> getDepartments();
	public Optional<List<Department>> findByOrginization(Organization organization);
}
