package com.im31.sta.service.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.im31.sta.entity.BasicData;

public interface BasicDataService {

	public BasicData add(BasicData stockBasicInfo);
	public List<BasicData> add(Iterable<BasicData> stockBasicInfos);
	public List<BasicData> update(Iterable<BasicData> stockBasicInfos);
	public void delete(String code);
	public List<BasicData> getByCodeOrName(String codeOrName);
	public List<BasicData> getByListDate(LocalDate start, LocalDate end);
	public List<BasicData> getByMarket(String market);
	public List<BasicData> getByMarketAndType(String market, String type);
	public List<BasicData> getAll();
	public Optional<BasicData> getByCode(String code);
}
