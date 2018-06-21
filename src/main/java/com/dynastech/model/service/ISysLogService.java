package com.dynastech.model.service;

import java.util.List;

import com.dynastech.model.entity.SysLog;
import com.dynastech.model.entity.SysLogExample;

public interface ISysLogService {
	
	
	/**
	 * @param example
	 * @return
	 */
	public List<SysLog> selectByExample(SysLogExample example);
}
