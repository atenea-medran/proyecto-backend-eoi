package com.atenea.talentmixer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atenea.talentmixer.models.dao.IprojectDao;
import com.atenea.talentmixer.models.entities.Project;

@Service
public class ProjectServiceImpl implements IprojectService {
	
	@Autowired  // Para incorporar un componente dentro de otro componente
	private IprojectDao projectDao;
	
	@Override
	@Transactional(readOnly=true)  // Auto-exiges que dentro del método no usarás nada que pueda modificar los datos
	public List<Project> findAll() {
		return (List<Project>)projectDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Project findById(Long id) {
		return projectDao.findById(id).orElse(null);
	}

}
