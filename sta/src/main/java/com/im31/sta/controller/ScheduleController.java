/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.im31.sta.entity.Task;
import com.im31.sta.entity.TaskSchedule;
import com.im31.sta.exception.ResourceNotFoundException;
import com.im31.sta.resource.TaskScheduleResource;
import com.im31.sta.resource.TaskScheduleResourceAssembler;
import com.im31.sta.service.data.TaskScheduleService;
import com.im31.sta.service.data.TaskService;

/**
 * End point for TaskSchedule data.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@RestController
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@RequestMapping("/api")
public class ScheduleController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	@Autowired
	private TaskScheduleService scheduleService;
	
	@Autowired TaskService taskService;
	
	/**
	 * Retrieve the schedule of a specified task according to the given schedule id.
	 * @param scheduleId
	 * @return {@code response} with status 200(OK) and the requested schedule resource data.
	 */
	@GetMapping("/v1/schedules/{scheduleId}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
	public ResponseEntity<Resource<TaskScheduleResource>> getScheduleById(
			@PathVariable long scheduleId) {
		
		Optional<TaskSchedule> optionalSchedule = scheduleService.getById(scheduleId);
		
		if (!optionalSchedule.isPresent()) {
			throw new ResourceNotFoundException("Schedule", "scheduleId", scheduleId);
		}
		
		TaskSchedule schedule = optionalSchedule.get();
		
		Resource<TaskScheduleResource> resource =
				new TaskScheduleResourceAssembler().toResource(schedule);
		
		return ResponseEntity.ok(resource);
	}
	
	/**
	 * Retrieve task schedules according to the given path parameters.
	 * @param taskName
	 * @return {@code response} with status 200(OK) and all schedule resource data belong to
	 * the specified task.
	 */
	@GetMapping(value = "/v1/schedules", produces = {"application/hal+json"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Resources<Resource<TaskScheduleResource>>> getSchedules(
			@RequestParam(value = "taskName", required = false) String taskName) {
		
		List<TaskSchedule> schedules;
		
		if (null == taskName) {
			schedules = scheduleService.findAll();
		} else {
			Optional<Task> optionalTask = taskService.getByTaskName(taskName);
			
			if (!optionalTask.isPresent()) {
				throw new ResourceNotFoundException("Task", "taskName", taskName);
			}
			
			Task task = optionalTask.get();
			
			schedules = scheduleService.findByTaskId(task.getId());
		}
		
		Link link = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder
				.methodOn(ScheduleController.class)
				.getSchedules(taskName))
				.withSelfRel();
		
		Resources<Resource<TaskScheduleResource>> resources =
				new TaskScheduleResourceAssembler().toResources(schedules, link);
		
		return ResponseEntity.ok().body(resources);
	}
	
	/**
	 * Update a schedule.
	 * @param scheduleId
	 * @param resource New schedule informations to be updated.
	 * @return {@code response} with status 200(OK) and the updated schdule resource data.
	 */
	@PutMapping(value = "/v1/schedules/{scheduleId}", produces = {"application/hal+json"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Resource<TaskScheduleResource>> updateSchedule(
			@PathVariable long scheduleId,
			@RequestBody TaskScheduleResource resource) {
		
		Optional<TaskSchedule> optionalSchedule = scheduleService.getById(scheduleId);
		
		if (!optionalSchedule.isPresent()) {
			throw new ResourceNotFoundException("Schedule", "schedule id", scheduleId);
		}
		
		var oldSchedule = optionalSchedule.get();
		resource.updateEntity(oldSchedule);
		
		TaskSchedule updatedSchedule = scheduleService.update(oldSchedule);
		
		Resource<TaskScheduleResource> updatedResource =
				new TaskScheduleResourceAssembler().toResource(updatedSchedule);
		
		return ResponseEntity.ok(updatedResource);
	}
	
}
