package com.im31.sta.service.data;

import java.util.List;
import java.util.Optional;

import com.im31.sta.entity.User;

public interface UserService {

	public boolean existsByUsername(String username);
	public boolean existsByEmail(String email);
	public User addUser(User user);
	public void deleteUser(String username);
	public void deleteUser(long userId);
	public User updateUser(User user);
	public User getUserById(Long userId);
	public Optional<User> getUserByUsername(String username);
	public List<User> getUsers();
}
