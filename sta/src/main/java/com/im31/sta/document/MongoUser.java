/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoUser entity.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
@Document(collection = "dsap.users")
public class MongoUser extends MongoBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String name;
	@DBRef
	private Set<MongoRole> roles;
	private String phone;
	private String email;
	private LocalDateTime creation;
	private LocalDateTime loginTime;
	private LocalDateTime lastLoginTime;
	private int loginCount;
	private String loginIp;
	private String lastLoginIp;
	private boolean enabled;

	public MongoUser() {
	}

	public MongoUser(String id, String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;

		MongoRole role = new MongoRole("USER");
		this.roles = new HashSet<MongoRole>();
		this.roles.add(role);

		this.creation = LocalDateTime.now();
		this.loginCount = 0;
		this.enabled = true;
	}

	public MongoUser(String username, String password, String name, Set<MongoRole> roles) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.roles = roles;
		this.creation = LocalDateTime.now();
		this.loginCount = 0;
		this.enabled = true;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MongoRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<MongoRole> roles) {
		this.roles = roles;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
