/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Stock entity.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@Entity
@Table(name="t_stock_basic_info")
public class BasicData {

	private Long id;
	private String code;
	private String name;
	private Market market;
	private String type;
	private LocalDate listDate;
	private String stFlag;
	private Double priceEarningRatio;
	private Double marketValue;
	private Double circulationMarketValue;
	private LocalDate latestDataStamp;
	private String historyName;
	private LocalDate delistDate;

	public BasicData() {
	}

	public BasicData(
		String code,
		String name,
		Market market,
		String type,
		LocalDate listDate,
		String historyName,
		LocalDate delistDate) {
			
		this.code = code;
		this.name = name;
		this.market = market;
		this.type = type;
		this.listDate = listDate;
		this.stFlag = "N";
		this.historyName = historyName;
		this.delistDate = delistDate;
	}
	
	public BasicData(
		String code,
		String name,
		Market market,
		String type,
		LocalDate listDate,
		Double priceEarningRatio,
		Double marketValue,
		Double circulationMarketValue,
		LocalDate latestDataStamp,
		String historyName,
		LocalDate delistDate) {
		
		this.code = code;
		this.name = name;
		this.market = market;
		this.type = type;
		this.listDate = listDate;
		this.stFlag = "N";
		this.priceEarningRatio = priceEarningRatio;
		this.marketValue = marketValue;
		this.circulationMarketValue = circulationMarketValue;
		this.latestDataStamp = latestDataStamp;
		this.historyName = historyName;
		this.delistDate = delistDate;
	}
	
	public BasicData(
			String code,
			String name,
			Market market,
			String type,
			String listDate,
			Double priceEarningRatio,
			Double marketValue,
			Double circulationMarketValue,
			LocalDate latestDataStamp,
			String historyName,
			String delistDate) {
			
			this.code = code;
			this.name = name;
			this.market = market;
			this.type = type;
			
			try {
				this.listDate = LocalDate.parse(listDate, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (Exception e) {
				this.listDate = null;
			}
			
			this.stFlag = "N";
			this.priceEarningRatio = priceEarningRatio;
			this.marketValue = marketValue;
			this.circulationMarketValue = circulationMarketValue;
			this.latestDataStamp = latestDataStamp;
			this.historyName = historyName;
			
			try {
				this.delistDate = LocalDate.parse(delistDate, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (Exception e) {
				this.delistDate = null;
			}
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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getListDate() {
		return listDate;
	}

	public void setListDate(LocalDate listDate) {
		this.listDate = listDate;
	}

	public String getStFlag() {
		return stFlag;
	}

	public void setStFlag(String stFlag) {
		this.stFlag = stFlag;
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

	public String getHistoryName() {
		return historyName;
	}

	public void setHistoryName(String historyName) {
		this.historyName = historyName;
	}

	public LocalDate getLatestDataStamp() {
		return latestDataStamp;
	}

	public void setLatestDataStamp(LocalDate latestDataStamp) {
		this.latestDataStamp = latestDataStamp;
	}

	public LocalDate getDelistDate() {
		return delistDate;
	}

	public void setDelistDate(LocalDate delistDate) {
		this.delistDate = delistDate;
	}
}
