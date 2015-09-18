package com.easymarket.dao;

import com.easymarket.core.Person;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import java.util.List;

public class PersonDAO extends AbstractDAO<Person> {
	private static final String HQL_FIND_ALL = "com.easymarket.core.Person.findAll";

	public PersonDAO(SessionFactory factory) {
		super(factory);
	}

	public Optional<Person> findById(Long id) {
		return Optional.fromNullable(get(id));
	}

	public Person create(Person person) {
		return persist(person);
	}

	public List<Person> findAll() {
		return list(namedQuery(HQL_FIND_ALL));
	}

	public void delete(Person person) {
		currentSession().delete(person);
	}
}
