package com.tcs.springbootemployee;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;

import com.tcs.springbootemployee.model.Department;
import com.tcs.springbootemployee.model.Organization;
import com.tcs.springbootemployee.service.DepartmentService;
import com.tcs.springbootemployee.service.OrganizationService;

public class DepartmentMenu {
	
	private static DepartmentService departmentService;
	private static OrganizationService organizationService;

	public static void start(ApplicationContext context) {
		departmentService = context.getBean(DepartmentService.class);
		organizationService = context.getBean(OrganizationService.class);
		
		String menuOptions = "a - Add Department | u - Update Department | "
				+ "d - Delete Department | f - Find Department | "
				+ "o - Find Department By Organization ID | "
				+ "g - Get All Departments | c - Cancel";
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
		case "o":
			findByOrg();
			break;
		default:
			System.out.println("invalid command");
		}
	}
	
	private static void add() {
		long id = Menu.getInputLong("Enter ID:");
		long orgId = Menu.getInputLong("Enter Organization ID:");
		String name = Menu.getInput("Enter name:");
		Department department = new Department();
		department.setId(id);
		department.setName(name);
		
		Optional<Organization> optional = organizationService.findById(orgId);
		if(optional.isPresent()) {
			department.setOrganization(optional.get());
		} else {
			System.out.println("Organization does not exists.");
			return;
		}
		
		String result = departmentService.save(department);
		System.out.println(result);
	}
	
	private static void update() {
		long id = Menu.getInputLong("Enter ID of department to update:");
		long orgId = Menu.getInputLong("Enter Organization ID:");
		String name = Menu.getInput("Enter name:");
		Department department = new Department();
		department.setId(id);
		department.setName(name);

		Optional<Organization> optional = organizationService.findById(orgId);
		if(optional.isPresent()) {
			department.setOrganization(optional.get());
		} else {
			System.out.println("Organization does not exists.");
			return;
		}
		
		String result = departmentService.save(department);
		System.out.println(result);
	}
	
	private static void delete() {
		long id = Menu.getInputLong("Enter ID:");
		String result = departmentService.deleteDepartment(id);
		System.out.println(result);
	}
	
	private static void find() {
		long id = Menu.getInputLong("Enter ID:");
		Optional<Department> optional = departmentService.findById(id);
		if(optional.isPresent())
			printDepartment(optional.get());
		else
			System.out.println("Not Found");
	}
	
	private static void getAll() {
		Optional<List<Department>> optional = departmentService.getDepartments();
		if(optional.isPresent()) {
			List<Department> departments = optional.get();
			System.out.println(departments.size() + " departments found:");
			departments.forEach(d -> {
				printDepartment(d);
			});
		} else
			System.out.println("None Found");
	}
	
	private static void findByOrg() {
		long id = Menu.getInputLong("Enter Organization ID:");
		Optional<Organization> orgOptional = organizationService.findById(id);
		if(orgOptional.isEmpty()) {
			System.out.println("Organization not found.");
			return;
		}
		Optional<List<Department>> optional = departmentService.findByOrginization(orgOptional.get());
		if(optional.isPresent()) {
			List<Department> departments = optional.get();
			System.out.println(departments.size() + " departments found:");
			departments.forEach(d -> {
				printDepartment(d);
			});
		} else
			System.out.println("None Found");
	}
	
	private static void printDepartment(Department department) {
		System.out.print("ID: " + department.getId());
		System.out.print("  Org ID: " + department.getOrganization().getId());
		System.out.print("  Name: " + department.getName() + "\n");
	}
}
