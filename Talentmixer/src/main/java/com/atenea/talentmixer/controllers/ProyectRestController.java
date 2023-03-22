package com.atenea.talentmixer.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atenea.talentmixer.models.entities.Project;
import com.atenea.talentmixer.services.IprojectService;


@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/api")
public class ProyectRestController {

	@Autowired
	private IprojectService projectService;
	
	@GetMapping("/projects")
	public List<Project> index(){
		return projectService.findAll();
	}
	
	@GetMapping("/projects/{id}")
	public Project show(@PathVariable Long id){
		return projectService.findById(id);
	}
	
	
}
