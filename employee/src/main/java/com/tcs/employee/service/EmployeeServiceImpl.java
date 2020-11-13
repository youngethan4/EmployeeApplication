package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.EmployeeDAO;
import com.tcs.employee.dao.EmployeeDAOImpl;
import com.tcs.employee.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	private static EmployeeServiceImpl employeeServiceImpl;

	public static synchronized EmployeeService getInstance() {
		if (employeeServiceImpl == null)
			employeeServiceImpl = new EmployeeServiceImpl();
		return employeeServiceImpl;
	}

	private EmployeeServiceImpl() {
	}

	@Override
	public String addEmployee(Employee employee) {
		EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
		String result = employeeDao.addEmployee(employee);
		return result;
	}

	@Override
	public String updateEmployee(long id, Employee employee) {
		EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
		String result = employeeDao.updateEmployee(id, employee);
		return result;
	}

	@Override
	public String deleteEmployee(long id) {
		EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
		String result = employeeDao.deleteEmployee(id);
		return result;
	}

	@Override
	public Optional<Employee> findById(long id) {
		EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
		Optional<Employee> optional = employeeDao.findById(id);
		return optional;
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
		Optional<List<Employee>> optional = employeeDao.getEmployees();
		return optional;
	}

	@Override
	public Optional<List<Employee>> findByOrganizationId(long id) {
		EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
		Optional<List<Employee>> optional = employeeDao.findByOrganizationId(id);
		return optional;
	}

}
