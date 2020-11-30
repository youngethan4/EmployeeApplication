package com.tcs.departmentrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.departmentrestapi.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	public List<Department> findByOrganizationId(long id);
}
