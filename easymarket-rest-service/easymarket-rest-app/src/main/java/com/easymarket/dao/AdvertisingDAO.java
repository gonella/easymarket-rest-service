package com.easymarket.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.easymarket.core.Advertising;
import com.google.common.base.Optional;

public class AdvertisingDAO extends AbstractDAO<Advertising> {
	private static final String HQL_FIND_ALL = "com.easymarket.core.Advertising.findAll";

	public AdvertisingDAO(SessionFactory factory) {
		super(factory);
	}

	public Optional<Advertising> findById(Long id) {
		return Optional.fromNullable(get(id));
	}

	public Advertising create(Advertising obj) {
		return persist(obj);
	}

	public List<Advertising> findAll() {
		return list(namedQuery(HQL_FIND_ALL));
	}

	public void delete(Advertising obj) {
		currentSession().delete(obj);
	}
}
