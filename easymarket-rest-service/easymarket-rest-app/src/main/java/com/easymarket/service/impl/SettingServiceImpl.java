package com.easymarket.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import com.easymarket.core.Setting;
import com.easymarket.dao.SettingDAO;
import com.easymarket.service.SettingService;
import com.google.common.base.Optional;

//Service not working(fixing inject by dropwizard guicey(https://github.com/xvik/dropwizard-guicey)
//@Service
public class SettingServiceImpl implements SettingService {

	private SettingDAO dao = null;
	
	public SettingServiceImpl(SettingDAO dao) {
		this.dao=dao;
	}
	
	@Override
	public Setting createSetting(Setting obj) {
		return dao.create(obj);
	}

	public void deleteSetting(Long id) {
		Optional<Setting> result = dao.findById(id);
		dao.delete(result.get());

	}

	public Setting getSetting(Long id) {
		return findSafely(id);
	}

	private Setting findSafely(long id) {
		final Optional<Setting> result = dao.findById(id);
		if (!result.isPresent()) {
			throw new NotFoundException("No such Setting.");
		}
		return result.get();
	}

	public List<Setting> listSetting() {
		return dao.findAll();
	}
}
