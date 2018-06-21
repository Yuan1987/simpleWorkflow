
package com.dynastech.system.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.common.service.ICommonService;
import com.dynastech.base.common.service.IRedisService;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.system.entity.Resource;
import com.dynastech.system.entity.ResourceExample;
import com.dynastech.system.entity.ResourceExample.Criteria;
import com.dynastech.system.entity.User;
import com.dynastech.system.service.IResourceManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/system/resourceManager")
public class ResourceManagerController {

	private static Logger logger = Logger.getLogger(ResourceManagerController.class);

	@Autowired
	private IResourceManagerService resourceManagerService;
	
	@Autowired
	ICommonService commonService;
	
	
	@RequiresPermissions("resource:index")
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/resource_index");
		return mv;
	}
	
	@RequiresPermissions("resource:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("re.id");
		Map<String, Object> data = new HashMap<String, Object>();
		
		ResourceExample re=new ResourceExample();
		re.createCriteria().andIsdeletedEqualTo(false);
		
		List<Resource> resource = resourceManagerService.selectByExample(re);
		// 取分页信息
        PageInfo<Resource> pageInfo = new PageInfo<Resource>(resource);
		data.put("rows", resource);
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	@RequestMapping(value = "/menus", method = RequestMethod.POST)
	public @ResponseBody List<Resource> menuList(
			@RequestParam(value = "searchText", required = false) String searchText) {
		
		User user=commonService.getCurrentUser();
		
		//@SuppressWarnings("unchecked")
		List<Resource> //resource=(List<Resource>)redisService.getValue("menus:"+user.getId());
		
		//if(resource==null){
			resource = resourceManagerService.findMenus(user.getId());
		//}
		return resource;
	}
	
	@RequestMapping(value = "/listForTree", method = RequestMethod.POST)
	public @ResponseBody List<Resource> listForTree(
			@RequestParam(value = "isSelect", required = false,defaultValue="true") boolean isSelect) {

		ResourceExample re=new ResourceExample();
		Criteria c= re.createCriteria().andIsdeletedEqualTo(false);

		if(isSelect){
			c.andParentIdIsNotNull();
		}
		
		re.setOrderByClause(" orderNo ");
		
		List<Resource> resource = resourceManagerService.selectByExample(re);
		
		return resource;
	}
	
	@RequiresPermissions("resource:add")
	@RequestMapping("/add")
	public @ResponseBody
	Map<String, Object> add(Resource resource) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			resource.setId(UUID.randomUUID().toString().toUpperCase());
			
			int a = resourceManagerService.add(resource);
			
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("ResourceManagerController.add",e);
		}
		return data;
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	@ResponseBody
	public Resource getById(String id){
		return resourceManagerService.findById(id);
	}
	
	@RequiresPermissions("resource:update")
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(Resource re) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = resourceManagerService.update(re);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.update", e);
		}
		return data;
	}
	
	@RequiresPermissions("resource:delete")
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(String id,String pobj) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = resourceManagerService.delete(id);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}

}
