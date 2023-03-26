package com.atenea.talentmixer.models.services;

import java.util.List;

import com.atenea.talentmixer.models.entities.Project;

public interface IprojectService {
	
	public List<Project> findAll();
	public Project findById(int id);
	public void delete(int id);
	public Project save(Project project);

}
