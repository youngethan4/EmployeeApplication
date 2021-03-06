package com.tcs.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.employee.config.DBConfig;
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
		String name = Menu.getInput("Enter name:");
		String address = Menu.getInput("Enter address:");
		Organization organization = new Organization();
		organization.setId(id);
		organization.setName(name);
		organization.setAddress(address);
		String result = organizationService.save(organization);
		System.out.println(result);
	}
	
	private static void update() {
		long id = Menu.getInputLong("Enter ID of Org to update:");
		String name = Menu.getInput("Enter name:");
		String address = Menu.getInput("Enter address:");
		Organization organization = new Organization();
		organization.setId(id);
		organization.setName(name);
		organization.setAddress(address);
		String result = organizationService.save(organization);
		System.out.println(result);
	}
	
	private static void delete() {
		long id = Menu.getInputLong("Enter ID:");
		String result = organizationService.deleteOrganization(id);
		System.out.println(result);
	}
	
	private static void find() {
		long id = Menu.getInputLong("Enter ID:");
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
			organizations.forEach(o -> {
				printOrganization(o);
			});
		} else {
			System.out.println("None found");
		}
	}
	
	private static void printOrganization(Organization organization) {
		System.out.print("ID: " + organization.getId());
		System.out.print("  Name: " + organization.getName());
		System.out.print("  Address: " + organization.getAddress() + "\n");
	}
}
