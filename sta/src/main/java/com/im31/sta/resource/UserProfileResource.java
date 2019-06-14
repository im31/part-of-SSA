/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.resource;

import java.util.Collection;

import com.im31.sta.entity.User;

/**
 * User profile data for transfer between client and server.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
public class UserProfileResource extends BaseResource {
	
    private String username;
    private String name;
    private Collection<String> authorities;

    public UserProfileResource() {
		super();
	}
    
    public UserProfileResource(
    		long resourceId, String username, String name, Collection<String> authorities) {
        super(resourceId);
        
        this.username = username;
        this.name = name;
        this.authorities = authorities;
    }

    public void updateEntity(User user) {
    	if (null != username && !username.isBlank()) {
    		user.setUsername(username);
    	}
    	
    	if (null != name && !name.isBlank()) {
    		user.setName(name);
    	}
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Collection<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<String> authorities) {
		this.authorities = authorities;
	}
}
