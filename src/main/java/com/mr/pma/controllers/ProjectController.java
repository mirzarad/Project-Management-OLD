package com.mr.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mr.pma.dao.EmployeeRepository;
import com.mr.pma.dao.ProjectRepository;
import com.mr.pma.entities.Employee;
import com.mr.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired // Autowired: give responsible to the spring to create an instance
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model){
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject); // the "" is the name of what were binding in the html file
		model.addAttribute("allEmployees", employees); // the "" is the name of what were binding in the html file
		return "projects/new-project";	
	}
	
	@PostMapping("/save")
	public String createProject(Project project, @RequestParam List<Long> employees, Model model){
		proRepo.save(project);
		return "redirect:/projects"; // use a redirect to prevent duplicate submissions
	}
}
