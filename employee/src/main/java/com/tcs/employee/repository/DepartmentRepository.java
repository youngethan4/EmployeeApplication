package com.tcs.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Organization;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	public List<Department> findByOrganization(Organization organization);
}
