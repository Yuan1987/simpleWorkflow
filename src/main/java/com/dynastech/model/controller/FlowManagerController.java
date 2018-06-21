
package com.dynastech.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.util.I18nUtil;
import com.dynastech.flow.service.IFlowService;
import com.dynastech.model.entity.FlowBean;
import com.dynastech.model.entity.TaskBean;
import com.dynastech.model.service.IFlowManagerService;
import com.dynastech.system.entity.UserExample;
import com.dynastech.system.service.IUserManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 流程任务管理（系统管理员 干预流程）
 * @author yuan
 */
@Controller
@RequestMapping("/model/flowManager")
public class FlowManagerController {
	
	private static Logger logger = Logger.getLogger(FlowManagerController.class);
	
	@Autowired
	private IFlowManagerService flowManagerService;
	
	@Autowired
	private IUserManagerService userManagerService;
	
	@Autowired
	private IFlowService flowService;
	
	@RequiresPermissions("flowManager:view")
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		UserExample ue=new UserExample();
		ue.createCriteria().andIsdeletedEqualTo(false);
		mv.addObject("users", userManagerService.selectByExample(ue));
		mv.setViewName("/model/flowManager");
		return mv;
	}

	@RequiresPermissions("flowManager:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false,defaultValue="") String searchText) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("inst.START_TIME desc");
		Map<String, Object> data = new HashMap<String, Object>();
		
		List<FlowBean> list = flowManagerService.selectProcinst(true, searchText);
		// 取分页信息
        PageInfo<FlowBean> pageInfo = new PageInfo<FlowBean>(list);
		data.put("rows", list);
		data.put("total", pageInfo.getTotal());

		return data;
	}
	
	@RequiresPermissions("flowManager:view")
	@RequestMapping(value = "/taskList", method = RequestMethod.POST)
	public @ResponseBody List<TaskBean> taskList(String procinstId) {
		List<TaskBean> list = flowManagerService.selectTaskByProcinstId(procinstId);
		return list;
	}
	
	@RequiresPermissions("flowManager:view")
	@RequestMapping(value = "/taskTransfer", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> taskTransfer(String taskId,String oldUserId,String newUserId) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a= flowService.taskTransfer(taskId, oldUserId, newUserId);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("FlowManagerController.taskTransfer", e);
		}
		return data;
	}
	
	
}
