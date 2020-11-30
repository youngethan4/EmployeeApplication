package com.tcs.departmentrestapi.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.tcs.departmentrestapi.model.Department;
import com.tcs.departmentrestapi.service.DepartmentService;
import com.tcs.departmentrestapi.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@GetMapping
	public List<Department> getAllDepartments(){
		return departmentService.getDepartments().get();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable("id") long id) throws ResourceNotFoundException {
		Department dept = departmentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return ResponseEntity.ok().body(dept);
	}
	
	@GetMapping("/organization/{organizationId}")
	public ResponseEntity<List<Department>> getByOrganizationId(@PathVariable("organizationId") long organizationId) throws ResourceNotFoundException{
		List<Department> depts = departmentService.findByOrginizationId(organizationId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return ResponseEntity.ok().body(depts);
	}
	
	@PostMapping
	public ResponseEntity<Department> createDepartment(@RequestBody Department department, UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {
			Department dept = departmentService.save(department);
			UriComponents uriComponents = uriComponentsBuilder.path(request.getRequestURI() + "/{id}").buildAndExpand(dept.getId());
			return ResponseEntity.created(uriComponents.toUri()).body(dept);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @Valid @RequestBody Department department) throws ResourceNotFoundException {
		departmentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		department.setId(id);
		Department dept = departmentService.save(department);
		return ResponseEntity.ok().body(dept);
	}
	
	@DeleteMapping("{id}")
	public HashMap<String, Boolean> deleteDepartment(@PathVariable("id") long id) throws ResourceNotFoundException {
		departmentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		departmentService.deleteDepartment(id);
		
		HashMap<String, Boolean> hashmap = new HashMap<>();
		hashmap.put("deleted", Boolean.TRUE);
		return hashmap;
	}
}
