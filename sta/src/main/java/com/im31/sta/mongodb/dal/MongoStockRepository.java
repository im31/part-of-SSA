/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.mongodb.dal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.im31.sta.document.MongoStock;

/**
 * Mongo Stock repository.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@Repository
public interface MongoStockRepository extends MongoRepository<MongoStock, String> {
	List<MongoStock> findByCode(String code);
	List<MongoStock> findByName(String name);
	List<MongoStock> findByCodeOrName(String code, String name);
	List<MongoStock> findByMarket(String market);
	List<MongoStock> findByMarketAndType(String market, String type);
	List<MongoStock> findByListDateBetweenOrderByListDateDesc(LocalDate start, LocalDate end);
	
	void deleteByCode(String code);
}