package com.atenea.talentmixer.services;

import java.util.List;

import com.atenea.talentmixer.models.entities.Project;

public interface IprojectService {

	public List<Project> findAll();
	public Project findById(Long id);
}
