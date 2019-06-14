package com.im31.sta.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.im31.sta.entity.Role;
import com.im31.sta.entity.User;

public class StaUserDetails implements UserDetails {

		private static final long serialVersionUID = 1L;
		private Collection<? extends GrantedAuthority> authorities;
		private Long id;
		private String password;
		private String username;
		private String name;

		public StaUserDetails(User user) {
			this.id = user.getId();
			this.username = user.getUsername();
			this.name = user.getName();
			this.password = user.getPassword();
			this.authorities = translate(user.getRoles());
		}

		private Collection<? extends GrantedAuthority> translate(Set<Role> roles) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (Role role : roles) {
				String name = role.getName().toUpperCase();
				if (!name.startsWith("ROLE_")) {
					name = "ROLE_" + name;
				}
				authorities.add(new SimpleGrantedAuthority(name));
			}
			return authorities;
		}

		public Long getId() {
			return id;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public String getUsername() {
			return username;
		}

		public String getName() {
			return name;
		}
		
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}
