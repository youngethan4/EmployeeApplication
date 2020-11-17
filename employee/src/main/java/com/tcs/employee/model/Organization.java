package com.tcs.employee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	@Column(name="organization_id")
	private long id;
	private String name;
	private String address;
	
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
//	private List<Department> departments = new ArrayList<>();
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
//	private List<Employee> employees = new ArrayList<>();
}
