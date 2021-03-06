package com.tcs.springbootemployee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.springbootemployee.model.Employee;
import com.tcs.springbootemployee.model.Organization;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByOrganization(Organization organization);
}
