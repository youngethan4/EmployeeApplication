package com.tcs.departmentrestapi.service;

import java.util.List;
import java.util.Optional;

import com.tcs.departmentrestapi.model.Department;

public interface DepartmentService {

	public Department save(Department department);
	public void deleteDepartment(long id);
	public Optional<Department> findById(long id);
	public Optional<List<Department>> getDepartments();
	public Optional<List<Department>> findByOrginizationId(long id);
}
