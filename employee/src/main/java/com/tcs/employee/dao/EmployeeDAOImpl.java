package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.utils.DBUtils;
import com.tcs.employee.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static EmployeeDAOImpl employeeDAOImpl;

	public static synchronized EmployeeDAO getInstance() {
		if (employeeDAOImpl == null)
			employeeDAOImpl = new EmployeeDAOImpl();
		return employeeDAOImpl;
	}

	private EmployeeDAOImpl() {
	}

	@Override
	public String addEmployee(Employee employee) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String insert = "insert into employee (id, organizationId, departmentId, "
				+ "name, age, position) values(?,?,?,?,?,?)";
		try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setLong(1, employee.getId());
			preparedStatement.setLong(2, employee.getOrganizationId());
			preparedStatement.setLong(3, employee.getDepartmentId());
			preparedStatement.setString(4, employee.getName());
			preparedStatement.setInt(5, employee.getAge());
			preparedStatement.setString(6, employee.getPosition());

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
	public String updateEmployee(long id, Employee employee) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String update = "update employee set id=?, organizationId=?, "
				+ "departmentId=?, name=?, age=?, position=? where id=?";
		try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(update);
			preparedStatement.setLong(1, employee.getId());
			preparedStatement.setLong(2, employee.getOrganizationId());
			preparedStatement.setLong(3, employee.getDepartmentId());
			preparedStatement.setString(4, employee.getName());
			preparedStatement.setInt(5, employee.getAge());
			preparedStatement.setString(6, employee.getPosition());
			preparedStatement.setLong(7, id);

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
	public String deleteEmployee(long id) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String delete = "delete from employee where id=?";
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
	public Optional<Employee> findById(long id) {
		Connection connection = DBUtils.getConnection();
		Employee employee = null;
		ResultSet resultSet = null;
		String query = "select * from employee where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getLong("id"));
				employee.setOrganizationId(resultSet.getLong("organizationId"));
				employee.setDepartmentId(resultSet.getLong("departmentId"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				employee.setPosition(resultSet.getString("position"));
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		return Optional.of(employee);
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		Connection connection = DBUtils.getConnection();
		List<Employee> employees = new ArrayList<>();
		ResultSet resultSet = null;
		String query = "select * from employee";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getLong("id"));
				employee.setOrganizationId(resultSet.getLong("organizationId"));
				employee.setDepartmentId(resultSet.getLong("departmentId"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				employee.setPosition(resultSet.getString("position"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		if(employees.size() == 0)
			return Optional.empty();
		return Optional.of(employees);
	}

	@Override
	public Optional<List<Employee>> findByOrganizationId(long id) {
		Connection connection = DBUtils.getConnection();
		List<Employee> employees = new ArrayList<>();
		ResultSet resultSet = null;
		String query = "select * from employee where organizationId=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getLong("id"));
				employee.setOrganizationId(resultSet.getLong("organizationId"));
				employee.setDepartmentId(resultSet.getLong("departmentId"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				employee.setPosition(resultSet.getString("position"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		if(employees.size() == 0)
			return Optional.empty();
		return Optional.ofNullable(employees);
	}

	@Override
	public Optional<List<Employee>> findByDepartmentId(long id) {
		Connection connection = DBUtils.getConnection();
		List<Employee> employees = new ArrayList<>();
		ResultSet resultSet = null;
		String query = "select * from employee where departmentId=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getLong("id"));
				employee.setOrganizationId(resultSet.getLong("organizationId"));
				employee.setDepartmentId(resultSet.getLong("departmentId"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				employee.setPosition(resultSet.getString("position"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		return Optional.ofNullable(employees);
	}
}
