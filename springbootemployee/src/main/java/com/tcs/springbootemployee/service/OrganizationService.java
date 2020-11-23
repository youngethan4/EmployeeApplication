package com.tcs.springbootemployee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.springbootemployee.model.Organization;

public interface OrganizationService {
	
	public String save(Organization organization);
	public String deleteOrganization(long id);
	public Optional<Organization> findById(long id);
	public Optional<List<Organization>> getOrganizations();
}
