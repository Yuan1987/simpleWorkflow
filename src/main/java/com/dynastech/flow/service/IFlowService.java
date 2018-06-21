package com.dynastech.flow.service;

import java.util.List;
import java.util.Map;

import com.dynastech.flow.entity.Activity;
import com.dynastech.flow.entity.Procinst;
import com.dynastech.flow.entity.QueryTask;
import com.dynastech.flow.entity.QueryTaskResult;
import com.dynastech.flow.entity.Task;
import com.dynastech.flow.entity.TaskExample;
import com.dynastech.flow.exception.FlowException;


/**
 * 
 * 流程 service
 * @author yuanhb
 *
 */
public interface IFlowService {
	
	/**
	 * 启动流程
	 * 
	 * @param userId 启动人
	 * @param key 流程定义key
	 * @param businesskey 业务id
	 * @param list 下一环节 候选人或候选角色
	 * @return
	 */
	public Procinst startProcess(String userId,String key, String businesskey, List<String> ids);
	
	/**
	 * 任务查询
	 * @param roles
	 * @param userId
	 * @param key
	 * @param page
	 * @param size
	 * @param status false为待办 true 为已办
	 * @return
	 */
	QueryTaskResult querytodoList(List<String> roles,String userId,String key,boolean status,int page,int size);
	
	QueryTaskResult querytodoList(List<String> roles,String userId,QueryTask queryTast);
	
	/**
	 * 任务签收
	 * @param taskId
	 * @param userId
	 * @return
	 */
	int claim(String taskId,String userId);
	
	/**
	 * 完成 任务
	 * @param userId
	 * @param taskId
	 * @param pass
	 * @param ids
	 * @return
	 * @throws FlowException
	 */
	Map<String,Object> complete(String userId,String taskId,boolean pass,List<String> ids) throws FlowException;
	
	/**
	 * @param taskId
	 * @return
	 */
	Task findTaskByTaskId(String taskId);
	
	/**
	 * @param id
	 * @return
	 */
	Procinst findProcinstById(String id);
	
	/**
	 * @param te
	 * @return
	 */
	List<Task> selectTaskByExample(TaskExample te);
	
	/**
	 * 前加签
	 * @param taskId
	 */
	void addPreNode(String taskId,Activity newActivity,List<String> ids);
	
	/**
	 * @param taskId
	 */
	void taskRollback(String taskId);

	
	/**
	 * 获取该task 的流程定义
	 * @param task
	 * @return
	 * @throws FlowException
	 */
	public Activity getCurrentActivity(Task task) throws FlowException;
	
	/**
	 * 任务转办 只能针对user 不支持角色
	 * @param taskId
	 * @param oldUserId
	 * @param newUserId
	 */
	public int taskTransfer(String taskId,String oldUserId,String newUserId);
}
