package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employee.dao.DepartmentDAO;
import com.tcs.employee.dao.DepartmentDAOImpl;
import com.tcs.employee.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDAO departmentDao;

	@Override
	public String addDepartment(Department department) {
		String result = departmentDao.addDepartment(department);
		return result;
	}

	@Override
	public String updateDepartment(long id, Department department) {
		String result = departmentDao.updateDepartment(id, department);
		return result;
	}

	@Override
	public String deleteDepartment(long id) {
		String result = departmentDao.deleteDepartment(id);
		return result;
	}

	@Override
	public Optional<Department> findById(long id) {
		Optional<Department> optional = departmentDao.findById(id);
		return optional;
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		Optional<List<Department>> optional = departmentDao.getDepartments();
		return optional;
	}

	@Override
	public Optional<List<Department>> findByOrginizationId(long id) {
		Optional<List<Department>> optional = departmentDao.findByOrginizationId(id);
		return optional;
	}

}
