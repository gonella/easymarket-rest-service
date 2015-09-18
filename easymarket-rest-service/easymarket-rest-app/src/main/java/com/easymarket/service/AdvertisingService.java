package com.easymarket.service;

import java.util.List;

import com.easymarket.core.Advertising;

public interface AdvertisingService {

	public Advertising createAdvertising(Advertising Advertising);
	public void deleteAdvertising(Long id);
	public Advertising getAdvertising(Long id);
	public List<Advertising> listAdvertising();
}
