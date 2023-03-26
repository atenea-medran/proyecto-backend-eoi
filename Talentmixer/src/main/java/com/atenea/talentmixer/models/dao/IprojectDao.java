package com.atenea.talentmixer.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.atenea.talentmixer.models.entities.Project;

public interface IprojectDao extends CrudRepository<Project, Integer> {

}
