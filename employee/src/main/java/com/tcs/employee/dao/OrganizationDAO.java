package com.tcs.employee.dao;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Organization;

public interface OrganizationDAO {

	public String addOrganization(Organization organization);
	public String updateOrganization(long id, Organization organization);
	public String deleteOrganization(long id);
	public Optional<Organization> findById(long id);
	public Optional<List<Organization>> getOrganizations();
}
