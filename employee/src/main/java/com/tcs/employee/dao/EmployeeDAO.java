package com.tcs.employee.dao;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Employee;

public interface EmployeeDAO {
	public String addEmployee(Employee employee);
	public String updateEmployee(long id, Employee employee);
	public String deleteEmployee(long id);
	public Optional<Employee> findById(long id);
	public Optional<List<Employee>> getEmployees();
	public Optional<List<Employee>> findByOrganizationId(long id);
	public Optional<List<Employee>> findByDepartmentId(long id);
}
