package com.dynastech.model.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.util.DateUtil;
import com.dynastech.base.util.ExchangUtil;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.flow.entity.Activity;
import com.dynastech.flow.entity.Task;
import com.dynastech.flow.exception.FlowException;
import com.dynastech.flow.service.IFlowService;
import com.dynastech.model.entity.Apply;
import com.dynastech.model.entity.ApplyExample;
import com.dynastech.model.entity.ApplyWithEval;
import com.dynastech.model.entity.CheckDetail;
import com.dynastech.model.entity.CheckDetailExample;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.mapper.ApplyMapper;
import com.dynastech.model.mapper.CheckDetailMapper;
import com.dynastech.model.service.IApplyService;
import com.dynastech.system.entity.User;
import com.dynastech.system.service.IUserManagerService;

@Service
public class ApplyService implements IApplyService{
	
	private static Logger logger = Logger.getLogger(ApplyService.class);
	
	@Autowired
	private ApplyMapper applyMapper;
	
	@Autowired
	private CheckDetailMapper checkDetailMapper;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@Autowired
	private IUserManagerService userManagerService;
	
	@Autowired
	private IFlowService flowService;
	
	@Override
	public int add(Apply apply) {
		return applyMapper.insert(apply);
	}
	
	@Override
	public Apply findById(String id) {
		return applyMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Apply> selectByExample(ApplyExample example) {
		return applyMapper.selectByExample(example);
	}
	
	@Override
	public int update(Apply apply) {
		return applyMapper.updateByPrimaryKeySelective(apply);
	}
	
	@Override
	public int update(ApplyExample example,Apply apply) {
		return applyMapper.updateByExampleSelective(apply, example);
	}
	
	@Override
	@Transactional
	public Map<String, Object> check2(CheckDetail cd) throws FlowException{
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		User currentUser=commonService.getCurrentUser();
		String currentUserId=currentUser.getId();
		
		boolean pass=cd.getResult();
		String taskId=cd.getTaskid();
		Task task=flowService.findTaskByTaskId(taskId);
		if(task.getEndTime()!=null){//task已被完成
			data.put("message","该环节已完成！");
			return data;
		}
		String applyId=cd.getApplyid();
		Apply  apply=applyMapper.selectByPrimaryKey(applyId);
		
		Activity act=flowService.getCurrentActivity(task);
		
		List<String> candidate=null;
		if(!pass){//不通过
			apply.setStatus(ModelEnum.STATUS_3.getValue());
			if(cd.getIsback()!=null&&cd.getIsback()){
				apply.setStatus(ModelEnum.STATUS_4.getValue());
			}
		}else{//通过
			String nextKey=act.getNextActivityKey();
			
			if(!StringUtils.isBlank(nextKey)){//不是最后一个节点
				
				List<User> users=userManagerService.findLeadersByOrgId(apply.getUserid(),nextKey,apply.getSeq());
				if(users.size()<1){
					data.put("message","未找到下一环节审批人");
					return data;
				}
				candidate=new ArrayList<String>();
				for(User u:users){
					candidate.add(u.getId());
				}
			}else{//最后节点
				apply.setStatus(ModelEnum.STATUS_2.getValue());
			}
		}
		applyMapper.updateByPrimaryKeySelective(apply);
		flowService.claim(taskId, currentUserId);
		flowService.complete(currentUserId,taskId,pass,candidate);
		cd.setId(UUID.randomUUID().toString());
		cd.setCreatedtime(DateUtil.dateToStr(new Date()));
		cd.setUserid(currentUserId);
		cd.setUsername(currentUser.getDisplayname());
		cd.setNodename(task.getName());
		cd.setNodecode(task.getTaskDefKey());
		int a=checkDetailMapper.insert(cd);
		data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		
	/*	try{
			User applyUser=userManagerService.selectById(apply.getUserid());
			String msg=ExchangUtil.templeteForApply(apply, cd);
			ExchangUtil.sendEmail(applyUser.getEmail(),ExchangUtil.topic,msg);
		}catch(Exception e){
			logger.error("sendEmail error applyId:"+apply.getId()+" taskId:"+taskId, e);
		}*/
		return data;
	}
	
	@Override
	public List<CheckDetail> selectDetailsById(String id) {
		
		CheckDetailExample cde=new CheckDetailExample();
		
		cde.createCriteria().andApplyidEqualTo(id);
		
		cde.setOrderByClause("createdTime");
		
		return checkDetailMapper.selectByExample(cde);
	}
	
	@Override
	public List<ApplyWithEval> selectApplyWithEval(String userId, String status) {
		return applyMapper.selectApplyWithEval(userId, status);
	}
	
	@Override
	public ApplyWithEval selectApplyWithEvalByEFId(String efId) {
		return applyMapper.selectApplyWithEvalByEFId(efId);
	}
	
	@Override
	public boolean abledCommit(String userId, String seq, String year,String status){
		
		int i=applyMapper.getCount(userId, seq, year,status);
		
		return i>0?false:true;
	}
	
	@Override
	public List<CheckDetail> selectByExample(CheckDetailExample cde) {
		return checkDetailMapper.selectByExample(cde);
	}
}
