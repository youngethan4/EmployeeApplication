package com.tcs.organizationrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tcs.organizationrestapi.model.Organization;
import com.tcs.organizationrestapi.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public Organization save(Organization organization) {
		try{
			return organizationRepository.save(organization);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteOrganization(long id) {
		try{
			organizationRepository.deleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public Optional<Organization> findById(long id) {
		Optional<Organization> optional = organizationRepository.findById(id);
		return optional;
	}

	@Override
	@Transactional
	public Optional<List<Organization>> getOrganizations() {
		List<Organization> organizations = organizationRepository.findAll();
		return Optional.ofNullable(organizations);
	}

}
