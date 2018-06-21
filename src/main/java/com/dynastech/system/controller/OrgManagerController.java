
package com.dynastech.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.common.service.ICommonService;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.system.entity.Organization;
import com.dynastech.system.service.IOrgManagerService;

@Controller
@RequestMapping("/system/orgManager")
public class OrgManagerController {

	private static Logger logger = Logger.getLogger(OrgManagerController.class);

	@Autowired
	private IOrgManagerService orgManagerService;
	
	@Autowired
	ICommonService commonService;
	

	@RequiresPermissions("org:index")
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/org_index");
		mv.addObject("orgs",orgManagerService.selectOrganizationForTree());
		return mv;
	}

	@RequestMapping(value = "/getOrgTree", method = RequestMethod.POST)
	public @ResponseBody Object getOrgTree() {
		
		List<Organization> orgs = new ArrayList<Organization>();
		orgs = orgManagerService.selectOrganizationForTree();
		return orgs;
	}
	
	@RequiresPermissions("org:add")
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(Organization org,String pobj) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			String id=UUID.randomUUID().toString();
			
			org.setId(id);
			
			Date now=new Date();
			org.setDatetimecreated(now);
			org.setDatetimemodified(now);
			org.setPath(org.getPath()+"/"+org.getName());
			org.setOrdinal(0);
			int a = orgManagerService.add(org,pobj);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}
	
	@RequiresPermissions("org:update")
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(Organization org) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			org.setPath(org.getPath()+"/"+org.getName());
			int a = orgManagerService.update(org);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.update", e);
		}
		return data;
	}
	
	@RequiresPermissions("org:delete")
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(String id,String pobj) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			int a = orgManagerService.delete(id,pobj);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}
}
