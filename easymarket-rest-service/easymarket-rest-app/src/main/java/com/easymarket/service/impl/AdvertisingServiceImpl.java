package com.easymarket.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import com.easymarket.core.Advertising;
import com.easymarket.dao.AdvertisingDAO;
import com.easymarket.service.AdvertisingService;
import com.google.common.base.Optional;

//Service not working(fixing inject by dropwizard guicey(https://github.com/xvik/dropwizard-guicey)
//@Service
public class AdvertisingServiceImpl implements AdvertisingService {

	private AdvertisingDAO dao = null;
	
	public AdvertisingServiceImpl(AdvertisingDAO dao) {
		this.dao=dao;
	}
	
	@Override
	public Advertising createAdvertising(Advertising obj) {
		return dao.create(obj);
	}

	public void deleteAdvertising(Long id) {
		Optional<Advertising> result = dao.findById(id);
		dao.delete(result.get());

	}

	public Advertising getAdvertising(Long id) {
		return findSafely(id);
	}

	private Advertising findSafely(long id) {
		final Optional<Advertising> result = dao.findById(id);
		if (!result.isPresent()) {
			throw new NotFoundException("No such Advertising.");
		}
		return result.get();
	}

	public List<Advertising> listAdvertising() {
		return dao.findAll();
	}
}
