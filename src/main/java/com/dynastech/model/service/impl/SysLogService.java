package com.dynastech.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynastech.model.entity.SysLog;
import com.dynastech.model.entity.SysLogExample;
import com.dynastech.model.mapper.SysLogMapper;
import com.dynastech.model.service.ISysLogService;

@Service
public class SysLogService implements ISysLogService{
	
	@Autowired
	private SysLogMapper sysLogMapper;
	
	@Override
	public List<SysLog> selectByExample(SysLogExample example) {
		
		
		return sysLogMapper.selectByExample(example);
	}

}
