package com.atenea.talentmixer.models.services;

import java.util.List;

import com.atenea.talentmixer.models.entities.UserAccount;

public interface IuserAccountService {
	
	public List<UserAccount> findAll();
	public UserAccount findById(int id);
	public void delete(int id);
	public UserAccount save(UserAccount userAccount);

}
