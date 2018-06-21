package com.dynastech.model.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.global.Constants;
import com.dynastech.base.log.LogType;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.base.util.LogUtil;
import com.dynastech.common.service.impl.DictionaryService;
import com.dynastech.model.entity.SysAbility;
import com.dynastech.model.entity.SysAbilityExample;
import com.dynastech.model.entity.SysAbilityExample.Criteria;
import com.dynastech.model.service.ISysAbilityService;
import com.dynastech.system.controller.DictionaryController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/system/ability")
public class SysAbilityController {
	
	private static Logger logger = Logger.getLogger(DictionaryController.class);
	
	@Autowired
	private ISysAbilityService abilityService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@Autowired
	private DictionaryService dicService;
	
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		List<com.dynastech.common.entity.Dictionary> abilitykinddics = dicService.findByKind("abilitykind");
		mv.addObject("dics_abilitykind",abilitykinddics);
		mv.setViewName("/model/sys_ability");
		return mv;
	}
	
//	@RequiresPermissions("ability:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText,
			 @RequestParam(value = "abilitykind", required = false) String abilitykind) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("sa.id");
		Map<String, Object> data = new HashMap<String, Object>();
		
		
		SysAbilityExample sae = new SysAbilityExample();
		Criteria c = sae.createCriteria().andIsdeletedEqualTo(false);
		if(!StringUtils.isBlank(searchText)){
			c.andNameLike("%"+searchText+"%");
		}
		if(!StringUtils.isBlank(abilitykind)){
			c.andTypeidEqualTo(abilitykind);
		}
		List<SysAbility> ability = abilityService.getSysAbilityList(sae);
		
		// 取分页信息
        PageInfo<SysAbility> pageInfo = new PageInfo<SysAbility>(ability);
		data.put("rows", ability);
		data.put("total", pageInfo.getTotal());

		return data;
	}
	
	@RequestMapping("/edit.html")
	public ModelAndView update(String id){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/sys_ability_edit");
		mv.addObject("sqlxList",dicService.findByKind(Constants.DIC_KIND_SQLX));
		mv.addObject("jbList",dicService.findByKind(Constants.DIC_KIND_SBJB));
		mv.addObject("xlList",dicService.findByKind(Constants.DIC_KIND_SBXL));
		mv.addObject("dics_formula",dicService.findByKind("formula"));
        mv.addObject("dics_abilitykind",dicService.findByKind("abilitykind"));
		mv.addObject("as", abilityService.findAbilityById(id));
		return mv;
	}
	
	@RequestMapping("/add.html")
	public ModelAndView add(String id){
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("sqlxList",dicService.findByKind(Constants.DIC_KIND_SQLX));
		mv.addObject("jbList",dicService.findByKind(Constants.DIC_KIND_SBJB));
		mv.addObject("xlList",dicService.findByKind(Constants.DIC_KIND_SBXL));
		mv.addObject("dics_formula",dicService.findByKind("formula"));
        mv.addObject("dics_abilitykind",dicService.findByKind("abilitykind"));
		
		mv.setViewName("/model/sys_ability_add");
		
		return mv;
	}
	
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(SysAbility sa) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			if (StringUtils.isBlank(sa.getId())) {
			
				sa.setId(UUID.randomUUID().toString());
				sa.setDatetimecreated(new Date());
				sa.setIsdeleted(false);
			}
			int a = abilityService.addSysAbility(sa);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
			LogUtil.addLog(logger, LogType.DIC_ADD.getValue(), sa.getId(), commonService.getCurrentUser());
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(SysAbility sa) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			sa.setDatetimemodified(new Date());
			int a = abilityService.update(sa);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("dictionaryController.update", e);
		}
		return data;
	}
	
	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDelete(String ids) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String[] ida = ids.split(",");
			
			int a = abilityService.batchDelete(ida);

			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			logger.error("dictionaryController.batchDelete", e);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}

}
