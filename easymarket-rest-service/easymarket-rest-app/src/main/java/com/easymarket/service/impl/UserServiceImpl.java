package com.easymarket.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import com.easymarket.core.User;
import com.easymarket.dao.UserDAO;
import com.easymarket.service.UserService;
import com.google.common.base.Optional;

//Service not working(fixing inject by dropwizard guicey(https://github.com/xvik/dropwizard-guicey)
//@Service
public class UserServiceImpl implements UserService {

	private UserDAO dao = null;
	
	public UserServiceImpl(UserDAO dao) {
		this.dao=dao;
	}
	
	@Override
	public User createUser(User obj) {
		return dao.create(obj);
	}

	public void deleteUser(Long id) {
		Optional<User> result = dao.findById(id);
		dao.delete(result.get());

	}

	public User getUser(Long id) {
		return findSafely(id);
	}

	private User findSafely(long id) {
		final Optional<User> result = dao.findById(id);
		if (!result.isPresent()) {
			throw new NotFoundException("No such user.");
		}
		return result.get();
	}

	public List<User> listUser() {
		return dao.findAll();
	}
}
