package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.model.Organization;
import com.tcs.employee.utils.DBUtils;

public class OrganizationDAOImpl implements OrganizationDAO {
	
	private static OrganizationDAOImpl organizationDAOImpl;

	public static synchronized OrganizationDAO getInstance() {
		if (organizationDAOImpl == null)
			organizationDAOImpl = new OrganizationDAOImpl();
		return organizationDAOImpl;
	}

	private OrganizationDAOImpl() {
	}

	@Override
	public String addOrganization(Organization organization) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String insert = "insert into organization (id, name, address) values(?,?,?)";
		try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setLong(1, organization.getId());
			preparedStatement.setString(2, organization.getName());
			preparedStatement.setString(3, organization.getAddress());

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
	public String updateOrganization(long id, Organization organization) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String update = "update organization set id=?, name=?, address=? "
				+ "where id=?";
		try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(update);
			preparedStatement.setLong(1, organization.getId());
			preparedStatement.setString(2, organization.getName());
			preparedStatement.setString(3, organization.getAddress());
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
	public String deleteOrganization(long id) {
		Connection connection = DBUtils.getConnection();
		int result = 0;
		String delete = "delete from organization where id=?";
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
	public Optional<Organization> findById(long id) {
		Connection connection = DBUtils.getConnection();
		Organization organization = null;
		ResultSet resultSet = null;
		String query = "select * from organization where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
			DepartmentDAO departmentDao = DepartmentDAOImpl.getInstance();
			if (resultSet.next()) {
				organization = new Organization();
				long organizationId = resultSet.getLong("id");
				organization.setId(organizationId);
				organization.setName(resultSet.getString("name"));
				organization.setAddress(resultSet.getString("address"));
				Optional<List<Employee>> employees = employeeDao.findByOrganizationId(organizationId);
				organization.setEmployees(employees.orElse(new ArrayList<>()));
				Optional<List<Department>> departments = departmentDao.findByOrginizationId(organizationId);
				organization.setDepartments(departments.orElse(new ArrayList<>()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		return Optional.ofNullable(organization);
	}

	@Override
	public Optional<List<Organization>> getOrganizations() {
		Connection connection = DBUtils.getConnection();
		List<Organization> organizations = new ArrayList<>();
		ResultSet resultSet = null;
		String query = "select * from organization";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			EmployeeDAO employeeDao = EmployeeDAOImpl.getInstance();
			DepartmentDAO departmentDao = DepartmentDAOImpl.getInstance();
			while (resultSet.next()) {
				Organization organization = new Organization();
				long organizationId = resultSet.getLong("id");
				organization.setId(organizationId);
				organization.setName(resultSet.getString("name"));
				organization.setAddress(resultSet.getString("address"));
				Optional<List<Employee>> employees = employeeDao.findByOrganizationId(organizationId);
				organization.setEmployees(employees.orElse(new ArrayList<>()));
				Optional<List<Department>> departments = departmentDao.findByOrginizationId(organizationId);
				organization.setDepartments(departments.orElse(new ArrayList<>()));
				organizations.add(organization);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		if(organizations.size() == 0)
			return Optional.empty();
		return Optional.ofNullable(organizations);
	}

}
