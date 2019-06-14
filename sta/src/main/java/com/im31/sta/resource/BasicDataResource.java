/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.im31.sta.entity.BasicData;

/**
 * Stock data for transfer between client and server.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
public class BasicDataResource extends BaseResource {

	private String code;
	private String name;
	private String market;
	private String type;
	private String listDate;
	private String stFlag;
	private Double priceEarningRatio;
	private Double marketValue;
	private Double circulationMarketValue;
	private String historyName;
	private String delistDate;

	public BasicDataResource() {
		super();
	}
	
	public BasicDataResource(
			long resourceId,
			String code,
			String name,
			String market,
			String type,
			String listDate,
			String stFlag,
			Double priceEarningRatio,
			Double marketValue,
			Double circulationMarketValue,
			String historyName,
			String delistDate) {

		super(resourceId);
		
		this.code = code;
		this.name = name;
		this.market = market;
		this.type = type;
		this.listDate = listDate;
		this.stFlag = stFlag;
		this.priceEarningRatio = priceEarningRatio;
		this.marketValue = marketValue;
		this.circulationMarketValue = circulationMarketValue;
		this.historyName = historyName;
		this.delistDate = delistDate;
	}

	public BasicDataResource(
			long resourceId,
			String code,
			String name,
			String market,
			String type,
			LocalDate listDate,
			String stFlag,
			Double priceEarningRatio,
			Double marketValue,
			Double circulationMarketValue,
			String historyName,
			LocalDate delistDate) {

		super(resourceId);
		
		String listDateString = null;
		if (listDate != null) {
			listDateString = listDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		
		String delistDateString = null;
		if (delistDate != null) {
			delistDateString = delistDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		
		this.code = code;
		this.name = name;
		this.market = market;
		this.type = type;
		this.listDate = listDateString;
		this.stFlag = stFlag;
		this.priceEarningRatio = priceEarningRatio;
		this.marketValue = marketValue;
		this.circulationMarketValue = circulationMarketValue;
		this.historyName = historyName;
		this.delistDate = delistDateString;
	}
	
	public static BasicDataResource of(BasicData info) {
		String listDate = null;
		if (info.getListDate() != null) {
			listDate = info.getListDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		
		String delistDate = null;
		if (info.getDelistDate() != null) {
			delistDate = info.getDelistDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		
		BasicDataResource data = new BasicDataResource(
				info.getId(),
				info.getCode(),
				info.getName(),
				info.getMarket().getShortName(),
				info.getType(),
				listDate,
				info.getStFlag(),
				info.getPriceEarningRatio(),
				info.getMarketValue(),
				info.getCirculationMarketValue(),
				info.getHistoryName(),
				delistDate);
		return data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getListDate() {
		return listDate;
	}

	public void setListDate(String listDate) {
		this.listDate = listDate;
	}

	public String getStFlag() {
		return stFlag;
	}

	public void setStFlag(String stFlag) {
		this.stFlag = stFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPriceEarningRatio() {
		return priceEarningRatio;
	}

	public void setPriceEarningRatio(Double priceEarningRatio) {
		this.priceEarningRatio = priceEarningRatio;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public Double getCirculationMarketValue() {
		return circulationMarketValue;
	}

	public void setCirculationMarketValue(Double circulationMarketValue) {
		this.circulationMarketValue = circulationMarketValue;
	}

	public String getDelistDate() {
		return delistDate;
	}

	public void setDelistDate(String delistDate) {
		this.delistDate = delistDate;
	}

	public String getHistoryName() {
		return historyName;
	}

	public void setHistoryName(String historyName) {
		this.historyName = historyName;
	}
}
