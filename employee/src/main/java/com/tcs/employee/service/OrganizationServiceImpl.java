package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.OrganizationDAO;
import com.tcs.employee.dao.OrganizationDAOImpl;
import com.tcs.employee.model.Organization;

public class OrganizationServiceImpl implements OrganizationService {
	
	private static OrganizationServiceImpl organizationServiceImpl;

	public static synchronized OrganizationService getInstance() {
		if (organizationServiceImpl == null)
			organizationServiceImpl = new OrganizationServiceImpl();
		return organizationServiceImpl;
	}

	private OrganizationServiceImpl() {
	}

	@Override
	public String addOrganization(Organization organization) {
		OrganizationDAO organizationDao = OrganizationDAOImpl.getInstance();
		String result = organizationDao.addOrganization(organization);
		return result;
	}

	@Override
	public String updateOrganization(long id, Organization organization) {
		OrganizationDAO organizationDao = OrganizationDAOImpl.getInstance();
		String result = organizationDao.updateOrganization(id, organization);
		return result;
	}

	@Override
	public String deleteOrganization(long id) {
		OrganizationDAO organizationDao = OrganizationDAOImpl.getInstance();
		String result = organizationDao.deleteOrganization(id);
		return result;
	}

	@Override
	public Optional<Organization> findById(long id) {
		OrganizationDAO organizationDao = OrganizationDAOImpl.getInstance();
		Optional<Organization> optional = organizationDao.findById(id);
		return optional;
	}

	@Override
	public Optional<List<Organization>> getOrganizations() {
		OrganizationDAO organizationDao = OrganizationDAOImpl.getInstance();
		Optional<List<Organization>> optional = organizationDao.getOrganizations();
		return optional;
	}

}
