package com.mr.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mr.pma.dao.EmployeeRepository;
import com.mr.pma.dao.ProjectRepository;
import com.mr.pma.dto.ChartData;
import com.mr.pma.dto.EmployeeProject;
import com.mr.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver; 
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		
		// we are querying the database for projects
		List<Project> projects = proRepo.findAll(); // Return a list of repos
		model.addAttribute("projectsList", projects);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		// Lets convert projectData object into a json structure for use in js
		// import com.fasterxml.jackson.databind.ObjectMapper;
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		model.addAttribute("projectStatusCnt", jsonString);
		
		// we are querying the database for employees
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
		
		return "main/home";
	}
	
}
