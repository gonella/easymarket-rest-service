package com.easymarket.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.easymarket.core.Product;
import com.easymarket.core.User;
import com.google.common.base.Optional;

public class UserDAO extends AbstractDAO<User> {
	private static final String HQL_FIND_ALL = "com.easymarket.core.User.findAll";

	public UserDAO(SessionFactory factory) {
		super(factory);
	}

	public Optional<User> findById(Long id) {
		return Optional.fromNullable(get(id));
	}

	public User create(User obj) {
		return persist(obj);
	}

	public List<User> findAll() {
		return list(namedQuery(HQL_FIND_ALL));
	}

	public void delete(User obj) {
		currentSession().delete(obj);
	}
}
