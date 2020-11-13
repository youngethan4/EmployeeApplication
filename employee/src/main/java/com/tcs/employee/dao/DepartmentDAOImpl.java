package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.dbcp2.datasources.PerUserPoolDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.utils.DBUtils;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
	
	@Autowired
	DBUtils dbUtils;

	@Override
	public String addDepartment(Department department) {
		Connection connection = dbUtils.getConnection();
		int result = 0;
		String insert = "insert into department (id, organizationId, name) values(?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setLong(1, department.getId());
			preparedStatement.setLong(2, department.getOrganizationId());
			preparedStatement.setString(3, department.getName());

			result = preparedStatement.executeUpdate();

			if (result > 0) {
				connection.commit();
				return "success";
			} else
				return "fail";
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public String updateDepartment(long id, Department department) {
		Connection connection = dbUtils.getConnection();
		int result = 0;
		String update = "update department set id=?, organizationId=?, name=? "
				+ "where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(update);
			preparedStatement.setLong(1, department.getId());
			preparedStatement.setLong(2, department.getOrganizationId());
			preparedStatement.setString(3, department.getName());
			preparedStatement.setLong(4, id);

			result = preparedStatement.executeUpdate();

			if (result > 0) {
				connection.commit();
				return "success";
			} else
				return "fail";
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Override
	public String deleteDepartment(long id) {
		Connection connection = dbUtils.getConnection();
		int result = 0;
		String delete = "delete from department where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(delete);
			preparedStatement.setLong(1, id);

			result = preparedStatement.executeUpdate();

			if (result > 0) {
				connection.commit();
				return "success";
			} else
				return "fail";
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	@Autowired
	EmployeeDAO employeeDao;
	
	@Override
	public Optional<Department> findById(long id) {
		Connection connection = dbUtils.getConnection();
		Department department = null;
		ResultSet resultSet = null;
		String query = "select * from department where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				department = new Department();
				long departmentId = resultSet.getLong("id");
				department.setId(departmentId);
				department.setOrganizationId(resultSet.getLong("organizationId"));
				department.setName(resultSet.getString("name"));
				Optional<List<Employee>> employees = employeeDao.findByDepartmentId(departmentId);
				department.setEmployees(employees.orElse(new ArrayList<>()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.ofNullable(department);
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		Connection connection = dbUtils.getConnection();
		List<Department> departments = new ArrayList<>();
		ResultSet resultSet = null;
		String query = "select * from department";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Department department = new Department();
				long departmentId = resultSet.getLong("id");
				department.setId(departmentId);
				department.setOrganizationId(resultSet.getLong("organizationId"));
				department.setName(resultSet.getString("name"));
				Optional<List<Employee>> employees = employeeDao.findByDepartmentId(departmentId);
				department.setEmployees(employees.orElse(new ArrayList<>()));
				departments.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		} finally {
			dbUtils.closeConnection(connection);
		}
		if(departments.size() == 0)
			return Optional.empty();
		return Optional.ofNullable(departments);
	}

	@Override
	public Optional<List<Department>> findByOrginizationId(long id) {
		Connection connection = dbUtils.getConnection();
		List<Department> departments = new ArrayList<>();
		ResultSet resultSet = null;
		String query = "select * from department where organizationId=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Department department = new Department();
				long departmentId = resultSet.getLong("id");
				department.setId(departmentId);
				department.setOrganizationId(resultSet.getLong("organizationId"));
				department.setName(resultSet.getString("name"));
				Optional<List<Employee>> employees = employeeDao.findByDepartmentId(departmentId);
				department.setEmployees(employees.orElse(new ArrayList<>()));
				departments.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		} finally {
			dbUtils.closeConnection(connection);
		}
		if(departments.size() == 0)
			return Optional.empty();
		return Optional.ofNullable(departments);
	}

}
