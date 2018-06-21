
package com.dynastech.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.global.Constants;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.common.service.IDictionaryService;
import com.dynastech.flow.entity.Procinst;
import com.dynastech.flow.entity.QueryTaskResult;
import com.dynastech.flow.entity.Task;
import com.dynastech.flow.entity.TaskExample;
import com.dynastech.flow.service.IFlowService;
import com.dynastech.model.entity.Apply;
import com.dynastech.model.entity.CheckDetail;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.service.IApplyService;
import com.dynastech.system.entity.Role;
import com.dynastech.system.entity.User;
import com.dynastech.system.service.IOrgManagerService;
import com.dynastech.system.service.IRoleManagerService;

/**
 * 待我审批 （申报表）
 * 
 * @author yuanhb
 *
 */
@Controller
@RequestMapping("/model/check")
public class CheckController {

	private static Logger logger = Logger.getLogger(CheckController.class);
	
	@Autowired
	private IApplyService applyService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@Autowired
	IOrgManagerService orgManagerService;
	
	@Autowired
	private IFlowService flowService;
	
	@Autowired
	private IRoleManagerService roleManagerService;
	
	@Autowired
	private IDictionaryService dicService;
	
	
	@RequiresPermissions("check:view")
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/check_index");
		return mv;
	}
	
	@RequiresPermissions("check:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "15", required = false) int size,
			@RequestParam(value="status",defaultValue="0")boolean status) {

		Map<String, Object> data = new HashMap<String, Object>();
		
		User user=commonService.getCurrentUser();
		
		String userId=user.getId();
		
		List<Apply> list=new Vector<Apply>();
		
		List<String> roleIds=new ArrayList<String>();
		
		List <Role> roles=roleManagerService.selectRolesByUserId(userId);
		
		for(Role role:roles){
			roleIds.add(role.getId());
		}
		
		QueryTaskResult qtr = flowService.querytodoList(roleIds,userId,Constants.FLOW_APPLY,status,page,size);
		long count=qtr.getCount();
		for(Task task:qtr.gettList()){
			String processinsId=task.getProcInstId();
			Procinst pi=flowService.findProcinstById(processinsId);
			String businesskey=pi.getBusinessId();
			Apply  apply=applyService.findById(businesskey);
			apply.setTaskId(task.getId());
			apply.setTaskName(task.getName());
			apply.setTaskKey(task.getTaskDefKey());
			apply.setComplete(status);
			apply.setTaskEndTime(task.getEndTime());
			list.add(apply);
		}
		data.put("rows", list);
		data.put("total", count);

		return data;
	}

	@RequiresPermissions("check:view")
	@RequestMapping("/checkApplyInfo.html")
	public ModelAndView checkApplyInfo(String id,String taskId,boolean complete) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/checkApplyInfo");
		mv.addObject("detailList",applyService.selectDetailsById(id));
		mv.addObject("apply",applyService.findById(id));
		mv.addObject("taskId", taskId);
		mv.addObject("jbList", dicService.findByKind(Constants.DIC_KIND_SBJB));
		mv.addObject("xlList", dicService.findByKind(Constants.DIC_KIND_SBXL));
		mv.addObject("complete", complete);
		return mv;
	}
	
	@RequiresPermissions("check:view")
	@RequestMapping("/toCheck.html")
	public ModelAndView toCheck(String id,boolean pass,String taskId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/check");
		mv.addObject("pass",pass);
		mv.addObject("taskId",taskId);
		mv.addObject("applyId", id);
		
		Task task=flowService.findTaskByTaskId(taskId);
		if(ModelEnum.FLOW_NODE1_CODE.getValue().equals(task.getTaskDefKey())){//部门领导审批
			mv.addObject("isDEPT",true);
		}
		return mv;
	}
	
	@RequiresPermissions("check:view")
	@RequestMapping("/toBacthCheck.html")
	public ModelAndView toBacthCheck(String data){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/check_batch");
		mv.addObject("type", Constants.FLOW_APPLY);
		mv.addObject("data", StringEscapeUtils.escapeJson(data));
		return mv;
	}
	
	/**
	 * 按角色对应人员模式（由于角色不再和组织绑定，现采取此模式）
	 * @param cd
	 * @return
	 */
	@RequiresPermissions("check:view")
	@RequestMapping("/check2")
	public @ResponseBody Map<String, Object> check2(CheckDetail cd) {
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			
			data=applyService.check2(cd);

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("checkController.check", e);
		}
		return data;
	}
	
	@RequiresPermissions("check:view")
	@RequestMapping("/batchCheck")
	public @ResponseBody Map<String, Object> batchCheck(String data,String opinion,boolean result){
		
		Map<String, Object> map = new HashMap<String, Object>(4);
		try {
			List<Apply> list= JSONArray.parseArray(data, Apply.class);
			for(Apply a:list){
				CheckDetail cd=new CheckDetail();
				cd.setTaskid(a.getTaskId());
				cd.setApplyid(a.getId());
				cd.setResult(result);
				cd.setOpinion(opinion);
				map=applyService.check2(cd);
			}
		} catch (Exception e) {
			map.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("checkController.check", e);
		}
		return map;
	}
	
	
	@RequestMapping(value = "/applyCheckDetail", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> applyCheckDetail(String applyId) {

		Map<String, Object> data = new HashMap<String, Object>(4);
		
		data.put("rows", applyService.selectDetailsById(applyId));

		return data;
	}
	
	/*
     * 查看流程图
     * */
	@RequestMapping("/lookprocessdefine")
	public @ResponseBody Map<String,Object> loadResourceByDeployment(String processId) throws Exception{
	
		Map<String, Object> data = new HashMap<String, Object>(8);
		
		Procinst proc= flowService.findProcinstById(processId);
		
		data.put("flowJson", proc.getProcDef());
		
		if(proc.getEndTime()==null){
			
			TaskExample te=new TaskExample();
			
			te.createCriteria().andProcInstIdEqualTo(processId).andEndTimeIsNull().andIsdeletedEqualTo(false);
			
			List<Task> list= flowService.selectTaskByExample(te);
			
			if(!list.isEmpty()){
				data.put("currentNode",list.get(0).getTaskDefKey());
			}
		}
		return data;
	}
	
	@RequestMapping("/showProcessimg.html")
	public ModelAndView showProcessimg(String processId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/processImg");
		mv.addObject("processId", processId);
		return mv;
	}
}
