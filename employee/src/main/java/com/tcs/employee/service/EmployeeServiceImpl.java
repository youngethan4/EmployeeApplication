package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employee.model.Employee;
import com.tcs.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String save(Employee employee) {
		try{
			employeeRepository.save(employee);
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public String deleteEmployee(long id) {
		try{
			employeeRepository.deleteById(id);
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public Optional<Employee> findById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		return optional;
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return Optional.ofNullable(employees);
	}

	@Override
	public Optional<List<Employee>> findByOrganizationId(long id) {
		List<Employee> employees = employeeRepository.findByOrganizationId(id);
		return Optional.ofNullable(employees);
	}

}
