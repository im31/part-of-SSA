package com.im31.sta.service.data.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.im31.sta.dao.UserRepository;
import com.im31.sta.entity.User;
import com.im31.sta.exception.NotImplementException;
import com.im31.sta.service.data.UserService;
import com.im31.sta.util.ObjectJsonMapper;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		userRepository.deleteByUsername(username);
	}

	@Override
	@Transactional
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}
	
	@Override
	public User updateUser(User user) {
		User updatedUser = userRepository.saveAndFlush(user);
		return updatedUser;
	}

	@Override
	public User getUserById(Long userId) {
		User user = userRepository.findById(userId).get();
		return user;
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> list = new ArrayList<User>();
		list = userRepository.findAll();
		return list;
	}

	@Override
	public boolean existsByUsername(String username) {
		return false; // userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return false; // userRepository.existsByEmail(email);
	}
}
