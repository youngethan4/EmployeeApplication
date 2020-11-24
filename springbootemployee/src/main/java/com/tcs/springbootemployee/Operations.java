package com.tcs.springbootemployee;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;

import com.tcs.springbootemployee.Menu.model;
import com.tcs.springbootemployee.model.Department;
import com.tcs.springbootemployee.model.Employee;
import com.tcs.springbootemployee.model.Organization;
import com.tcs.springbootemployee.service.DepartmentService;
import com.tcs.springbootemployee.service.EmployeeService;
import com.tcs.springbootemployee.service.OrganizationService;

public class Operations {

	private static OrganizationService organizationService;
	private static DepartmentService departmentService;
	private static EmployeeService employeeService;
	private static String orgOptions = "a - Add Organization | u - Update Organization | "
			+ "d - Delete Organization | f - Find Organization | " + "g - Get All Organizations | c - Cancel";
	private static String deptOptions = "a - Add Department | u - Update Department | "
			+ "d - Delete Department | f - Find Department | o - Find Department By Organization ID | "
			+ "g - Get All Departments | c - Cancel";
	private static String employeeOptions = "a - Add Employee | u - Update Employee | "
			+ "d - Delete Employee | f - Find Employee | o - Find Employee By Organization ID | "
			+ "g - Get All Employees | c - Cancel";
	private static model selected;

	public static void start(ApplicationContext context, model model) {
		selected = model;
		organizationService = context.getBean(OrganizationService.class);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);

		String input = "";
		if (selected.equals(Menu.model.ORG))
			input = Menu.getInput(orgOptions);
		else if (selected.equals(Menu.model.DEPT))
			input = Menu.getInput(deptOptions);
		else if (selected.equals(Menu.model.EMPLOYEE))
			input = Menu.getInput(employeeOptions);
		else
			return;

		switch (input) {
		case "c":
			return;
		case "a":
			if (selected.equals(Menu.model.ORG))
				saveOrg();
			else if (selected.equals(Menu.model.DEPT))
				saveDept();
			else
				saveEmployee();
			break;
		case "u":
			if (selected.equals(Menu.model.ORG))
				saveOrg();
			else if (selected.equals(Menu.model.DEPT))
				saveDept();
			else
				saveEmployee();
			break;
		case "d":
			if (selected.equals(Menu.model.ORG))
				deleteOrg();
			else if (selected.equals(Menu.model.DEPT))
				deleteDept();
			else
				deleteEmployee();
			break;
		case "f":
			if (selected.equals(Menu.model.ORG))
				findOrg();
			else if (selected.equals(Menu.model.DEPT))
				findDept();
			else
				findEmployee();
			break;
		case "o":
			if (selected.equals(Menu.model.ORG)) {
				System.out.println("invalid command");
				break;
			} else if (selected.equals(Menu.model.DEPT))
				findDeptByOrg();
			else
				findEmployeeByOrg();
			break;
		case "g":
			if (selected.equals(Menu.model.ORG))
				getAllOrgs();
			else if (selected.equals(Menu.model.DEPT))
				getAllDepts();
			else
				getAllEmployees();
			break;
		default:
			System.out.println("invalid command");
		}
	}

	private static void saveOrg() {
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

	private static void deleteOrg() {
		long id = Menu.getInputLong("Enter ID:");
		String result = organizationService.deleteOrganization(id);
		System.out.println(result);
	}

	private static Organization findOrg() {
		long id = Menu.getInputLong("Enter Organization ID:");
		Optional<Organization> optional = organizationService.findById(id);
		Organization org = null;
		if (optional.isPresent()) {
			org = optional.get();
			if (selected.equals(model.ORG))
				printOrganization(org);
		} else {
			System.out.println("Not found");
		}
		return org;
	}

	private static void getAllOrgs() {
		Optional<List<Organization>> optional = organizationService.getOrganizations();
		if (optional.isPresent()) {
			List<Organization> organizations = optional.get();
			System.out.println(organizations.size() + " organizations found:");
			organizations.forEach(o -> {
				printOrganization(o);
			});
		} else {
			System.out.println("None found");
		}
	}

	private static void saveEmployee() {
		long id = Menu.getInputLong("Enter Employee ID:");
		Organization org = findOrg();
		if (org == null)
			return;
		Department dept = findDept();
		if (dept == null)
			return;
		String name = Menu.getInput("Enter name:");
		int age = Menu.getInputInt("Enter age:");
		String position = Menu.getInput("Enter position:");
		Employee employee = new Employee();
		employee.setId(id);
		employee.setOrganization(org);
		employee.setDepartment(dept);
		employee.setName(name);
		employee.setAge(age);
		employee.setPosition(position);
		String result = employeeService.save(employee);
		System.out.println(result);
	}

	private static void deleteEmployee() {
		long id = Menu.getInputLong("Enter ID:");
		String result = employeeService.deleteEmployee(id);
		System.out.println(result);
	}

	private static void findEmployee() {
		long id = Menu.getInputLong("Enter ID:");
		Optional<Employee> optional = employeeService.findById(id);
		if (optional.isPresent())
			printEmployee(optional.get());
		else
			System.out.println("Not found");
	}

	private static void getAllEmployees() {
		Optional<List<Employee>> optional = employeeService.getEmployees();
		if (optional.isPresent()) {
			List<Employee> employees = optional.get();
			System.out.println(employees.size() + " employees found:");
			employees.forEach(e -> {
				printEmployee(e);
			});
		} else {
			System.out.println("None found");
		}
	}

	private static void findEmployeeByOrg() {
		long id = Menu.getInputLong("Enter Organization ID:");
		Optional<Organization> orgOptional = organizationService.findById(id);
		if (orgOptional.isEmpty()) {
			System.out.println("Organization not found.");
			return;
		}
		Optional<List<Employee>> optional = employeeService.findByOrganization(orgOptional.get());
		if (optional.isPresent()) {
			List<Employee> employees = optional.get();
			System.out.println(employees.size() + " employees found:");
			employees.forEach(e -> {
				printEmployee(e);
			});
		} else {
			System.out.println("None found");
		}
	}

	private static void saveDept() {
		long id = Menu.getInputLong("Enter ID:");
		Organization org = findOrg();
		if (org == null)
			return;
		String name = Menu.getInput("Enter name:");
		Department department = new Department();
		department.setId(id);
		department.setName(name);
		department.setOrganization(org);

		String result = departmentService.save(department);
		System.out.println(result);
	}

	private static void deleteDept() {
		long id = Menu.getInputLong("Enter ID:");
		String result = departmentService.deleteDepartment(id);
		System.out.println(result);
	}

	private static Department findDept() {
		long id = Menu.getInputLong("Enter Department ID:");
		Optional<Department> optional = departmentService.findById(id);
		Department dept = null;
		if (optional.isPresent()) {
			dept = optional.get();
			if (selected.equals(model.DEPT))
				printDepartment(dept);
		} else
			System.out.println("Not Found");
		return dept;
	}

	private static void getAllDepts() {
		Optional<List<Department>> optional = departmentService.getDepartments();
		if (optional.isPresent()) {
			List<Department> departments = optional.get();
			System.out.println(departments.size() + " departments found:");
			departments.forEach(d -> {
				printDepartment(d);
			});
		} else
			System.out.println("None Found");
	}

	private static void findDeptByOrg() {
		long id = Menu.getInputLong("Enter Organization ID:");
		Optional<Organization> orgOptional = organizationService.findById(id);
		if (orgOptional.isEmpty()) {
			System.out.println("Organization not found.");
			return;
		}
		Optional<List<Department>> optional = departmentService.findByOrginization(orgOptional.get());
		if (optional.isPresent()) {
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

	private static void printEmployee(Employee employee) {
		System.out.print("ID: " + employee.getId());
		System.out.print("  Org ID: " + employee.getOrganization().getId());
		System.out.print("  Dept ID: " + employee.getDepartment().getId());
		System.out.print("  Name: " + employee.getName());
		System.out.print("  Age: " + employee.getAge());
		System.out.print("  Position: " + employee.getPosition() + "\n");
	}

	private static void printOrganization(Organization organization) {
		System.out.print("ID: " + organization.getId());
		System.out.print("  Name: " + organization.getName());
		System.out.print("  Address: " + organization.getAddress() + "\n");
	}
}
