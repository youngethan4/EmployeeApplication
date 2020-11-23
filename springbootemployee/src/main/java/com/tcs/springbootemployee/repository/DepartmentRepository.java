package com.tcs.springbootemployee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.springbootemployee.model.Department;
import com.tcs.springbootemployee.model.Organization;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	public List<Department> findByOrganization(Organization organization);
}
