/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im31.sta.entity.BasicData;

/**
 * Interface for {@link BasicData} repository.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
public interface BasicDataRepository extends JpaRepository<BasicData, Long> {

	List<BasicData> findByCode(String code);
	List<BasicData> findByName(String name);
	List<BasicData> findByCodeOrName(String code, String name);
	List<BasicData> findByMarket(String market);
	List<BasicData> findByMarketAndType(String market, String type);
	List<BasicData> findByListDateBetweenOrderByListDateDesc(LocalDate start, LocalDate end);
	
	void deleteByCode(String code);
}
