package com.tcs.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.employee.config.DBConfig;
import com.tcs.employee.dao.DepartmentDAOImpl;
import com.tcs.employee.dao.EmployeeDAOImpl;
import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.service.EmployeeService;

public class EmployeeMenu {
	
	private static EmployeeService employeeService;

	public static void start() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		employeeService = context.getBean(EmployeeService.class);
		
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
		if(id == -1)
			return;
		long orgId = Menu.getInputLong("Enter Organization ID:");
		if(orgId == -1)
			return;
		long deptId = Menu.getInputLong("Enter Department ID:");
		if(deptId == -1)
			return;
		String name = Menu.getInput("Enter name:");
		if(name.equals(""))
			return;
		int age = Menu.getInputInt("Enter age:");
		if(age == -1)
			return;
		String position = Menu.getInput("Enter position:");
		if(position.equals(""))
			return;
		Employee employee = new Employee();
		employee.setId(id);
		employee.setOrganizationId(orgId);
		employee.setDepartmentId(deptId);
		employee.setName(name);
		employee.setAge(age);
		employee.setPosition(position);
		String result = employeeService.addEmployee(employee);
		System.out.println(result);
	}
	
	private static void update() {
		long updateId = Menu.getInputLong("Enter ID of employee to update:");
		if(updateId == -1)
			return;
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		long orgId = Menu.getInputLong("Enter Organization ID:");
		if(orgId == -1)
			return;
		long deptId = Menu.getInputLong("Enter Department ID:");
		if(deptId == -1)
			return;
		String name = Menu.getInput("Enter name:");
		if(name.equals(""))
			return;
		int age = Menu.getInputInt("Enter age:");
		if(age == -1)
			return;
		String position = Menu.getInput("Enter position:");
		if(position.equals(""))
			return;
		Employee employee = new Employee();
		employee.setId(id);
		employee.setOrganizationId(orgId);
		employee.setDepartmentId(deptId);
		employee.setName(name);
		employee.setAge(age);
		employee.setPosition(position);
		String result = employeeService.updateEmployee(updateId, employee);
		System.out.println(result);
	}
	
	private static void delete() {
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		String result = employeeService.deleteEmployee(id);
		System.out.println(result);
	}
	
	private static void find() {
		long id = Menu.getInputLong("Enter ID:");
		if(id == -1)
			return;
		Optional<Employee> optional = employeeService.findById(id);
		if(optional.isPresent()) 
			System.out.println(optional.get());
		else
			System.out.println("Not found");
	}
	
	private static void getAll() {
		Optional<List<Employee>> optional = employeeService.getEmployees();
		if(optional.isPresent()) {
			List<Employee> employees = optional.get();
			System.out.println(employees.size() + " employees found:");
			for(Employee employee: employees)
				System.out.println(employee);
		} else {
			System.out.println("None found");
		}
	}
	
	private static void findByOrg() {
		long id = Menu.getInputLong("Enter Organization ID:");
		if(id == -1)
			return;
		Optional<List<Employee>> optional = employeeService.findByOrganizationId(id);
		if(optional.isPresent()) {
			List<Employee> employees = optional.get();
			System.out.println(employees.size() + " employees found:");
			for(Employee employee: employees)
				System.out.println(employee);
		} else {
			System.out.println("None found");
		}
	}
}
