package com.mr.pma.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity // Marks a POJO that is should be saved to a database; specifies that the object is an entity
public class Project {
	
	// @GeneratedValue(strategy): provides the specification of generation strategies  for the values of Primary Keys
	//	 	param:strategy: (Optional) the primary generation strategy that the persistence provider must use to generate the annotated entity primary key
	//	 	value:GenerationType.AUTO: Specifies that the keys will be automatically generated in the database

	@Id // The mapped column for the primary key of the entity is assumed to be the primary key of the primary table. 
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_seq")
	private long projectId;
	private String name;
	private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
	private String description;
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, 
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", 
			   joinColumns=@JoinColumn(name="project_id"),
			   inverseJoinColumns=@JoinColumn(name="employee_id")
	)
	private List<Employee> employees; 
	
	public Project() {
		
	}
	
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	// Convenience Method
	public void addEmployee(Employee emp) {
		if(employees==null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}
	
}
