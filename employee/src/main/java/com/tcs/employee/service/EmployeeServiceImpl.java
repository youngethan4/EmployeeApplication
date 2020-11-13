package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employee.dao.EmployeeDAO;
import com.tcs.employee.dao.EmployeeDAOImpl;
import com.tcs.employee.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDAO employeeDao;

	@Override
	public String addEmployee(Employee employee) {
		String result = employeeDao.addEmployee(employee);
		return result;
	}

	@Override
	public String updateEmployee(long id, Employee employee) {
		String result = employeeDao.updateEmployee(id, employee);
		return result;
	}

	@Override
	public String deleteEmployee(long id) {
		String result = employeeDao.deleteEmployee(id);
		return result;
	}

	@Override
	public Optional<Employee> findById(long id) {
		Optional<Employee> optional = employeeDao.findById(id);
		return optional;
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		Optional<List<Employee>> optional = employeeDao.getEmployees();
		return optional;
	}

	@Override
	public Optional<List<Employee>> findByOrganizationId(long id) {
		Optional<List<Employee>> optional = employeeDao.findByOrganizationId(id);
		return optional;
	}

}
