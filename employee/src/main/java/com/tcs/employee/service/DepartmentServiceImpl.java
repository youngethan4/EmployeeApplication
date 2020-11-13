package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.DepartmentDAO;
import com.tcs.employee.dao.DepartmentDAOImpl;
import com.tcs.employee.model.Department;

public class DepartmentServiceImpl implements DepartmentService {
	
	private static DepartmentServiceImpl departmentServiceImpl;

	public static synchronized DepartmentService getInstance() {
		if (departmentServiceImpl == null)
			departmentServiceImpl = new DepartmentServiceImpl();
		return departmentServiceImpl;
	}

	private DepartmentServiceImpl() {
	}

	@Override
	public String addDepartment(Department department) {
		DepartmentDAO departmentDao = DepartmentDAOImpl.getInstance();
		String result = departmentDao.addDepartment(department);
		return result;
	}

	@Override
	public String updateDepartment(long id, Department department) {
		DepartmentDAO departmentDao = DepartmentDAOImpl.getInstance();
		String result = departmentDao.updateDepartment(id, department);
		return result;
	}

	@Override
	public String deleteDepartment(long id) {
		DepartmentDAO departmentDao = DepartmentDAOImpl.getInstance();
		String result = departmentDao.deleteDepartment(id);
		return result;
	}

	@Override
	public Optional<Department> findById(long id) {
		DepartmentDAO departmentDao = DepartmentDAOImpl.getInstance();
		Optional<Department> optional = departmentDao.findById(id);
		return optional;
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		DepartmentDAO departmentDao = DepartmentDAOImpl.getInstance();
		Optional<List<Department>> optional = departmentDao.getDepartments();
		return optional;
	}

	@Override
	public Optional<List<Department>> findByOrginizationId(long id) {
		DepartmentDAO departmentDao = DepartmentDAOImpl.getInstance();
		Optional<List<Department>> optional = departmentDao.findByOrginizationId(id);
		return optional;
	}

}
