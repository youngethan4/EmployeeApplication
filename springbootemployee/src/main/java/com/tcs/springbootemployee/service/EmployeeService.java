package com.tcs.springbootemployee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.springbootemployee.model.Employee;
import com.tcs.springbootemployee.model.Organization;

public interface EmployeeService {
	public String save(Employee employee);
	public String deleteEmployee(long id);
	public Optional<Employee> findById(long id);
	public Optional<List<Employee>> getEmployees();
	public Optional<List<Employee>> findByOrganization(Organization organization);
}
