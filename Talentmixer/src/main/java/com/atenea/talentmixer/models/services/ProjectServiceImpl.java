package com.atenea.talentmixer.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atenea.talentmixer.models.dao.IprojectDao;
import com.atenea.talentmixer.models.entities.Project;
import com.atenea.talentmixer.utilities.ImageUtils;


@Service
public class ProjectServiceImpl implements IprojectService {
	
	@Autowired
	private IprojectDao projectDao;
	
	private final ImageUtils imageUtils = new ImageUtils();
	
	@Override
	@Transactional(readOnly=true)
	public List<Project> findAll() {
		return (List<Project>)projectDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Project findById(int id) {
		return projectDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		projectDao.deleteById(id);
	}

	@Override
	@Transactional
	public Project save(Project project) {
		if(project.getImage() != null) {
			String ruta = imageUtils.saveImageBase64("projects", project.getImage());
			project.setImage(ruta);
		}
		return projectDao.save(project);
	}


}
