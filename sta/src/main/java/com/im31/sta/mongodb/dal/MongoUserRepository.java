/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.mongodb.dal;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.im31.sta.document.MongoUser;

/**
 * Mongo User repository.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {
	Optional<MongoUser> findByUsername(String username);
	void deleteByUsername(String username);
	void deleteById(String userId);
}
