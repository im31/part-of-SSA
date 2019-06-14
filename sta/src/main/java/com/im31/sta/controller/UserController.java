/**
 * Copyright 2018-2019 the original author or authors.
 */

package com.im31.sta.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.im31.sta.entity.Role;
import com.im31.sta.entity.User;
import com.im31.sta.exception.BadRequestException;
import com.im31.sta.exception.InternalServerErrorException;
import com.im31.sta.exception.ResourceNotFoundException;
import com.im31.sta.resource.SignUpRequest;
import com.im31.sta.resource.UserProfileResource;
import com.im31.sta.resource.UserSettingsResource;
import com.im31.sta.resource.UserSettingsResourceAssembler;
import com.im31.sta.security.StaUserDetails;
import com.im31.sta.service.data.RoleService;
import com.im31.sta.service.data.UserService;

/**
 * End point for user data.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@RestController
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@RequestMapping("/api")
public class UserController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private RoleService roleService;

	@GetMapping(value = "/users/me", produces = {"application/hal+json"})
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public UserProfileResource getCurrentUserProfile() throws Exception {
		
		StaUserDetails currentUser = getCurrentUser();
		
		Collection<String> authorities = currentUser.getAuthorities().stream()
				.map(authority -> authority.getAuthority()).collect(Collectors.toList());
		
        UserProfileResource userProfile = new UserProfileResource(
        		currentUser.getId(),
        		currentUser.getUsername(),
        		currentUser.getUsername(),
        		authorities);
        
        return userProfile;
    }

	/**
	 * Sign up a new account.
	 * @param signUpRequest must not be {@literal null}.
	 * @return {@code response} with status 201 and the location of the new added user resource in the 
	 * header section if succeeds, otherwise an {@code exception} will be returned.
	 */
	@PostMapping(value = "/v1/users/sign-up", produces = {"application/hal+json"})
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		
		Assert.notNull(signUpRequest, "signUpRequest must not be null.");
		
        if(userService.existsByUsername(signUpRequest.getUsername())) {
        	throw new BadRequestException(
        			"Username: " + signUpRequest.getUsername() + "has already existed.");
        }

        if(userService.existsByEmail(signUpRequest.getEmail())) {
        	throw new BadRequestException("Email: " + signUpRequest.getEmail() + "has already existed.");
        }

    	User user = new User();
    	user.setUsername(signUpRequest.getUsername());
    	user.setName(signUpRequest.getName());
    	user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setCreation(LocalDateTime.now());
        user.setLoginCount(0);

        Role userRole = roleService.getByName("User")
                .orElseThrow(() -> new InternalServerErrorException("User Role not found."));

        user.setRoles(Collections.singleton(userRole));

        User result = userService.addUser(user);

        logger.info("New user created: " + result.getUsername());
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).build();
    }
	
	/**
	 * Retrieve user settings according to the given user id.
	 * @param username
	 * @return {@code response} with status 200 and settings if succeeds, otherwise an exception 
	 * will be returned.
	 */
	@GetMapping(value = "/v1/users/{username}/settings", produces = {"application/hal+json"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Resource<UserSettingsResource>> getUserSettings(@PathVariable String username) {
		var optionalUser = userService.getUserByUsername(username);
		
		if (!optionalUser.isPresent()) {
			throw new ResourceNotFoundException("User", "username", username);
		}
		
		var user = optionalUser.get();
		Resource<UserSettingsResource> resource = new UserSettingsResourceAssembler().toResource(user);
		
		return ResponseEntity.ok(resource);
	}
	
	/**
	 * Change user settings, such as account availability.
	 * @param username
	 * @param settings new settings to be updated, it must not be {@code null}.
	 * @return {@code response} with status 200 and updated settings if succeeds, otherwise an exception 
	 * will be returned.
	 */
	@PutMapping(value = "/v1/users/{username}/settings", produces = {"application/hal+json"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Resource<UserSettingsResource>> updateUserSettings(
			@PathVariable String username, @RequestBody UserSettingsResource settings) {
		
		Assert.notNull(settings, "settings must not be null.");
		
		Optional<User> optionalUser = userService.getUserByUsername(username);
		
		if (!optionalUser.isPresent()) {
			throw new ResourceNotFoundException("User", "username", username);
		}
		
		User user = optionalUser.get();
		
		settings.updateEntity(user);
		
		user = userService.updateUser(user);
		Resource<UserSettingsResource> resource = new UserSettingsResourceAssembler().toResource(user);
		
		return ResponseEntity.ok(resource);
	}
	
	/**
	 * Delete a user.
	 * @param username
	 * @return {@code response} with status 204 if succeeds.
	 */
	@DeleteMapping(value = "/v1/users/{username}", produces = {"application/hal+json"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
		
		logger.info("User deleted: " + username);
		
		return ResponseEntity.noContent().build();
	}
	
}
