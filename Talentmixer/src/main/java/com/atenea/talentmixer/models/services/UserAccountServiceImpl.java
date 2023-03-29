package com.atenea.talentmixer.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atenea.talentmixer.models.dao.IuserAccountDao;
import com.atenea.talentmixer.models.entities.Project;
import com.atenea.talentmixer.models.entities.UserAccount;


@Service
public class UserAccountServiceImpl implements IuserAccountService {
	
	@Autowired
	private IuserAccountDao userAccountDao;

	@Override
	@Transactional(readOnly=true)
	public List<UserAccount> findAll() {
		return (List<UserAccount>) userAccountDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserAccount findById(int id) {
		return userAccountDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		userAccountDao.deleteById(id);
	}

	@Override
	@Transactional
	public UserAccount save(UserAccount userAccount) {
		return userAccountDao.save(userAccount);
	}

}
