package com.dynastech.model.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.file.IFileSystemService;
import com.dynastech.base.util.DateUtil;
import com.dynastech.base.util.ExchangUtil;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.flow.entity.Activity;
import com.dynastech.flow.entity.Task;
import com.dynastech.flow.exception.FlowException;
import com.dynastech.flow.service.IFlowService;
import com.dynastech.model.entity.AttachmentFile;
import com.dynastech.model.entity.AttachmentFileExample;
import com.dynastech.model.entity.AttachmentFileRelation;
import com.dynastech.model.entity.AttachmentFileRelationExample;
import com.dynastech.model.entity.CheckDetail;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.entity.PersonAbility;
import com.dynastech.model.entity.PersonAbilityEvaluate;
import com.dynastech.model.entity.PersonAbilityEvaluateExample;
import com.dynastech.model.entity.PersonAbilityExample;
import com.dynastech.model.entity.PersonEvaluationform;
import com.dynastech.model.entity.PersonEvaluationformAbility;
import com.dynastech.model.entity.PersonEvaluationformExample;
import com.dynastech.model.entity.SysAbility;
import com.dynastech.model.entity.SysEvaluationForm;
import com.dynastech.model.entity.SysEvaluationFormExample;
import com.dynastech.model.mapper.AttachmentFileMapper;
import com.dynastech.model.mapper.AttachmentFileRelationMapper;
import com.dynastech.model.mapper.CheckDetailMapper;
import com.dynastech.model.mapper.PersonAbilityEvaluateMapper;
import com.dynastech.model.mapper.PersonAbilityMapper;
import com.dynastech.model.mapper.PersonEvaluationformAbilityMapper;
import com.dynastech.model.mapper.PersonEvaluationformMapper;
import com.dynastech.model.service.IPersonEvaluationFormService;
import com.dynastech.model.service.ISysEvaluationformService;
import com.dynastech.system.entity.User;
import com.dynastech.system.service.IUserManagerService;

@Service
public class PersonEvaluationFormService implements IPersonEvaluationFormService {
	
	private static Logger logger = Logger.getLogger(PersonEvaluationFormService.class);

	@Autowired
	private PersonEvaluationformMapper prfMapper;

	@Autowired
	private PersonAbilityMapper paMapper;

	@Autowired
	private PersonEvaluationformAbilityMapper pefaMapper;

	@Autowired
	private ISysEvaluationformService sefService;

	@Autowired
	private IFileSystemService fileSystemService;
	
	@Autowired
    private AttachmentFileMapper fileMapper;
	
	@Autowired
	private AttachmentFileRelationMapper afrMapper;
	
	@Autowired
	private CommonServiceImpl commonService; 
	
	@Autowired
	private IFlowService flowService;
	
	@Autowired
	private CheckDetailMapper checkDetailMapper;
	
	@Autowired
	private PersonAbilityEvaluateMapper paeMapper;
	
	@Autowired
	private IUserManagerService userManagerService;
	

	@Override
	public int add(PersonEvaluationform pef) {
		return 0;
	}
	
	@Override
	public int update(PersonEvaluationform pef) {
		return prfMapper.updateByPrimaryKeySelective(pef);
	}

	@Override
	public List<PersonEvaluationform> selectByExample(PersonEvaluationformExample example) {
		return prfMapper.selectByExample(example);
	}
	
	@Override
	public PersonEvaluationform findById(String id) {
		return prfMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public String initPersonEvaluationForm(String userId, String applyId, String serial, String level) {

		String pefId = "";

		SysEvaluationFormExample sefe = new SysEvaluationFormExample();
		sefe.createCriteria().andSerialEqualTo(serial).andLevelEqualTo(level)
				.andStatusEqualTo(SysEvaluationForm.STUTUS_1).andIsdeletedEqualTo(false);
		sefe.setOrderByClause("version desc");
		List<SysEvaluationForm> formList = sefService.getSysEvaluationFormList(sefe);
		List<SysAbility> list = null;
		if (!formList.isEmpty()) {

			// 循环调用 暂不考虑性能

			SysEvaluationForm sef = formList.get(0);

			list = sefService.getSysAbilities(sef.getId());

			PersonEvaluationform pef = new PersonEvaluationform();

			Date now = new Date();

			pefId = UUID.randomUUID().toString();

			pef.setId(pefId);
			pef.setDatetimecreated(now);
			pef.setDatetimemodified(now);
			pef.setUserid(userId);
			pef.setSysformid(sef.getId());
			pef.setName(sef.getName());
			pef.setSerial(sef.getSerial());
			pef.setLevel(sef.getLevel());
			pef.setVersion(sef.getVersion());
			pef.setPassmark(sef.getPassmark());
			pef.setAssessornumber(sef.getJudgesnumber());
			pef.setStandardfile(sef.getStandardfile());
			pef.setApplyid(applyId);
			pef.setIsdeleted(false);

			prfMapper.insert(pef);// 个人测评表

			for (SysAbility sa : list) {

				PersonAbility pa = new PersonAbility();
				String paId = UUID.randomUUID().toString();
				
				PersonAbilityExample pae=new PersonAbilityExample();
				pae.createCriteria().andSysAbilityidEqualTo(sa.getId()).andUseridEqualTo(userId);
				List<PersonAbility> paList= paMapper.selectByExample(pae);
				
				if(!paList.isEmpty()){//判定该人已有此能力项（从个人附件管理创建或 以前审核过此序列、此层级的能力项）
					
					//由于系统测评表管理功能修改时  都会重新生成能力项 数据，因此只要 测评表变更后，都会个人能力项也会新增
					paId=paList.get(0).getId();
				}else{
					pa.setId(paId);
					pa.setUserid(userId);
					pa.setDatetimecreated(now);
					pa.setDatetimemodified(now);
					pa.setSysAbilityid(sa.getId());
					pa.setName(sa.getName());
					pa.setTypeid(sa.getTypeid());
					pa.setSerial(sa.getSerial());
					pa.setThelevel(sa.getThelevel());
					pa.setEvidence(sa.getEvidence());
					pa.setScore(sa.getScore());
					pa.setPassmark(sa.getPassmark());
					paMapper.insert(pa);// 个人能力项
				}
				PersonEvaluationformAbility pefa = new PersonEvaluationformAbility();
				pefa.setId(UUID.randomUUID().toString());
				pefa.setEvaluationformid(pefId);
				pefa.setAbilityid(paId);
				pefaMapper.insert(pefa);// 关联关系表
			}
		}
		return pefId;
	}

	
	/**
	 * 2017年9月29日此方法过多的次的访问数据库、过多循环 待优化
	 * 
	 * 2017年10月11日  已优化
	 * **/
	@Override
	public List<PersonAbility> findPersonAbilityByEvalId(String peId) {
		return paMapper.selectFullPersonAbility(peId);
	}
	
	@Override
	public PersonAbility findPersonAbilityByPaId(String paId) {
		
		PersonAbility pa= new PersonAbility();
		pa.setId(paId);
		
		AttachmentFileRelationExample afre=new AttachmentFileRelationExample();
		
		afre.createCriteria().andYwidEqualTo(paId);
		
		List<AttachmentFileRelation> afrList= afrMapper.selectByExample(afre);
		
		List<String> afIds=new Vector<String>();
		
		afrList.forEach(af->{
			afIds.add(af.getFileid());
		});
		
		if(!afIds.isEmpty()){
			AttachmentFileExample afe=new AttachmentFileExample();
			afe.createCriteria().andGuidIn(afIds);
			afe.setOrderByClause("CreateTime");
			List<AttachmentFile> fileList= fileMapper.selectByExample(afe);
			pa.setFiles(fileList);
		}
		
		return pa;
	}
	
	@Override
	public PersonAbility findPersonAbilityWithOutFileByPaId(String paId) {
		return paMapper.selectByPrimaryKey(paId);
	}
	

	@Override
	public int updatePersonAbility(PersonAbility pa) {
		return paMapper.updateByPrimaryKeySelective(pa);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int uploadFile(String paId, String userId, MultipartFile[] file,
			String fileids,String descriptions,String newdescriptions) throws Exception {

		String[] newd=newdescriptions.split(",",-1);
		
		String [] oldfileIds=fileids.split(",");
		String [] oldd=descriptions.split(",");
		
		for(int i=0;i<oldfileIds.length;i++){
			AttachmentFile af = new AttachmentFile();
			af.setGuid(oldfileIds[i]);
			af.setDescription(oldd[i]);
			fileMapper.updateByPrimaryKeySelective(af);
		}
		
		for (int i=0;i<file.length;i++) {

			String fileId = UUID.randomUUID().toString();
			String OriginalName= file[i].getOriginalFilename();
			if(StringUtils.isBlank(OriginalName)){
				continue;
			}
			String cfileName = OriginalName.substring(OriginalName.lastIndexOf("."), OriginalName.length());
			String path = userId + "/" + UUID.randomUUID().toString() + cfileName;

			AttachmentFile af = new AttachmentFile();
			af.setGuid(fileId);
			af.setUserguid(userId);
			af.setIsfolder(0);
			af.setPid("");
			af.setFilename(cfileName);
			af.setFriendlyfilename(OriginalName);
			af.setFilecontenttype(file[i].getContentType());
			af.setFilesize(file[i].getSize());
			af.setFilephysicalpath(path);
			af.setIsdeleted(0);
			af.setCreatetime(new Date());
			af.setCreateuserid(userId);
			af.setDescription(newd[i]);
			fileMapper.insert(af);

			AttachmentFileRelation afr = new AttachmentFileRelation();
			afr.setId(UUID.randomUUID().toString());
			afr.setYwid(paId);
			afr.setFileid(fileId);
			
			afrMapper.insert(afr);
			
			fileSystemService.save(file[i], path);

		}
		return 1;
	}
	
	@Override
	public int deleteFile(String fileId,String paId){
		
		AttachmentFileRelationExample afre=new AttachmentFileRelationExample();
		afre.createCriteria().andFileidEqualTo(fileId).andYwidEqualTo(paId);

		return afrMapper.deleteByExample(afre);
	}
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> check2(CheckDetail cd) throws FlowException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("message",I18nUtil.getTextValue("dynastech.common.operation.success"));
		User currentUser=commonService.getCurrentUser();
		String currentUserId=currentUser.getId();
		
		boolean pass=cd.getResult();
		String taskId=cd.getTaskid();
		Task task=flowService.findTaskByTaskId(taskId);
		
		if(task.getEndTime()!=null){//task已被完成
			data.put("message","该环节已完成！");
			return data;
		}
		
		//当序列小组指定测评人的审核是不能用这个方法的。（其实前端已过滤，后台再过滤一次  避免意外）
		if(task.getTaskDefKey().equals(ModelEnum.FLOW_NODE3_CODE.getValue())){
			return data;
		}
		
		String efId=cd.getApplyid();
		PersonEvaluationform  pef=prfMapper.selectByPrimaryKey(efId);
		
		Activity act=flowService.getCurrentActivity(task);
		
		//下一环节的用户
		List<String> userList=null;
		if(!pass){//不通过
			pef.setStatus(ModelEnum.EVALSTATUS_3.getValue());
			if(cd.getIsback()){
				//审核状态未通过且 是退回状态
				pef.setStatus(ModelEnum.EVALSTATUS_4.getValue());
			}
		}else{//通过
			String nextKey=act.getNextActivityKey();
			if(!StringUtils.isBlank(nextKey)){//不是最后一个节点
				//查询该部门领导角色
				List <User> users=userManagerService.findLeadersByOrgId(pef.getUserid(),nextKey,pef.getSerial());
				if(users.size()<1){
					data.put("message","未找到下一环节审核人");
					return data;
				}
				userList=new ArrayList<String>();
				for(User u:users){
					userList.add(u.getId());
				}
			}else{//最后节点
				pef.setStatus(ModelEnum.EVALSTATUS_2.getValue());
			}
		}
		prfMapper.updateByPrimaryKeySelective(pef);
		flowService.claim(taskId, currentUserId);
		flowService.complete(currentUserId,taskId,pass,userList);
		cd.setId(UUID.randomUUID().toString());
		cd.setCreatedtime(DateUtil.dateToStr(new Date()));
		cd.setUserid(currentUserId);
		cd.setUsername(currentUser.getDisplayname());
		cd.setNodename(task.getName());
		cd.setNodecode(task.getTaskDefKey());
		checkDetailMapper.insert(cd);
		
		/*try{
			User applyUser=userManagerService.selectById(pef.getUserid());
			String msg=ExchangUtil.templete(applyUser,pef, cd);
			ExchangUtil.sendEmail(applyUser.getEmail(),ExchangUtil.topic,msg);
		}catch(Exception e){
			logger.error("sendEmail error pefId:"+pef.getId()+" taskId:"+taskId, e);
		}*/
		
		return data;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> chose(String efId,String taskId,String jsonData) throws FlowException {
		
		Map<String, Object> data = new HashMap<String, Object>();
		Task task=flowService.findTaskByTaskId(taskId);
		if(task.getEndTime()!=null){//task已被完成
			data.put("message","该环节已完成！");
			return data;
		}
		
		User user=commonService.getCurrentUser();
		String userId=user.getId();
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> listDatas = (List<Map<String,Object>>) JSONArray.parse(jsonData);
		
		Set<String> set=new HashSet<>();
		
		for(Map<String,Object> json:listDatas){
			
			String assessorid=(String)json.get("assessorid");
			
			if(StringUtils.isBlank(assessorid)){
				data.put("message","测评人不能为空");//先写汉字了 
				return data;
			}
			
			String abilityid=(String)json.get("id");
			String abilityName=(String)json.get("name");
			
			//删除此条能力项 对应的测评人
			PersonAbilityEvaluateExample paee=new PersonAbilityEvaluateExample();
			paee.createCriteria().andAbilityidEqualTo(abilityid);
			paeMapper.deleteByExample(paee);
				
			String [] assessorids=assessorid.split(",");
			
			Set<String> setIds=new HashSet<String>(Arrays.asList(assessorids));
			
			for(String ids:setIds){
				
				if(!StringUtils.isBlank(ids)){
					set.add(ids);
					PersonAbilityEvaluate pae=new PersonAbilityEvaluate();
					Date now=new Date();
					pae.setAbilityid(abilityid);
					pae.setAbilityname(abilityName);
					pae.setAssessorid(ids);
					pae.setDatetimecreated(now);
					pae.setDatetimemodified(now);
					pae.setIsdeleted(false);
					pae.setEvaluationformid(efId);
					pae.setId(UUID.randomUUID().toString());
					pae.setAssessor(userManagerService.selectById(ids).getDisplayname());
					//新增此条能力项 对的应得测评人
					paeMapper.insert(pae);
				}
			}
		}
		
		List<String> userList=new ArrayList<String>(set);
		
		flowService.claim(taskId, userId);
		flowService.complete(userId,taskId,true,userList);
		data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
		return data;
	}
	
	@Override
	public Map<String, Object> batchChose(String efId, String taskId, String userIds) throws FlowException {

		Map<String, Object> data = new HashMap<String, Object>();
		
		Task task=flowService.findTaskByTaskId(taskId);
		if(task.getEndTime()!=null){//task已被完成
			data.put("message","该环节已完成！");
			return data;
		}
		
		User cuser=commonService.getCurrentUser();
		String userId=cuser.getId();
		//暂用此查询  （此查询包含了 附件信息等  浪费）
		List<PersonAbility> paList=paMapper.selectFullPersonAbility(efId);
		Set<String> set=new HashSet<>();
		for(PersonAbility pa:paList){
			//删除此条能力项 对应的测评人
			PersonAbilityEvaluateExample paee=new PersonAbilityEvaluateExample();
			paee.createCriteria().andAbilityidEqualTo(pa.getId());
			paeMapper.deleteByExample(paee);
			
			for(String uid:userIds.split(",")){
				set.add(uid);
				PersonAbilityEvaluate pae=new PersonAbilityEvaluate();
				Date now=new Date();
				pae.setAbilityid(pa.getId());
				pae.setAbilityname(pa.getName());
				pae.setAssessorid(uid);
				pae.setDatetimecreated(now);
				pae.setDatetimemodified(now);
				pae.setIsdeleted(false);
				pae.setEvaluationformid(efId);
				pae.setId(UUID.randomUUID().toString());
				pae.setAssessor(userManagerService.selectById(uid).getDisplayname());
				//新增此条能力项 对的应得测评人
				paeMapper.insert(pae);
			}
		}
		List<String> userList=new ArrayList<String>(set);
		flowService.claim(taskId, userId);
		flowService.complete(userId,taskId,true,userList);
		data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
		return data;
	}
	
	@Override
	public int markscore(String paeId, Double score,String note) {
		
		PersonAbilityEvaluate pae=new PersonAbilityEvaluate();
		pae.setId(paeId);
		pae.setScoring(score);
		pae.setNote(note);
		return paeMapper.updateByPrimaryKeySelective(pae);
	}
	
	/*@Override
	@Transactional
	public int count(String jsonData) {
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> listDatas = (List<Map<String,Object>>) JSONArray.parse(jsonData);
		int i=0;
		for(Map<String,Object> json:listDatas){
			
			String abilityid=(String)json.get("id");
			Double scoring=Double.valueOf(json.get("scoring")+"");
			PersonAbility pa=new PersonAbility();
			pa.setId(abilityid);
			pa.setScoring(scoring);
			i=paMapper.updateByPrimaryKeySelective(pa);
		}	
		return i;
	}*/
	
	@Override
	public double count(String paId, String efId){
		
		PersonAbilityEvaluateExample paee=new PersonAbilityEvaluateExample();
		
		paee.createCriteria().andAbilityidEqualTo(paId).andEvaluationformidEqualTo(efId).andIsdeletedEqualTo(false);
				
		List<PersonAbilityEvaluate> list= paeMapper.selectByExample(paee);
		
		double avg=list.stream().mapToDouble(pae->pae.getScoring()).average().getAsDouble();
		
		BigDecimal b = new BigDecimal(avg);   
		avg = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		return avg;
	}
	
	
	@Override
	public PersonAbilityEvaluate getPersonAbilityEvaluateById(String id) {
		return paeMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<PersonAbilityEvaluate> getPersonAbilityEvaluateSByPeIdandUserId(String peId, String userId) {
		
		PersonAbilityEvaluateExample paee=new PersonAbilityEvaluateExample();
		
		paee.createCriteria().andEvaluationformidEqualTo(peId).andAssessoridEqualTo(userId);
		
		return paeMapper.selectByExample(paee);
	}
}
