/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.mongodb.dal;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.im31.sta.document.MongoTask;

/**
 * Mongo Task repository.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@Repository
public interface MongoTaskRepository extends MongoRepository<MongoTask, String> {
	Optional<MongoTask> findByTaskName(String taskName);
}