package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employee.dao.OrganizationDAO;
import com.tcs.employee.dao.OrganizationDAOImpl;
import com.tcs.employee.model.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	OrganizationDAO organizationDao;

	@Override
	public String addOrganization(Organization organization) {
		String result = organizationDao.addOrganization(organization);
		return result;
	}

	@Override
	public String updateOrganization(long id, Organization organization) {
		String result = organizationDao.updateOrganization(id, organization);
		return result;
	}

	@Override
	public String deleteOrganization(long id) {
		String result = organizationDao.deleteOrganization(id);
		return result;
	}

	@Override
	public Optional<Organization> findById(long id) {
		Optional<Organization> optional = organizationDao.findById(id);
		return optional;
	}

	@Override
	public Optional<List<Organization>> getOrganizations() {
		Optional<List<Organization>> optional = organizationDao.getOrganizations();
		return optional;
	}

}
