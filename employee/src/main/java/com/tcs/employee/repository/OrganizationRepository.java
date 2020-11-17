package com.tcs.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.employee.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
