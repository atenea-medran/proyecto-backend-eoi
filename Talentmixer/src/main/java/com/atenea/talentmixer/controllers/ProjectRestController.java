package com.atenea.talentmixer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atenea.talentmixer.models.entity.Project;
import com.atenea.talentmixer.models.services.IprojectService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/projects")
public class ProjectRestController {
	
	@Autowired
	private IprojectService projectService;
	
	@GetMapping("")
	public List<Project> index(){
		return projectService.findAll();
	}

}
