package com.easymarket.service;

import java.util.List;

import com.easymarket.core.Setting;

public interface SettingService {

	public Setting createSetting(Setting Setting);
	public void deleteSetting(Long id);
	public Setting getSetting(Long id);
	public List<Setting> listSetting();
}
