package com.easymarket.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.easymarket.core.Supplier;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.AbstractDAO;

public class SupplierDAO extends AbstractDAO<Supplier> {
	private static final String HQL_FIND_ALL = "com.easymarket.core.Supplier.findAll";
	private static final String HQL_FIND_ALL_BY_CITY_STATE_COUNTRY = "com.easymarket.core.Supplier.findBasedOnCityAndStateAndCountry";

	public SupplierDAO(SessionFactory factory) {
		super(factory);
	}

	public Optional<Supplier> findById(Long id) {
		return Optional.fromNullable(get(id));
	}

	public Supplier create(Supplier obj) {
		return persist(obj);
	}

	public List<Supplier> findAll() {
		return list(namedQuery(HQL_FIND_ALL));
	}
	public List<Supplier> findBasedOnCityAndStateAndCountry(String city,String state,String country) {
		Query namedQuery = namedQuery(HQL_FIND_ALL_BY_CITY_STATE_COUNTRY).setParameter("city", city).setParameter("state", state).setParameter("country", country);
		return list(namedQuery);
	}

	public void delete(Supplier obj) {
		currentSession().delete(obj);
	}
}
