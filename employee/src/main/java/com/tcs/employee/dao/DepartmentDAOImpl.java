package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.dbcp2.datasources.PerUserPoolDataSource;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.utils.DBUtils;

public class DepartmentDAOImpl implements DepartmentDAO {
	
	private static DepartmentDAOImpl departmentDAOImpl;

	public static synchronized DepartmentDAO getInstance() {
		if (departmentDAOImpl == null)
			departmentDAOImpl = new DepartmentDAOImpl();
		return departmentDAOImpl;
	}

	private DepartmentDAOImpl() {
	}

	@Override
	public String addDepartment(Department department) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String insert = "insert into department (id, organizationId, name) values(?,?,?)";
		try {
			connection.setAutoCommit(false);
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
			DBUtils.closeConnection(connection);
		}
	}

	@Override
	public String updateDepartment(long id, Department department) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String update = "update department set id=?, organizationId=?, name=? "
				+ "where id=?";
		try {
			connection.setAutoCommit(false);
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
			DBUtils.closeConnection(connection);
		}
	}

	@Override
	public String deleteDepartment(long id) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String delete = "delete from department where id=?";
		try {
			connection.setAutoCommit(false);
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
			DBUtils.closeConnection(connection);
		}
	}

	@Override
	public Optional<Department> findById(long id) {
		Connection connection = DBUtils.getConnection();
		Department department = null;
		ResultSet resultSet = null;
		String query = "select * from department where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
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
			DBUtils.closeConnection(connection);
		}
		return Optional.ofNullable(department);
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		Connection connection = DBUtils.getConnection();
		List<Department> departments = new ArrayList<>();
		ResultSet resultSet = null;
		String query = "select * from department";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
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
			DBUtils.closeConnection(connection);
		}
		if(departments.size() == 0)
			return Optional.empty();
		return Optional.ofNullable(departments);
	}

	@Override
	public Optional<List<Department>> findByOrginizationId(long id) {
		Connection connection = DBUtils.getConnection();
		List<Department> departments = new ArrayList<>();
		ResultSet resultSet = null;
		String query = "select * from department where organizationId=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
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
			DBUtils.closeConnection(connection);
		}
		if(departments.size() == 0)
			return Optional.empty();
		return Optional.ofNullable(departments);
	}

}
