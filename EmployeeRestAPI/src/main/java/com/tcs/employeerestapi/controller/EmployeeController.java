package com.tcs.employeerestapi.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcs.employeerestapi.exception.ResourceNotFoundException;
import com.tcs.employeerestapi.model.Employee;
import com.tcs.employeerestapi.service.EmployeeService;

@RestController
@RequestMapping("/api/test/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getEmployees().get();
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) throws ResourceNotFoundException {
		Employee emp = employeeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return ResponseEntity.ok().body(emp);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/organization/{organizationId}")
	public ResponseEntity<List<Employee>> getByDepartmentId(@PathVariable("organizationId") long organizationId) throws ResourceNotFoundException{
		//Organization organization = organizationService.findById(organizationId).get();
		List<Employee> emps = employeeService.findByOrganizationId(organizationId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return ResponseEntity.ok().body(emps);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {
		Employee emp = employeeService.save(employee);
		UriComponents uriComponents = uriComponentsBuilder.path(request.getRequestURI() + "/{id}").buildAndExpand(emp.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(emp);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody Employee employee) throws ResourceNotFoundException {
		employeeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		employee.setId(id);
		Employee emp = employeeService.save(employee);
		return ResponseEntity.ok().body(emp);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public HashMap<String, Boolean> deleteEmployee(@PathVariable("id") long id) throws ResourceNotFoundException {
		employeeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		employeeService.deleteEmployee(id);
		
		HashMap<String, Boolean> hashmap = new HashMap<>();
		hashmap.put("deleted", Boolean.TRUE);
		return hashmap;
	}
}
