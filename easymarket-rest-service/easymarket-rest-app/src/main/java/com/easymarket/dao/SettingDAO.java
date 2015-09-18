package com.easymarket.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.easymarket.core.Setting;
import com.google.common.base.Optional;

public class SettingDAO extends AbstractDAO<Setting> {
	private static final String HQL_FIND_ALL = "com.easymarket.core.Setting.findAll";

	public SettingDAO(SessionFactory factory) {
		super(factory);
	}

	public Optional<Setting> findById(Long id) {
		return Optional.fromNullable(get(id));
	}

	public Setting create(Setting obj) {
		return persist(obj);
	}

	public List<Setting> findAll() {
		return list(namedQuery(HQL_FIND_ALL));
	}

	public void delete(Setting obj) {
		currentSession().delete(obj);
	}
}
