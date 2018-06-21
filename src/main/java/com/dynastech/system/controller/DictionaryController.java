
package com.dynastech.system.controller;

import java.io.InputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.log.LogType;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.base.util.ImportExcelUtil;
import com.dynastech.base.util.LogUtil;
import com.dynastech.base.util.PinYinUtil;
import com.dynastech.common.entity.Dictionary;
import com.dynastech.common.entity.DictionaryExample;
import com.dynastech.common.entity.DictionaryExample.Criteria;
import com.dynastech.common.service.IDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/system/dictionary")
public class DictionaryController {

	private static Logger logger = Logger.getLogger(DictionaryController.class);

	
	@Autowired
	private IDictionaryService dicService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@RequiresPermissions("dic:view")
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/dic_index");
		return mv;
	}


	@RequiresPermissions("dic:view")
	@RequestMapping(value = "/getDicKind", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> getDicKind() {


		return dicService.getDicKind();

	}

	@RequiresPermissions("dic:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "kind", required = false) String kind) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("dic.kind,dic.code");
		Map<String, Object> data = new HashMap<String, Object>();
		
		DictionaryExample de=new DictionaryExample();
		Criteria c= de.createCriteria().andIsdeletedEqualTo(false);
		
		if(!StringUtils.isBlank(searchText)){
			c.andDetailLike("%"+searchText+"%");
		}
		if(!StringUtils.isBlank(kind)){
			c.andKindEqualTo(kind);
		}

		List<Dictionary> dics = dicService.selectByExample(de);
		// 取分页信息
        PageInfo<Dictionary> pageInfo = new PageInfo<Dictionary>(dics);
		data.put("rows", dics);
		data.put("total", pageInfo.getTotal());

		return data;
	}
	
	@RequiresPermissions("dic:update")
	@RequestMapping("/edit.html")
	public ModelAndView edit(String id) {
		ModelAndView mv = new ModelAndView();
		DictionaryExample de=new DictionaryExample();
		de.createCriteria().andIsdeletedEqualTo(false);
		de.setOrderByClause("dic.kind,dic.code");
		List<Dictionary> dics = dicService.selectByExample(de);
		mv.addObject("dics",dics);
		
		mv.addObject("dic", dicService.findById(id));
		
		mv.setViewName("/system/dic_edit");
		return mv;
	}
	
	@RequiresPermissions("dic:add")
	@RequestMapping("/add.html")
	public ModelAndView add(String orgId) {
		ModelAndView mv = new ModelAndView();
		DictionaryExample de=new DictionaryExample();
		de.createCriteria().andIsdeletedEqualTo(false);
		
		de.setOrderByClause("dic.kind,dic.code");
		
		List<Dictionary> dics = dicService.selectByExample(de);
		
		mv.addObject("dics",dics);
		mv.setViewName("/system/dic_add");
		return mv;
	}
	
	@RequiresPermissions("dic:add")
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(Dictionary dic) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			dic.setId(UUID.randomUUID().toString());
			int a = dicService.add(dic);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
			LogUtil.addLog(logger, LogType.DIC_ADD.getValue(), dic.getId(), commonService.getCurrentUser());
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}
	
	@RequiresPermissions("dic:update")
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(Dictionary dic) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = dicService.update(dic);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("dictionaryController.update", e);
		}
		return data;
	}
	
	@RequiresPermissions("dic:delete")
	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDelete(String ids) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String[] ida = ids.split(",");

			int a = dicService.batchDelete(ida);

			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			logger.error("dictionaryController.batchDelete", e);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}

	@RequiresPermissions("dic:add")
	@RequestMapping("/import.html")
	public ModelAndView dicImport(String orgId) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("url", "/system/dictionary/import");
		mv.addObject("tableId", "dicTable");
		mv.addObject("templateName", "dicTemplate.xlsx");
		mv.setViewName("/common/import");
		return mv;
	}
	
	@RequestMapping("/import")
	public @ResponseBody Map<String, Object> upload(@RequestParam(value = "file",required = true) MultipartFile file ) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a =0;
			   InputStream in = null;  
		        List<List<String>> listob = null;  
		        in = file.getInputStream();  
		        listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());  
		        for (int i = 0; i < listob.size(); i++) {
		        	
		        	try{
		        		List<String> lo = listob.get(i);  
			            Dictionary dic=new Dictionary();//暂时单条处理  待完善
			            dic.setId(UUID.randomUUID().toString());
			            dic.setKindname(lo.get(0));
			            dic.setKind(lo.get(1));
			            dic.setCode(lo.get(2));
			            dic.setDetail(lo.get(3));
			            dic.setFirstspell(PinYinUtil.getPinYinHeadChar(lo.get(3)));
			            dicService.add(dic);
			            a++;
		        	}catch(Exception e){
		        		logger.error("dictionaryController.import", e);
		        	}
		        }
			
			data.put("count",a);

		} catch (Exception e) {
			data.put("count",0);
			logger.error("dictionaryController.import", e);
		}
		return data;
	}
	
}
