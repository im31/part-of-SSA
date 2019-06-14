/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.im31.sta.entity.BasicData;
import com.im31.sta.entity.Market;
import com.im31.sta.exception.BadRequestException;
import com.im31.sta.exception.ResourceNotFoundException;
import com.im31.sta.resource.BasicDataResource;
import com.im31.sta.resource.BasicDataResourceAssembler;
import com.im31.sta.service.data.BasicDataService;
import com.im31.sta.service.data.MarketService;

/**
 * End point for stock data.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@RestController
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@RequestMapping("/api")
public class StockController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private BasicDataService stockService;
	
	@Autowired
	private MarketService marketService;
	
	/**
	 * Add a new listed stock.
	 * @param basicDataResource
	 * @return {@code response} with status 201(Created) and the location of the new added stock in the header
	 */
	@PostMapping(value = "/v1/stocks", produces = {"application/hal+json"})
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addStock(@RequestBody BasicDataResource basicDataResource) {
		
		Optional<Market> optionalMarket = marketService.getMarketByShortName(basicDataResource.getMarket());
		
		if (!optionalMarket.isPresent()) {
			throw new BadRequestException("Wrong market short name: " + basicDataResource.getMarket());
		}
		
		BasicData stock = new BasicData(
				basicDataResource.getCode(),
				basicDataResource.getName(),
				optionalMarket.get(),
				basicDataResource.getType(),
				basicDataResource.getListDate(),
				basicDataResource.getPriceEarningRatio(),
				basicDataResource.getMarketValue(),
				basicDataResource.getCirculationMarketValue(),
				null,
				basicDataResource.getHistoryName(),
				basicDataResource.getDelistDate());
		
		stock = stockService.add(stock);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/v1/stocks/{code}")
				.buildAndExpand(stock.getCode())
				.toUri();

        return ResponseEntity.created(location).build();
    }
	
	/**
	 * Retrieve the stocks according to the given path parameters.
	 * @param code Stock code.
	 * @return {@code response} with status 200(OK) and the requested stock resource data.
	 */
	@GetMapping("/v1/stocks/{code}")
	public ResponseEntity<Resource<BasicDataResource>> getStock(@PathVariable() String code) {
		
		Optional<BasicData> optionalStock = stockService.getByCode(code);
		
		if (!optionalStock.isPresent()) {
			throw new ResourceNotFoundException("Stock", "code", code);
		}
		
		Resource<BasicDataResource> resource =
				new BasicDataResourceAssembler().toResource(optionalStock.get());
		
		return ResponseEntity.ok().body(resource);
	}
	
	/**
	 * Retrieve the stocks according to the given path parameters.
	 * @param start List date.
	 * @param end List date.
	 * @param codes Stock code array.
	 * @return {@code response} with status 200(OK) and all the stock resource data according to the filter.
	 */
	@GetMapping("/v1/stocks")
	public ResponseEntity<Resources<Resource<BasicDataResource>>> getStocks(
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "codes", required = false) String[] codes) {
		
		if (null == start && null == end && null == codes) {
			throw new BadRequestException("Please set a valid filter.");
		}
		
		List<BasicData> stocks = new ArrayList<BasicData>();
		
		if (codes != null) {
			for (String code: codes) {
				var stock = stockService.getByCode(code);
				if (stock.isPresent()) {
					stocks.add(stock.get());
				}
			}
		} else {
			stocks = stockService.getByListDate(
					LocalDate.parse(start, DateTimeFormatter.ISO_DATE),
					LocalDate.parse(end, DateTimeFormatter.ISO_DATE));
		}
		
		if (null == stocks || stocks.size() == 0) {
			throw new ResourceNotFoundException(
					"Stock", "filter", "start = " + start + ", end = " + end + ", codes = " + codes);
		}
		
		Link selfLink = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder
				.methodOn(StockController.class)
				.getStocks(start, end, codes))
				.withSelfRel();
		
		var resources = new BasicDataResourceAssembler().toResources(stocks, selfLink);
		
		return ResponseEntity.ok().body(resources);
	}
	
	/**
	 * Delete stock created by unit tests.
	 * @param code
	 * @return {@code response} with status 204(no content).
	 */
	@DeleteMapping(value = "/v1/stocks/{code}", produces = {"application/hal+json"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteStock(@PathVariable String code) {
		
		if (null == code || !code.equals("000000")) {
			throw new BadRequestException("Wrong stock code: " + code);
		}
		
		stockService.delete(code);

		return ResponseEntity.noContent().build();
	}
}
