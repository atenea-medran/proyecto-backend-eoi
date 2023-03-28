package com.atenea.talentmixer.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.atenea.talentmixer.models.entities.UserAccount;

public interface IuserAccountDao extends CrudRepository<UserAccount, Integer>  {

}
