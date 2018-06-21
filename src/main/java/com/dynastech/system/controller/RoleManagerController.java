
package com.dynastech.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.common.service.ICommonService;
import com.dynastech.base.global.Constants;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.system.entity.Role;
import com.dynastech.system.entity.RoleExample;
import com.dynastech.system.service.IRoleManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/system/roleManager")
public class RoleManagerController {

	private static Logger logger = Logger.getLogger(RoleManagerController.class);

	@Autowired
	private IRoleManagerService roleManagerService;
	
	
	@Autowired
	ICommonService commonService;
	

	@RequiresPermissions("role:index")
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("roleType",roleManagerService.findRoleType());
		mv.setViewName("/system/role_index");
		return mv;
	}
	
	@RequiresPermissions("role:view")
	@RequestMapping(value = "/roleList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> roleList(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "nodeid", required = false,defaultValue="F24FB3B0-CE54-4E87-B524-CEF637F7C2A9") String nodeid) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("r.datetimemodified desc");
		Map<String, Object> data = new HashMap<String, Object>();
		
		RoleExample re=new RoleExample();
		re.createCriteria().andIsdeletedEqualTo(false);
		
		List<Role> roles = roleManagerService.selectRoleByExample(re);
		// 取分页信息
        PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
		data.put("rows", roles);
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	@RequiresPermissions("role:add")
	@RequestMapping("/add")
	public @ResponseBody
	Map<String, Object> add(Role role,@RequestParam(defaultValue="")String resourceIds){
		
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			role.setId(UUID.randomUUID().toString());
			role.setOrdinal(0);
			role.setOrganizationid(Constants.ORG_ROOT_ID);
			
			String name=role.getDisplayname();
			RoleExample re=new RoleExample();
			re.createCriteria().andDisplaynameEqualTo(name).andIsdeletedEqualTo(false);
			
			List <Role> list=roleManagerService.selectRoleByExample(re);
			
			if(list.size()>0){
				data.put("message", I18nUtil.getTextValue("dynastech.common.operation.repeat"));
				return data;
			}
			
			int a = roleManagerService.add(role,resourceIds.split(","));
			
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("RoleManagerController.add",e);
		}
		return data;
	}
	
	@RequiresPermissions("role:view")
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getById(String id){
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("role",roleManagerService.findById(id));
		
		
		List <String> roleIds= new ArrayList<String>();
		roleIds.add(id);
		
		data.put("resourceIds", roleManagerService.selectRoleReourceByRoleId(roleIds));
		
		return data;
	}
	
	@RequiresPermissions("role:update")
	@RequestMapping("/update")
	public @ResponseBody
	Map<String, Object> update(Role role,@RequestParam(defaultValue=",")String resourceIds){
		
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			String name=role.getDisplayname();
			RoleExample re=new RoleExample();
			re.createCriteria().andDisplaynameEqualTo(name).andIsdeletedEqualTo(false).andIdNotEqualTo(role.getId());
			
			List <Role> list=roleManagerService.selectRoleByExample(re);
			
			if(list.size()>0){
				data.put("message", I18nUtil.getTextValue("dynastech.common.operation.repeat"));
				return data;
			}
		
			if("".equals(resourceIds)){
				resourceIds=",";
			}
			role.setOrganizationid(Constants.ORG_ROOT_ID);
			int a = roleManagerService.update(role,resourceIds.split(","));
			
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("RoleManagerController.update",e);
		}
		return data;
	}
	
	@RequiresPermissions("role:delete")
	@RequestMapping("/delete")
	public @ResponseBody
	Map<String, Object> delete(String id){
		
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			int a = roleManagerService.delete(id);
			
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("RoleManagerController.delete",e);
		}
		return data;
	}
	
	//@RequiresPermissions("role:view")
	@RequestMapping(value = "/roleListByOrgId", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,String>> roleListByOrgId(
			@RequestParam(value = "orgId", required = true) String orgId,
			String searchText) {
		
		orgId=Constants.ORG_ROOT_ID;
		
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		List<Map<String,String>> roles = roleManagerService.selectRoleByOrgIdForSelect2(orgId.split(","),searchText);
		return roles;
	}
}
