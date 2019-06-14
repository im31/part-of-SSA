/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.resource;

import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.im31.sta.controller.UserProfileController;
import com.im31.sta.entity.User;
import com.im31.sta.security.StaUserDetails;

/**
 * Class for assembling {@link UserProfileResource} instance from a {@link User} instance.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
public class UserProfileResourceAssembler {

	/**
	 * Construct an instance of {@link UserSettingsResourceAssembler}.
	 * @param user
	 * @return
	 */
	public Resource<UserProfileResource> toResource(StaUserDetails user) {
		
		Resource<UserProfileResource> resource = createResource(user);
		return resource;
	}
	
	private Resource<UserProfileResource> createResource(StaUserDetails user) {
		
		var resource = new UserProfileResource();
		resource.setId(user.getId());
		resource.setUsername(user.getUsername());
		resource.setName(user.getName());
		resource.setAuthorities(user.getAuthorities().stream()
				.map(authority -> authority.getAuthority()).collect(Collectors.toList()));
		
		Link selfLink = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder
				.methodOn(UserProfileController.class)
				.getUserProfile())
				.withSelfRel();
		
		var result = new Resource<UserProfileResource>(resource, selfLink);
		return result;
	}
}
