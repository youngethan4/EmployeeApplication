package com.tcs.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.employee.model.Employee;
import com.tcs.employee.model.Organization;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByOrganization(Organization organization);
}
