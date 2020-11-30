package com.tcs.organizationrestapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="organizaton_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="organization_id")
	private long id;
	private String name;
	private String address;
	
//	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Department> departments = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Employee> employees = new ArrayList<>();
}
