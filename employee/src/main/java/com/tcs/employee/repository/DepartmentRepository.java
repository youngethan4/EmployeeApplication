package com.tcs.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.tcs.employee.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@EntityGraph(value = "Department.organizationId", type = EntityGraphType.FETCH)
	public List<Department> findByOrganizationId(long id);
}
