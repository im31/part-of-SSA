/**
 * Copyright 2018-2019 the original author or authors.
 */

package com.im31.sta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.im31.sta.resource.JwtAuthenticationResource;
import com.im31.sta.resource.LoginRequest;
import com.im31.sta.security.JwtTokenProvider;
import com.im31.sta.security.StaUserDetails;

/**
 * Authentication end point.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@RestController
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@RequestMapping("/api")
public class AuthenticationController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    /**
     * Sign in.
     * @param loginRequest must not be {@literal null}.
     * @return {@code response} with status 200 returned if the login succeeds, 
     * otherwise an {@code exception} returned.
     */
    @PostMapping(value = "/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {

    	Assert.notNull(loginRequest, "login request must not be null.");
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResource(jwt));
    }

    /**
     * Sign in.
     * @param loginRequest must not be {@literal null}.
     * @return {@code response} with status 200 and access token returned if the login succeeds, 
     * otherwise an {@code exception} returned.
     */
    @PostMapping(value = "/v1/auth/sign-in", produces = {"application/hal+json"})
    public ResponseEntity<Resource<JwtAuthenticationResource>> signIn_v1(
    		@RequestBody LoginRequest loginRequest) {
    	
    	Assert.notNull(loginRequest, "login request must not be null.");
    	
    	Authentication authentication = null;
    	try {
    		authentication = authenticationManager.authenticate(
    				 new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
    		);
    	} catch (AuthenticationException e) {
    		logger.info("Failed sign in. username or email: " + loginRequest.getUsernameOrEmail());
    		throw e;
    	}
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        var resource = new JwtAuthenticationResource(jwt);
        
        Link selfLink = ControllerLinkBuilder
        		.linkTo(ControllerLinkBuilder
        		.methodOn(AuthenticationController.class)
        		.signIn_v1(loginRequest)).withSelfRel();
        
		
        
        var result = new Resource<JwtAuthenticationResource>(
        		resource,
        		convertToRelativeLink(selfLink));
        
        var response = ResponseEntity.ok(result);
        
        return response;
    }
    
}
