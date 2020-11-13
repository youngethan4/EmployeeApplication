package com.tcs.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.employee.config.DBConfig;
import com.tcs.employee.dao.OrganizationDAO;
import com.tcs.employee.dao.OrganizationDAOImpl;
import com.tcs.employee.model.Organization;
import com.tcs.employee.service.DepartmentService;
import com.tcs.employee.service.OrganizationService;

public class OrganizationMenu {
	
	private static OrganizationService organizationService;

	public static void start() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		organizationService = context.getBean(OrganizationService.class);
		
		String menuOptions = "a - Add Organization | u - Update Organization | "
				+ "d - Delete Organization | f - Find Organization | "
				+ "g - Get All Organizations | c - Cancel";
		String input = Menu.getInput(menuOptions);
		switch(input) {
		case "c":
			return;
		case "a":
			add();
			break;
		case "u":
			update();
			break;
		case "d":
			delete();
			break;
		case "f":
			find();
			break;
		case "g":
			getAll();
			break;
		default:
			System.out.println("invalid command");
		}
	}
	
	private static void add() {
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		String name = Menu.getInput("Enter name:");
		String address = Menu.getInput("Enter address:");
		Organization organization = new Organization();
		organization.setId(id);
		organization.setName(name);
		organization.setAddress(address);
		String result = organizationService.addOrganization(organization);
		System.out.println(result);
	}
	
	private static void update() {
		long updateId = Menu.getInputLong("Enter ID of Org to update:");
		if(updateId == -1)
			return;
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		String name = Menu.getInput("Enter name:");
		if(name.equals(""))
			return;
		String address = Menu.getInput("Enter address:");
		if(address.equals(""))
			return;
		Organization organization = new Organization();
		organization.setId(id);
		organization.setName(name);
		organization.setAddress(address);
		String result = organizationService.updateOrganization(updateId, organization);
		System.out.println(result);
	}
	
	private static void delete() {
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		String result = organizationService.deleteOrganization(id);
		System.out.println(result);
	}
	
	private static void find() {
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		Optional<Organization> optional = organizationService.findById(id);
		if(optional.isPresent()) {
			System.out.println(optional.get());
		} else {
			System.out.println("Not found");
		}
	}
	
	private static void getAll() {
		Optional<List<Organization>> optional = organizationService.getOrganizations();
		if(optional.isPresent()) {
			List<Organization> organizations = optional.get();
			System.out.println(organizations.size() + " organizations found:");
			for(Organization organization: organizations)
				System.out.println(organization);
		} else {
			System.out.println("None found");
		}
	}
}
