package com.easymarket.service;

import java.util.List;

import com.easymarket.core.User;

public interface UserService {

	public User createUser(User User);
	public void deleteUser(Long id);
	public User getUser(Long id);
	public List<User> listUser();
}
