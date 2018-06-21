package com.dynastech.model.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
import com.dynastech.model.entity.Plan;
import com.dynastech.model.entity.PlanExample;
import com.dynastech.model.service.IPlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Jack
 * @date 2017年10月17日
 *
 */
@Controller
@RequestMapping("/system/plan")
public class PlanController {

	private static Logger logger = Logger.getLogger(PlanController.class);

	@Autowired
	private IPlanService planService;

	@Autowired
	private DictionaryService dicService;

	@Autowired
	private CommonServiceImpl commonService;

	/**
	 * 计划页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(Constants.DIC_KIND_SBXL);
		mv.setViewName("/model/sys_plan");
		return mv;
	}
	
	/**
	 * 计划页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/planView");
		return mv;
	}


	/**
	 *
	 * @param page
	 *            当前页
	 * @param size
	 *            页大小
	 * @param searchText
	 *            名称
	 * @param sYear
	 *            年度
	 * @return 分页结果集
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "sYear", required = false) int sYear) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("p.syear desc");
		Map<String, Object> data = new HashMap<>();
		PlanExample sae = new PlanExample();
		PlanExample.Criteria c = sae.createCriteria().andIsdeletedEqualTo(false);
		if (!StringUtils.isBlank(searchText)) {
			c.andNameLike("%" + searchText + "%");
		}
		if (sYear != 0) {
			c.andSyearEqualTo(sYear);
		}
		List<Plan> plans = planService.selectByExample(sae);
		// 取分页信息
		PageInfo<Plan> pageInfo = new PageInfo<>(plans);
		data.put("rows", plans);
		data.put("total", pageInfo.getTotal());

		return data;
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("xlList", dicService.findByKind(Constants.DIC_KIND_SBXL));
		mv.setViewName("/model/sys_plan_add");
		return mv;
	}

	/**
	 * 新增计划
	 * 
	 * @param sa
	 *            计划实体
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(Plan sa) {
		Map<String, Object> data = new HashMap<>(4);
		try {
			Date st = strToDate(sa.getStarttime());
			Calendar c = Calendar.getInstance();
			c.setTime(st);
			int year = c.get(Calendar.YEAR);

			Date et = strToDate(sa.getEndtime());
			Calendar c2 = Calendar.getInstance();
			c2.setTime(et);
			int eYear = c2.get(Calendar.YEAR);

			if (st.getTime() > et.getTime()) {
				throw new IllegalArgumentException("开始日期应小于结束日期！");
			}

			if (year != eYear) {
				throw new IllegalArgumentException("开始时间与结束时间年度不一致！");
			}
			sa.setId(UUID.randomUUID().toString());
			sa.setDatetimecreated(new Date());
			sa.setStatus("1");
			sa.setIsdeleted(false);
			sa.setSyear(year);
			int a = planService.insertPlan(sa);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success")
					: I18nUtil.getTextValue("dynastech.common.operation.fail"));
			LogUtil.addLog(logger, LogType.PLAN_ADD.getValue(), sa.getId(), commonService.getCurrentUser());
		} catch (ParseException ex) {
			data.put("message", "时间格式错误！");
			logger.error("PlanController.add", ex);
		} catch (IllegalArgumentException ex) {
			data.put("message", ex.getMessage());
			logger.error("PlanController.add", ex);
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("PlanController.add", e);
		}
		return data;
	}

	public Date strToDate(String str) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(str);
	}

	/**
	 * 修改页面
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView update(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/sys_plan_edit");
		mv.addObject("xlList", dicService.findByKind(Constants.DIC_KIND_SBXL));
		mv.addObject("plan", planService.selectByPrimaryKey(id));
		return mv;
	}

	/**
	 * 修改计划
	 * 
	 * @param sa
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(Plan sa) {
		Map<String, Object> data = new HashMap<>(4);
		try {
			Date st = strToDate(sa.getStarttime());
			Calendar c = Calendar.getInstance();
			c.setTime(st);
			int year = c.get(Calendar.YEAR);

			Date et = strToDate(sa.getEndtime());
			Calendar c2 = Calendar.getInstance();
			c2.setTime(et);
			int eYear = c2.get(Calendar.YEAR);
			if (st.getTime() > et.getTime()) {
				throw new IllegalArgumentException("开始日期应小于结束日期！");
			}
			if (year != eYear) {
				throw new IllegalArgumentException("开始时间与结束时间年度不一致");
			}

			sa.setSyear(year);
			sa.setDatetimemodified(new Date());
			int a = planService.updatePlan(sa);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success")
					: I18nUtil.getTextValue("dynastech.common.operation.fail"));
			LogUtil.addLog(logger, LogType.PLAN_UPDATE.getValue(), sa.getId(), commonService.getCurrentUser());
		} catch (ParseException ex) {
			data.put("message", "时间格式错误！");
			logger.error("PlanController.add", ex);
		} catch (IllegalArgumentException ex) {
			data.put("message", ex.getMessage());
			logger.error("PlanController.add", ex);
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("PlanController.update", e);
		}
		return data;
	}

	/**
	 * 批量逻辑删除计划
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDelete(String ids) {
		Map<String, Object> data = new HashMap<>(4);
		try {
			String[] ida = ids.split(",");
			int a = planService.batchDeletePlan(ida);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success")
					: I18nUtil.getTextValue("dynastech.common.operation.fail"));
			LogUtil.addLog(logger, LogType.PLAN_DELETE.getValue(), ids, commonService.getCurrentUser());
		} catch (Exception e) {
			logger.error("PlanController.batchDelete", e);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}

}
