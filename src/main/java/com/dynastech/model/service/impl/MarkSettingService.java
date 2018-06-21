package com.dynastech.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynastech.model.entity.MarkSetting;
import com.dynastech.model.mapper.MarkSettingMapper;
import com.dynastech.model.service.IMarkSettingService;

@Service
public class MarkSettingService implements IMarkSettingService{
	
	@Autowired
	private MarkSettingMapper markSettingMapper;
	
	@Override
	public MarkSetting getMarkSetting() {
		return markSettingMapper.selectByPrimaryKey("1");
	}
}
