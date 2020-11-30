package com.tcs.employeerestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employeerestapi.model.Employee;
import com.tcs.employeerestapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee employee) {
		try{
			return employeeRepository.save(employee);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteEmployee(long id) {
		try{
			employeeRepository.deleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
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
		return Optional.ofNullable(employeeRepository.findByOrganizationId(id));
	}

//	@Override
//	public Optional<List<Employee>> findByOrganization(Organization organization) {
//		return Optional.ofNullable(employeeRepository.findByOrganization(organization));
//	}

}
