package com.dynastech.model.service;

import java.util.List;

import com.dynastech.model.entity.FlowBean;
import com.dynastech.model.entity.TaskBean;

public interface IFlowManagerService {
	
	/**
	 * 查询所有流程实例
	 * @param Status 是否已完成  true 为已完成
	 * @param queryStr
	 * @return
	 */
	public List<FlowBean> selectProcinst(Boolean Status,String queryStr);
	
	/**
	 * 查询该流程实例对应的任务
	 * @param procinstId
	 * @return
	 */
	public List<TaskBean> selectTaskByProcinstId(String procinstId);
	
}
