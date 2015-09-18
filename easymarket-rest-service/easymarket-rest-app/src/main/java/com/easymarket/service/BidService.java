package com.easymarket.service;

import java.util.List;

import com.easymarket.core.Bid;

public interface BidService {

	public Bid createBid(Bid Bid);
	public void deleteBid(Long id);
	public Bid getBid(Long id);
	public List<Bid> listBid();
}
