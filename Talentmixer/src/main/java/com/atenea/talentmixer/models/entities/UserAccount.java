package com.atenea.talentmixer.models.entities;

// default package
// Generated 28 mar 2023 11:57:20 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * UserAccount generated by hbm2java
 */
@Entity
@Table(name = "user_account")
public class UserAccount implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String userAccountName;
	private String userAccountPassword;
	private Set<Project> projects = new HashSet<Project>(0);

	public UserAccount() {
	}

	public UserAccount(int id, String userAccountName, String userAccountPassword) {
		this.id = id;
		this.userAccountName = userAccountName;
		this.userAccountPassword = userAccountPassword;
	}

	public UserAccount(int id, String userAccountName, String userAccountPassword, Set<Project> projects) {
		this.id = id;
		this.userAccountName = userAccountName;
		this.userAccountPassword = userAccountPassword;
		this.projects = projects;
	}
	
	public UserAccount(UserAccount u) {
		this.id = u.id;
		this.userAccountName = u.userAccountName;
		this.userAccountPassword = u.userAccountPassword;
		this.projects = u.projects;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_account_name", nullable = false, length = 100)
	public String getUserAccountName() {
		return this.userAccountName;
	}

	public void setUserAccountName(String userAccountName) {
		this.userAccountName = userAccountName;
	}

	@Column(name = "user_account_password", nullable = false, length = 100)
	public String getUserAccountPassword() {
		return this.userAccountPassword;
	}

	public void setUserAccountPassword(String userAccountPassword) {
		this.userAccountPassword = userAccountPassword;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idUserAccount")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

}
