package com.tcs.employeerestapi.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employeerestapi.model.Employee;

public interface EmployeeService {
	public Employee save(Employee employee);
	public void deleteEmployee(long id);
	public Optional<Employee> findById(long id);
	public Optional<List<Employee>> getEmployees();
	public Optional<List<Employee>> findByOrganizationId(long id);
	//public Optional<List<Employee>> findByOrganization(Organization organization);
}
