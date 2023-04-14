package com.atenea.talentmixer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class ScheduledTruncate {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Scheduled(cron = "0 0 0 * * ?") 
	public void truncate() {
		entityManager.createNativeQuery("TRUNCATE TABLE project CASCADE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE user_account CASCADE").executeUpdate();
		entityManager.createNativeQuery("INSERT INTO user_account").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE user_account CASCADE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE TABLE user_account CASCADE").executeUpdate();
		
	}


}
