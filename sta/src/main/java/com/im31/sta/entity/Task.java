/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Task entity.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@Entity
@Table(name="t_task")
public class Task extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private String taskName;
	private String name;
	private String description;
	private String imageFile;
	
	public Task() {

	}

	public Task(String taskName, String name, String description, String imageFile) {
		this.taskName = taskName;
		this.name = name;
		this.description = description;
		this.imageFile = imageFile;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
