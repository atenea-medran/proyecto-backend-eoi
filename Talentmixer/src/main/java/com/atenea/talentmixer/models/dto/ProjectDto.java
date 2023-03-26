package com.atenea.talentmixer.models.dto;

import java.util.Date;

public class ProjectDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String tittle;
	private String summary;
	private String description;
	private Date createdAt;
	private String image;

	public ProjectDto() {
	}

	public ProjectDto(int id, String tittle, String summary, String description, Date createdAt, String image) {
		this.id = id;
		this.tittle = tittle;
		this.summary = summary;
		this.description = description;
		this.createdAt = createdAt;
		this.image = image;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTittle() {
		return this.tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
