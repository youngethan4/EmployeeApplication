package com.tcs.departmentrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.departmentrestapi.model.Department;
import com.tcs.departmentrestapi.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public Department save(Department department) {
		try{
			return departmentRepository.save(department);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void deleteDepartment(long id) {
		try{
			departmentRepository.deleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
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
	public Optional<List<Department>> findByOrginizationId(long id) {
		return Optional.ofNullable(departmentRepository.findByOrganizationId(id));
	}

}
