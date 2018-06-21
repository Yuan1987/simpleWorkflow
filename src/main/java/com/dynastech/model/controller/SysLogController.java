
package com.dynastech.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.model.entity.SysLog;
import com.dynastech.model.entity.SysLogExample;
import com.dynastech.model.entity.SysLogExample.Criteria;
import com.dynastech.model.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/system/syslog")
public class SysLogController {

	private static Logger logger = Logger.getLogger(SysLogController.class);

	@Autowired
	private ISysLogService sysLogService;
	
	@RequiresPermissions("syslog:view")
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/syslog_index");
		return mv;
	}
	
	@RequiresPermissions("syslog:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("log.createdTime desc");
		Map<String, Object> data = new HashMap<String, Object>();
		
		SysLogExample de=new SysLogExample();
		Criteria c= de.createCriteria();
		
		if(!StringUtils.isBlank(searchText)){
			c.andUsernameLike("%"+searchText+"%");
		}
		
		List<SysLog> logs = sysLogService.selectByExample(de);
		// 取分页信息
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(logs);
		data.put("rows", logs);
		data.put("total", pageInfo.getTotal());

		return data;
	}
}
