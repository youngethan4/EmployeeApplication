package com.tcs.springbootemployee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.springbootemployee.model.Department;
import com.tcs.springbootemployee.model.Organization;
import com.tcs.springbootemployee.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public String save(Department department) {
		try{
			departmentRepository.save(department);
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@Override
	public String deleteDepartment(long id) {
		try{
			departmentRepository.deleteById(id);
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	@Transactional
	public Optional<Department> findById(long id) {
		Optional<Department> optional = departmentRepository.findById(id);
		return optional;
	}

	@Override
	@Transactional
	public Optional<List<Department>> getDepartments() {
		List<Department> departments = departmentRepository.findAll();
		return Optional.ofNullable(departments);
	}

	@Override
	public Optional<List<Department>> findByOrginization(Organization organization) {
		return Optional.ofNullable(departmentRepository.findByOrganization(organization));
	}

}
