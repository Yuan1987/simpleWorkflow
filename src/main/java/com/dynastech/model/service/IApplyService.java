package com.dynastech.model.service;

import java.util.List;
import java.util.Map;

import com.dynastech.flow.exception.FlowException;
import com.dynastech.model.entity.Apply;
import com.dynastech.model.entity.ApplyExample;
import com.dynastech.model.entity.ApplyWithEval;
import com.dynastech.model.entity.CheckDetail;
import com.dynastech.model.entity.CheckDetailExample;

public interface IApplyService {
	
	/**
	 * @param example
	 * @return
	 */
	public List<Apply> selectByExample(ApplyExample example);
	
	/**
	 * @param id
	 * @return
	 */
	public Apply findById(String id);
	
	/**
	 * 新增
	 * @param apply
	 * @return
	 */
	public int add(Apply apply);
	
	/**
	 * 修改
	 * @param apply
	 * @return
	 */
	public int update(Apply apply);
	
	/**
	 * 根据条件更新
	 * @param example
	 * @return
	 */
	public int update (ApplyExample example,Apply apply);
	
	/**
	 * 审核 按角色找对应人员
	 * @param cd
	 * @return
	 */
	public Map<String, Object>  check2(CheckDetail cd)throws FlowException;
	
	/**
	 * 查询审批记录
	 * @param id
	 * @return
	 */
	public List<CheckDetail> selectDetailsById(String id);
	
	/**
	 * 通过用户ID 和申请的审核状态 来查询 申请和对应的评测表
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<ApplyWithEval> selectApplyWithEval(String userId,String status); 
	
	/**
	 * 通过测评表Id 查询 申请和对应的评测表
	 * @param userId
	 * @param status
	 * @return
	 */
	public ApplyWithEval selectApplyWithEvalByEFId(String efId);
	
	/**
	 * 验证该人员能否提交申请 （判定本年、该序列 非退回状态的）
	 * @return
	 */
	public boolean abledCommit(String userId,String seq,String year,String status);
	
	/**
	 * @param cde
	 * @return
	 */
	List<CheckDetail> selectByExample(CheckDetailExample cde);
}
