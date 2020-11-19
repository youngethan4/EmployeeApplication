package com.tcs.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.employee.config.DBConfig;
import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.model.Organization;
import com.tcs.employee.service.DepartmentService;
import com.tcs.employee.service.EmployeeService;
import com.tcs.employee.service.OrganizationService;

public class EmployeeMenu {
	
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static OrganizationService organizationService;

	public static void start() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		organizationService = context.getBean(OrganizationService.class);
		
		String menuOptions = "a - Add Employee | u - Update Employee | "
				+ "d - Delete Employee | f - Find Employee | "
				+ "o - Find Employee By Organization ID | "
				+ "g - Get All Employees | c - Cancel";
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
		long deptId = Menu.getInputLong("Enter Department ID:");
		String name = Menu.getInput("Enter name:");
		int age = Menu.getInputInt("Enter age:");
		String position = Menu.getInput("Enter position:");
		Employee employee = new Employee();
		employee.setId(id);
		//employee.setOrganizationId(orgId);
		//employee.setDepartmentId(deptId);
		Optional<Organization> optional = organizationService.findById(orgId);
		if(optional.isPresent()) {
			employee.setOrganization(optional.get());
		} else {
			System.out.println("Organization does not exists.");
			return;
		}
		Optional<Department> optional2 = departmentService.findById(deptId);
		if(optional2.isPresent()) {
			employee.setDepartment(optional2.get());
		} else {
			System.out.println("Department does not exists.");
			return;
		}
		employee.setName(name);
		employee.setAge(age);
		employee.setPosition(position);
		String result = employeeService.save(employee);
		System.out.println(result);
	}
	
	private static void update() {
		long id = Menu.getInputLong("Enter ID of employee to update:");
		long orgId = Menu.getInputLong("Enter Organization ID:");
		long deptId = Menu.getInputLong("Enter Department ID:");
		String name = Menu.getInput("Enter name:");
		int age = Menu.getInputInt("Enter age:");
		String position = Menu.getInput("Enter position:");
		
		Employee employee = new Employee();
		employee.setId(id);
		employee.setName(name);
		employee.setAge(age);
		employee.setPosition(position);
		
		Optional<Organization> optional = organizationService.findById(orgId);
		if(optional.isPresent()) {
			employee.setOrganization(optional.get());
		} else {
			System.out.println("Organization does not exists.");
			return;
		}
		Optional<Department> optional2 = departmentService.findById(deptId);
		if(optional2.isPresent()) {
			employee.setDepartment(optional2.get());
		} else {
			System.out.println("Department does not exists.");
			return;
		}
		
		String result = employeeService.save(employee);
		System.out.println(result);
	}
	
	private static void delete() {
		long id = Menu.getInputLong("Enter ID:");
		String result = employeeService.deleteEmployee(id);
		System.out.println(result);
	}
	
	private static void find() {
		long id = Menu.getInputLong("Enter ID:");
		Optional<Employee> optional = employeeService.findById(id);
		if(optional.isPresent()) 
			printEmployee(optional.get());
		else
			System.out.println("Not found");
	}
	
	private static void getAll() {
		Optional<List<Employee>> optional = employeeService.getEmployees();
		if(optional.isPresent()) {
			List<Employee> employees = optional.get();
			System.out.println(employees.size() + " employees found:");
			employees.forEach(e -> {
				printEmployee(e);
			});
		} else {
			System.out.println("None found");
		}
	}
	
	private static void findByOrg() {
		long id = Menu.getInputLong("Enter Organization ID:");
		Optional<Organization> orgOptional = organizationService.findById(id);
		if(orgOptional.isEmpty()) {
			System.out.println("Organization not found.");
			return;
		}
		Optional<List<Employee>> optional = employeeService.findByOrganization(orgOptional.get());
		if(optional.isPresent()) {
			List<Employee> employees = optional.get();
			System.out.println(employees.size() + " employees found:");
			employees.forEach(e -> {
				printEmployee(e);
			});
		} else {
			System.out.println("None found");
		}
	}
	
	private static void printEmployee(Employee employee) {
		System.out.print("ID: " + employee.getId());
		System.out.print("  Org ID: " + employee.getOrganization().getId());
		System.out.print("  Dept ID: " + employee.getDepartment().getId());
		System.out.print("  Name: " + employee.getName());
		System.out.print("  Age: " + employee.getAge());
		System.out.print("  Position: " + employee.getPosition() + "\n");
	}
}
