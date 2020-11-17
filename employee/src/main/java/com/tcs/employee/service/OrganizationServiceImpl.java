package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tcs.employee.model.Organization;
import com.tcs.employee.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public String save(Organization organization) {
		try{
			organizationRepository.save(organization);
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public String deleteOrganization(long id) {
		try{
			organizationRepository.deleteById(id);
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
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
