package com.atenea.talentmixer.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atenea.talentmixer.models.dao.IprojectDao;
import com.atenea.talentmixer.models.entity.Project;


@Service
public class ProjectServiceImpl implements IprojectService {
	
	@Autowired
	private IprojectDao projectDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Project> findAll() {
		return (List<Project>)projectDao.findAll();
	}


}
