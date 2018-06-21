package com.dynastech.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynastech.model.entity.FlowBean;
import com.dynastech.model.entity.TaskBean;
import com.dynastech.model.mapper.FlowMapper;
import com.dynastech.model.service.IFlowManagerService;

@Service
public class FlowManagerServiceImpl implements IFlowManagerService{
	
	@Autowired
	private FlowMapper flowMapper;

	@Override
	public List<FlowBean> selectProcinst(Boolean status, String queryStr) {
		return flowMapper.selectProcint(status, queryStr);
	}
	
	@Override
	public List<TaskBean> selectTaskByProcinstId(String procinstId) {
		return flowMapper.selectTaskByProcinstId(procinstId);
	}
}
