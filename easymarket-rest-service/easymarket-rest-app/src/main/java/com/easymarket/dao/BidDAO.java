package com.easymarket.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.easymarket.core.Bid;
import com.google.common.base.Optional;

public class BidDAO extends AbstractDAO<Bid> {
	private static final String HQL_FIND_ALL = "com.easymarket.core.Bid.findAll";

	public BidDAO(SessionFactory factory) {
		super(factory);
	}

	public Optional<Bid> findById(Long id) {
		return Optional.fromNullable(get(id));
	}

	public Bid create(Bid obj) {
		return persist(obj);
	}

	public List<Bid> findAll() {
		return list(namedQuery(HQL_FIND_ALL));
	}

	public void delete(Bid obj) {
		currentSession().delete(obj);
	}
}
