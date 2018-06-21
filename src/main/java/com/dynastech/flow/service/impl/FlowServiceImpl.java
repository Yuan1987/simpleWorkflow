package com.dynastech.flow.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dynastech.flow.entity.Activity;
import com.dynastech.flow.entity.Identitylink;
import com.dynastech.flow.entity.IdentitylinkExample;
import com.dynastech.flow.entity.Procdef;
import com.dynastech.flow.entity.ProcdefExample;
import com.dynastech.flow.entity.Procinst;
import com.dynastech.flow.entity.QueryTask;
import com.dynastech.flow.entity.QueryTaskResult;
import com.dynastech.flow.entity.Task;
import com.dynastech.flow.entity.TaskExample;
import com.dynastech.flow.exception.FlowException;
import com.dynastech.flow.mapper.IdentitylinkMapper;
import com.dynastech.flow.mapper.ProcdefMapper;
import com.dynastech.flow.mapper.ProcinstMapper;
import com.dynastech.flow.mapper.TaskMapper;
import com.dynastech.flow.service.IFlowService;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.entity.PersonAbilityEvaluate;
import com.dynastech.model.entity.PersonAbilityEvaluateExample;
import com.dynastech.model.mapper.PersonAbilityEvaluateMapper;
import com.dynastech.system.service.IUserManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class FlowServiceImpl implements IFlowService {

	@Autowired
	private ProcinstMapper procinstMapper;

	@Autowired
	private ProcdefMapper procdefMapper;

	@Autowired
	private IdentitylinkMapper identitylinkMapper;

	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private PersonAbilityEvaluateMapper paeMapper;
	
	@Autowired
	private IUserManagerService userManagerService;

	@Override
	public QueryTaskResult querytodoList(List<String> roles, String userId, String key, boolean status, int page,
			int size) {
		QueryTask qt=new QueryTask(key,page,size);
		qt.setStatus(status);
		return querytodoList(roles,userId,qt);
	}
	
	@Override
	public QueryTaskResult querytodoList(List<String> roles, String userId, QueryTask queryTast) {
		
		PageHelper.startPage(queryTast.getPage(), queryTast.getSize());
		PageHelper.orderBy("priority desc");
		List<Task> list = taskMapper.selectTask(roles, userId,queryTast);
		// 取分页信息
		PageInfo<Task> pageInfo = new PageInfo<Task>(list);
		QueryTaskResult qtr = new QueryTaskResult();
		qtr.setCount(pageInfo.getTotal());
		qtr.settList(list);
		
		return qtr;
	}

	@Override
	@Transactional
	public Procinst startProcess(String userId, String key, String businesskey, List<String> ids) {

		Procinst proc = null;

		ProcdefExample pde = new ProcdefExample();

		pde.createCriteria().andDefKeyEqualTo(key).andIsdeletedEqualTo(false);
		pde.setOrderByClause(" version desc");
		List<Procdef> list = procdefMapper.selectByExample(pde);

		Date now = new Date();

		if (!list.isEmpty()) {

			Procdef def = list.get(0);
			String edfJson = def.getDefinition();
			List<Activity> actList = JSONArray.parseArray(edfJson, Activity.class);

			if (!actList.isEmpty()) {
				Activity act = actList.get(0);

				proc = new Procinst();
				String procInstId = UUID.randomUUID().toString();
				proc.setId(procInstId);
				proc.setStartTime(now);
				proc.setStartUserId(userId);
				proc.setProcDef(edfJson);
				proc.setProcDefId(def.getId());
				proc.setStartActKey(act.getKey());
				proc.setBusinessId(businesskey);
				// 流程实例
				procinstMapper.insert(proc);

				String candidateType = act.getCandidateType();

				Task task = new Task();
				String taskId = UUID.randomUUID().toString();
				task.setId(taskId);
				task.setName(act.getName());
				task.setPriority(50);
				task.setProcDefId(def.getId());
				task.setTaskDefKey(act.getKey());
				task.setProcInstId(procInstId);
				task.setStartTime(now);

				taskMapper.insert(task);

				Identitylink il = new Identitylink();
				il.setTaskId(taskId);
				il.setProcInstId(procInstId);
				for (String id : ids) {
					il.setId(UUID.randomUUID().toString());
					if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_USER)) {
						il.setUserId(id);
						il.setRoleId(null);
					} else if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_ROLE)) {
						il.setUserId(null);
						il.setRoleId(id);
					}
					// 任务 与 人员 关系
					identitylinkMapper.insertSelective(il);
				}
			}
		}
		return proc;
	}

	@Override
	public int claim(String taskId, String userId) {
		
		//现无签收功能  直接在完成任务时 填写 Assignee
		return 0;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> complete(String userId,String taskId, boolean pass, List<String> ids) throws FlowException {

		Task task = taskMapper.selectByPrimaryKey(taskId);

		if (task == null) {
			throw new FlowException("can not find task by the taskId");
		}
		Date now = new Date();
		task.setEndTime(now);
		task.setAssignee(userId);
		taskMapper.updateByPrimaryKey(task);

		String procInstId = task.getProcInstId();
		String procDefId = task.getProcDefId();

		Activity activity = this.getCurrentActivity(task);

		TaskExample te = new TaskExample();
		te.createCriteria().andProcInstIdEqualTo(procInstId).andEndTimeIsNull().andIsdeletedEqualTo(false);
		// 剩余未完成的task 数量
		int count = taskMapper.countByExample(te);

		// 会签 现只支持全部通过 效率待优化
		if (!activity.getMultiInstance() || count == 0) {
			// 获取下一活动
			Activity nextActivity = this.getNextActivity(task);
			// 未通过或无下一节点 则完成该流程实例
			if (!pass || nextActivity == null) {
				Procinst procinst = procinstMapper.selectByPrimaryKey(procInstId);
				procinst.setEndTime(now);
				procinst.setEndActKey(task.getTaskDefKey());
				procinstMapper.updateByPrimaryKeySelective(procinst);
			} else {
				// 创建一下节点的任务
				// 下一节点为会签节点-》 每人一个task
				if (nextActivity.getMultiInstance()) {
					for (String id : ids) {

						Task nextTask = new Task();
						String nextTaskId = UUID.randomUUID().toString();
						nextTask.setId(nextTaskId);
						nextTask.setName(nextActivity.getName());
						nextTask.setPriority(50);
						nextTask.setProcDefId(procDefId);
						nextTask.setTaskDefKey(nextActivity.getKey());
						nextTask.setProcInstId(procInstId);
						nextTask.setStartTime(now);
						taskMapper.insert(nextTask);

						Identitylink il = new Identitylink();
						il.setTaskId(nextTaskId);
						il.setProcInstId(procInstId);

						String candidateType = nextActivity.getCandidateType();

						il.setId(UUID.randomUUID().toString());
						if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_USER)) {
							il.setUserId(id);
							il.setRoleId(null);
						} else if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_ROLE)) {
							il.setUserId(null);
							il.setRoleId(id);
						}
						// 任务 与 人员 关系
						identitylinkMapper.insertSelective(il);
					}
				} else {
					Task nextTask = new Task();
					String nextTaskId = UUID.randomUUID().toString();
					nextTask.setId(nextTaskId);
					nextTask.setName(nextActivity.getName());
					nextTask.setPriority(50);
					nextTask.setProcDefId(procDefId);
					nextTask.setTaskDefKey(nextActivity.getKey());
					nextTask.setProcInstId(procInstId);
					nextTask.setStartTime(now);
					taskMapper.insert(nextTask);

					Identitylink il = new Identitylink();
					il.setTaskId(nextTaskId);
					il.setProcInstId(procInstId);

					String candidateType = nextActivity.getCandidateType();

					for (String id : ids) {
						il.setId(UUID.randomUUID().toString());
						if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_USER)) {
							il.setUserId(id);
							il.setRoleId(null);
						} else if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_ROLE)) {
							il.setUserId(null);
							il.setRoleId(id);
						}
						// 任务 与 人员 关系
						identitylinkMapper.insertSelective(il);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获取 当前节点
	 * 
	 * @param task
	 * @return
	 * @throws FlowException
	 */
	@Transactional
	public Activity getCurrentActivity(Task task) throws FlowException {

		Procinst procInst = procinstMapper.selectByPrimaryKey(task.getProcInstId());

		List<Activity> actList = JSONArray.parseArray(procInst.getProcDef(), Activity.class);

		List<Activity> current = actList.stream().filter(act -> act.getKey().equals(task.getTaskDefKey()))
				.collect(Collectors.toList());

		if (current == null || current.isEmpty()) {
			throw new FlowException("can not find activity by the task");
		}
		return current.get(0);
	}

	/**
	 * 获取下一节点
	 * 
	 * @param task
	 * @return
	 * @throws FlowException
	 */
	private Activity getNextActivity(Task task) throws FlowException {

		Procinst procInst = procinstMapper.selectByPrimaryKey(task.getProcInstId());

		List<Activity> actList = JSONArray.parseArray(procInst.getProcDef(), Activity.class);

		List<Activity> current = actList.stream().filter(act -> act.getKey().equals(task.getTaskDefKey()))
				.collect(Collectors.toList());

		if (current == null || current.isEmpty()) {
			throw new FlowException("can not find activity by the task");
		}
		String nextKey = current.get(0).getNextActivityKey();

		if (!StringUtils.isBlank(nextKey)) {
			List<Activity> next = actList.stream().filter(act -> act.getKey().equals(nextKey))
					.collect(Collectors.toList());

			if (!next.isEmpty()) {
				return next.get(0);
			}
		}
		return null;
	}

	@Override
	public Task findTaskByTaskId(String taskId) {
		return taskMapper.selectByPrimaryKey(taskId);
	}

	@Override
	public Procinst findProcinstById(String id) {
		return procinstMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Task> selectTaskByExample(TaskExample te) {
		return taskMapper.selectByExample(te);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addPreNode(String taskId,Activity newActivity,List<String> ids){
		
		Task task = taskMapper.selectByPrimaryKey(taskId);
		Procinst procInst = procinstMapper.selectByPrimaryKey(task.getProcInstId());
		newActivity.setNextActivityKey(task.getTaskDefKey());
		List<Activity> oldActList = JSONArray.parseArray(procInst.getProcDef(), Activity.class);
		int index=0;
		
		for(int i=0;i<oldActList.size();i++){
			Activity act=oldActList.get(i);
			//获取加签的位置
			if(act.getKey().equals(newActivity.getNextActivityKey())){
				index=i;
				break;
			}
		}
		oldActList.add(index, newActivity);
		String newJson=JSON.toJSONString(oldActList);
		procInst.setProcDef(newJson);
		procinstMapper.updateByPrimaryKeySelective(procInst);
		
		task.setIsdeleted(true);
		taskMapper.updateByPrimaryKeySelective(task);
		
		Date now=new Date();
		// 创建节点的任务
		// 节点为会签节点-》 每人一个task
		if (newActivity.getMultiInstance()) {
			for (String id : ids) {

				Task nextTask = new Task();
				String nextTaskId = UUID.randomUUID().toString();
				nextTask.setId(nextTaskId);
				nextTask.setName(newActivity.getName());
				nextTask.setPriority(50);
				nextTask.setProcDefId(procInst.getProcDefId());
				nextTask.setTaskDefKey(newActivity.getKey());
				nextTask.setProcInstId(procInst.getId());
				nextTask.setStartTime(now);
				taskMapper.insert(nextTask);

				Identitylink il = new Identitylink();
				il.setTaskId(nextTaskId);
				il.setProcInstId(procInst.getId());

				String candidateType = newActivity.getCandidateType();

				il.setId(UUID.randomUUID().toString());
				if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_USER)) {
					il.setUserId(id);
					il.setRoleId(null);
				} else if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_ROLE)) {
					il.setUserId(null);
					il.setRoleId(id);
				}
				// 任务 与 人员 关系
				identitylinkMapper.insertSelective(il);
			}
		} else {
			Task nextTask = new Task();
			String nextTaskId = UUID.randomUUID().toString();
			nextTask.setId(nextTaskId);
			nextTask.setName(newActivity.getName());
			nextTask.setPriority(50);
			nextTask.setProcDefId(procInst.getProcDefId());
			nextTask.setTaskDefKey(newActivity.getKey());
			nextTask.setProcInstId(procInst.getId());
			nextTask.setStartTime(now);
			taskMapper.insert(nextTask);

			Identitylink il = new Identitylink();
			il.setTaskId(nextTaskId);
			il.setProcInstId(procInst.getId());

			String candidateType = newActivity.getCandidateType();

			for (String id : ids) {
				il.setId(UUID.randomUUID().toString());
				if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_USER)) {
					il.setUserId(id);
					il.setRoleId(null);
				} else if (StringUtils.equals(candidateType, Activity.ACTIVITY_CANDIDATE_TYPE_ROLE)) {
					il.setUserId(null);
					il.setRoleId(id);
				}
				// 任务 与 人员 关系
				identitylinkMapper.insertSelective(il);
			}
		}
	}
	
	@Override
	public void taskRollback(String taskId) {
		
	}
	
	@Override
	@Transactional
	public int taskTransfer(String taskId, String oldUserId, String newUserId) {
		IdentitylinkExample ile=new IdentitylinkExample();
		ile.createCriteria().andTaskIdEqualTo(taskId).andUserIdEqualTo(oldUserId);
		
		List<Identitylink> list= identitylinkMapper.selectByExample(ile);
		if(list.isEmpty()){
			return 0;
		}
		Identitylink il=list.get(0);
		il.setIsdeleted(true);
		identitylinkMapper.updateByPrimaryKeySelective(il);
		il.setId(UUID.randomUUID().toString());
		il.setUserId(newUserId);
		il.setIsdeleted(false);
		int i=identitylinkMapper.insert(il);
		
		Task task=taskMapper.selectByPrimaryKey(taskId);
		 
		//此处为具体业务代码 其实为流程本身无关，暂放在这，后面再做解耦
		if(task!=null&&StringUtils.equals(task.getTaskDefKey(),ModelEnum.FLOW_NODE4_CODE.getValue())){
			
			Procinst procinst= procinstMapper.selectByPrimaryKey(task.getProcInstId());
		
			PersonAbilityEvaluateExample paee=new PersonAbilityEvaluateExample();
			paee.createCriteria().andEvaluationformidEqualTo(procinst.getBusinessId()).andAssessoridEqualTo(oldUserId);
			List<PersonAbilityEvaluate> paelist=paeMapper.selectByExample(paee);
			for(PersonAbilityEvaluate pae:paelist){
				pae.setIsdeleted(true);
				paeMapper.updateByPrimaryKeySelective(pae);
				
				pae.setId(UUID.randomUUID().toString());
				pae.setIsdeleted(false);
				pae.setAssessorid(newUserId);
				pae.setAssessor(userManagerService.selectById(newUserId).getDisplayname());
				paeMapper.insertSelective(pae);
			}
		}
		
		return i;
	}
}
