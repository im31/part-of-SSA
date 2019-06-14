/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.document;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Task entity.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@Document(collection = "dsap.finance.tasks")
public class MongoTask extends MongoBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String taskName;
	private String name;
	private String description;
	private String imageFile;
	
	public MongoTask() {

	}

	public MongoTask(String taskName, String name, String description, String imageFile) {
		this.taskName = taskName;
		this.name = name;
		this.description = description;
		this.imageFile = imageFile;
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
