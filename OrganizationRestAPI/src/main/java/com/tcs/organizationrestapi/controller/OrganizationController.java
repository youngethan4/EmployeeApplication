package com.tcs.organizationrestapi.controller;

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

import com.tcs.organizationrestapi.exception.ResourceNotFoundException;
import com.tcs.organizationrestapi.model.Organization;
import com.tcs.organizationrestapi.service.OrganizationService;

@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;
	
	@GetMapping
	public List<Organization> getOrganization(){
		return organizationService.getOrganizations().get();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") long id) throws ResourceNotFoundException {
		Organization org = organizationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return ResponseEntity.ok().body(org);
	}
	
	@PostMapping
	public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization, UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {
		Organization org = organizationService.save(organization);
		UriComponents uriComponents = uriComponentsBuilder.path(request.getRequestURI() + "/{id}").buildAndExpand(org.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(org);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Organization> updateOrganization(@PathVariable("id") long id, @Valid @RequestBody Organization organization) throws ResourceNotFoundException {
		organizationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		organization.setId(id);
		Organization org = organizationService.save(organization);
		return ResponseEntity.ok().body(org);
	}
	
	@DeleteMapping("/{id}")
	public HashMap<String, Boolean> deleteOrganization(@PathVariable("id") long id) throws ResourceNotFoundException {
		organizationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		organizationService.deleteOrganization(id);
		
		HashMap<String, Boolean> hashmap = new HashMap<>();
		hashmap.put("deleted", Boolean.TRUE);
		return hashmap;
	}
}
