package com.easymarket.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import com.easymarket.core.Bid;
import com.easymarket.dao.BidDAO;
import com.easymarket.service.BidService;
import com.google.common.base.Optional;

//Service not working(fixing inject by dropwizard guicey(https://github.com/xvik/dropwizard-guicey)
//@Service
public class BidServiceImpl implements BidService {

	private BidDAO dao = null;
	
	public BidServiceImpl(BidDAO dao) {
		this.dao=dao;
	}
	
	@Override
	public Bid createBid(Bid obj) {
		return dao.create(obj);
	}

	public void deleteBid(Long id) {
		Optional<Bid> result = dao.findById(id);
		dao.delete(result.get());

	}

	public Bid getBid(Long id) {
		return findSafely(id);
	}

	private Bid findSafely(long id) {
		final Optional<Bid> result = dao.findById(id);
		if (!result.isPresent()) {
			throw new NotFoundException("No such Bid.");
		}
		return result.get();
	}

	public List<Bid> listBid() {
		return dao.findAll();
	}
}
