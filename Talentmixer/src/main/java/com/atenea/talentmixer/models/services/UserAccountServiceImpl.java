package com.atenea.talentmixer.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atenea.talentmixer.models.dao.IuserAccountDao;
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
	public UserAccount findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserAccount save(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

}
