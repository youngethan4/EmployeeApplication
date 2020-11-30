package com.tcs.organizationrestapi.service;

import java.util.List;
import java.util.Optional;

import com.tcs.organizationrestapi.model.Organization;

public interface OrganizationService {
	
	public Organization save(Organization organization);
	public void deleteOrganization(long id);
	public Optional<Organization> findById(long id);
	public Optional<List<Organization>> getOrganizations();
}
