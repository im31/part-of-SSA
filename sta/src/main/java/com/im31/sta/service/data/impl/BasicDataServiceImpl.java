/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.service.data.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.im31.sta.dao.BasicDataRepository;
import com.im31.sta.entity.BasicData;
import com.im31.sta.service.data.BasicDataService;

/**
 * Concrete class for {@link BasicData} Service interface.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@Service
public class BasicDataServiceImpl implements BasicDataService {

	@Autowired
	private BasicDataRepository stockBasicInfoRepository;
	
	@Override
	public BasicData add(BasicData stockBasicInfo) {
		BasicData savedInfo = stockBasicInfoRepository.save(stockBasicInfo);
		return savedInfo;
	}

	@Override
	public List<BasicData> update(Iterable<BasicData> stockBasicInfos) {
		List<BasicData> savedInfos = stockBasicInfoRepository.saveAll(stockBasicInfos);
		stockBasicInfoRepository.flush();
		return savedInfos;
	}
	
	@Override
	public List<BasicData> add(Iterable<BasicData> stockBasicInfos) {
		List<BasicData> savedInfos = stockBasicInfoRepository.saveAll(stockBasicInfos);
		return savedInfos;
	}
	
	@Override
	@Transactional
	public void delete(String code) {
		stockBasicInfoRepository.deleteByCode(code);
	}

	@Override
	public List<BasicData> getByCodeOrName(String codeOrName) {
		List<BasicData> infos = stockBasicInfoRepository.findByCodeOrName(codeOrName, codeOrName);
		return infos;
	}
	
	@Override
	public List<BasicData> getByMarket(String market) {
		List<BasicData> infos = stockBasicInfoRepository.findByMarket(market);
		return infos;
	}
	
	@Override
	public List<BasicData> getByMarketAndType(String market, String type) {
		List<BasicData> infos = stockBasicInfoRepository.findByMarketAndType(market, type);
		return infos;
	}
	
	@Override
	public List<BasicData> getByListDate(LocalDate start, LocalDate end) {
		if (start.isAfter(end)) {
			LocalDate swap = start;
			start = end;
			end = swap.plusDays(1);
		}
		
		List<BasicData> infos = stockBasicInfoRepository.findByListDateBetweenOrderByListDateDesc(start, end);
		return infos;
	}
	
	@Override
	public List<BasicData> getAll() {
		List<BasicData> infos = stockBasicInfoRepository.findAll();
		return infos;
	}

	@Override
	public Optional<BasicData> getByCode(String code) {
		var stocks = stockBasicInfoRepository.findByCode(code);
		if (null == stocks || stocks.size() == 0) {
			return Optional.empty();
		}
		
		return Optional.of(stocks.get(0));
	}
}
