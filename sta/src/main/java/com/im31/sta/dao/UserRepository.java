package com.im31.sta.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im31.sta.entity.User;

public interface UserRepository  extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	void deleteByUsername(String username);
	void deleteById(String userId);
	//boolean existsByUsername(String username);
	//boolean existsByEmail(String email);
}
