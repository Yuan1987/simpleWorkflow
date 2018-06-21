package com.dynastech.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dynastech.flow.exception.FlowException;
import com.dynastech.model.entity.CheckDetail;
import com.dynastech.model.entity.PersonAbility;
import com.dynastech.model.entity.PersonAbilityEvaluate;
import com.dynastech.model.entity.PersonEvaluationform;
import com.dynastech.model.entity.PersonEvaluationformExample;

public interface IPersonEvaluationFormService {
	
	public List<PersonEvaluationform> selectByExample(PersonEvaluationformExample example);
	
	public int add(PersonEvaluationform pef);
	
	public int update(PersonEvaluationform pef);
	
	public PersonEvaluationform findById(String id);
	
	/**
	 * 初始化个人申请对应的评测表
	 * 
	 * @param userId
	 * @param applyId
	 * @param serial
	 * @param level
	 * @return 个人测评表的Id
	 */
	public String initPersonEvaluationForm(String userId,String applyId,String serial,String level);
	
	
	/**
	 * 通过个人测评表ID 查询对应的个人能力项
	 * @param peId
	 * @return
	 */
	public List<PersonAbility> findPersonAbilityByEvalId(String peId);
	
	/**
	 * @param pa
	 * @return
	 */
	public int updatePersonAbility(PersonAbility pa);
	
	/**
	 * 能力项附件上传
	 * @param paId 能力项Id
	 * @param userId 用户id
	 * @param file 新上传的文件
	 * @param fileids 已有列表的文件ID
	 * @param descriptions 已有列表的文件描述
	 * @return
	 * @throws Exception
	 */
	public int uploadFile(String paId,String userId,MultipartFile [] file,String fileids,String descriptions,String newdescriptions)throws Exception;
	
	/**
	 * 查询能力项 by Id  因为目前业务，其他数据不需要，实体只含 附件信息 
	 * @param paId
	 * @return
	 */
	public PersonAbility findPersonAbilityByPaId(String paId);
	
	public PersonAbility findPersonAbilityWithOutFileByPaId(String paId);
	
	
	/**
	 * 能力项附件删除 只删除对应关系
	 * @param fileId
	 * @param paId 能力项id
	 * @return
	 */
	public int deleteFile(String fileId,String paId);
	
	/**
	 * 测评表 审核  角色对应人员模式
	 * @param cd
	 * @return
	 */
	public Map<String, Object> check2(CheckDetail cd)throws FlowException;
	
	/**
	 * 测评表 指定测评人
	 * @param cd
	 * @return
	 */
	public Map<String, Object> chose(String efId,String taskId,String jsonData)throws FlowException;
	
	
	/**
	 * 测评表 指定测评人
	 * @param cd
	 * @return
	 */
	public Map<String, Object> batchChose(String efId,String taskId,String userIds)throws FlowException;
	
	
	/**
	 * 单条能力项打分
	 * @param paeId
	 * @param score
	 * @return
	 */
	public int markscore(String paeId,Double score,String note);
	
	/**
	 * 测评表 汇总计算分
	 * @param cd
	 * @return
	 */
	/*public int count(String jsonData);*/
	
	/** 
	 * @param paId 能力项id
	 * @param efId 测评表id
	 * @return
	 */
	public double count(String paId,String efId);
	
	/**
	 * 获取能力项测评信息
	 * @return
	 */
	PersonAbilityEvaluate getPersonAbilityEvaluateById(String id);
	
	/**
	 * 根据测评表Id and userId 获取 此人对某一测评表 的所有能力的测评信息
	 * @param peId
	 * @param userId
	 * @return
	 */
	List<PersonAbilityEvaluate> getPersonAbilityEvaluateSByPeIdandUserId(String peId,String userId);

}
