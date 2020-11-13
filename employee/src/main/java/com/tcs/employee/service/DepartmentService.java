package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;

public interface DepartmentService {

	public String addDepartment(Department department);
	public String updateDepartment(long id, Department department);
	public String deleteDepartment(long id);
	public Optional<Department> findById(long id);
	public Optional<List<Department>> getDepartments();
	public Optional<List<Department>> findByOrginizationId(long id);
}
