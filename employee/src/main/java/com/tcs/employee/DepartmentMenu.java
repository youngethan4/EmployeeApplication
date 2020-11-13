package com.tcs.employee;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.DepartmentDAOImpl;
import com.tcs.employee.dao.OrganizationDAOImpl;
import com.tcs.employee.model.Department;
import com.tcs.employee.model.Organization;

public class DepartmentMenu {

	public static void start() {
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
		if(id == -1)
			return;
		long orgId = Menu.getInputLong("Enter Organization ID:");
		if(orgId == -1)
			return;
		String name = Menu.getInput("Enter name:");
		if(name.equals(""))
			return;
		Department department = new Department();
		department.setId(id);
		department.setOrganizationId(orgId);
		department.setName(name);
		String result = DepartmentDAOImpl.getInstance().addDepartment(department);
		System.out.println(result);
	}
	
	private static void update() {
		long updateId = Menu.getInputLong("Enter ID of department to update:");
		if(updateId == -1)
			return;
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		long orgId = Menu.getInputLong("Enter Organization ID:");
		if(orgId == -1)
			return;
		String name = Menu.getInput("Enter name:");
		if(name.equals(""))
			return;
		Department department = new Department();
		department.setId(id);
		department.setOrganizationId(orgId);
		department.setName(name);
		String result = DepartmentDAOImpl.getInstance().updateDepartment(updateId, department);
		System.out.println(result);
	}
	
	private static void delete() {
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		String result = DepartmentDAOImpl.getInstance().deleteDepartment(id);
		System.out.println(result);
	}
	
	private static void find() {
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		Optional<Department> optional = DepartmentDAOImpl.getInstance().findById(id);
		if(optional.isPresent())
			System.out.println(optional.get());
		else
			System.out.println("Not Found");
	}
	
	private static void getAll() {
		Optional<List<Department>> optional = DepartmentDAOImpl.getInstance().getDepartments();
		if(optional.isPresent()) {
			List<Department> departments = optional.get();
			System.out.println(departments.size() + " departments found:");
			for(Department department: departments)
				System.out.println(department);
		} else
			System.out.println("None Found");
	}
	
	private static void findByOrg() {
		long id = Menu.getInputLong("Enter Organization ID:");
		if(id == -1)
			return;
		Optional<List<Department>> optional = DepartmentDAOImpl.getInstance().findByOrginizationId(id);
		if(optional.isPresent()) {
			List<Department> departments = optional.get();
			System.out.println(departments.size() + " departments found:");
			for(Department department: departments)
				System.out.println(department);
		} else
			System.out.println("None Found");
	}
}
