package com.tcs.springbootemployee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="department_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	@Column(name="department_id")
	private long id;
	//private long organizationId;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "department")
	private List<Employee> employees = new ArrayList<>();
}
