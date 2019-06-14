/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.im31.sta.controller.StockController;
import com.im31.sta.entity.BasicData;

/**
 * Class for assembling {@link BasicDataResource} instance from a {@link BasicData} instance.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
public class BasicDataResourceAssembler {

	/**
	 * Construct an instance of {@link BasicDataResourceAssembler}.
	 * @param basicData
	 * @return
	 */
	public Resource<BasicDataResource> toResource(BasicData basicData) {
		
		Resource<BasicDataResource> resource = createResource(basicData);
		return resource;
	}
	
	public Resources<Resource<BasicDataResource>> toResources(Iterable<BasicData> stocks, Link selfLink) {
		List<Resource<BasicDataResource>> resourceList = new ArrayList<>();
		
		for (BasicData stock : stocks) {
			Resource<BasicDataResource> resource = createResource(stock);
			resourceList.add(resource);
		}
		
		Resources<Resource<BasicDataResource>> resources = new Resources<>(resourceList, selfLink);
		return resources;
	}
	
	private Resource<BasicDataResource> createResource(BasicData basicData) {
		
		BasicDataResource resource = new BasicDataResource(
				basicData.getId(),
				basicData.getCode(),
				basicData.getName(),
				basicData.getMarket().getShortName(),
				basicData.getType(),
				basicData.getListDate(),
				basicData.getStFlag(),
				basicData.getPriceEarningRatio(),
				basicData.getMarketValue(),
				basicData.getCirculationMarketValue(),
				basicData.getHistoryName(),
				basicData.getDelistDate());
		
		Link selfLink = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder
				.methodOn(StockController.class)
				.getStock(basicData.getCode()))
				.withSelfRel();
		
		var result = new Resource<BasicDataResource>(resource, selfLink);
		return result;
	}
}
