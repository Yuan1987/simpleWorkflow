package com.dynastech.model.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.dynastech.base.global.Constants;
import com.dynastech.base.log.LogType;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.base.util.ImportExcelUtil;
import com.dynastech.base.util.LogUtil;
import com.dynastech.common.service.IDictionaryService;
import com.dynastech.model.entity.Exam;
import com.dynastech.model.entity.ExamExample;
import com.dynastech.model.service.IExamService;
import com.dynastech.system.controller.DictionaryController;
import com.dynastech.system.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/system/exam")
public class ExamController {
	
	private static Logger logger = Logger.getLogger(DictionaryController.class);
	
	@Autowired
	private IExamService examService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@Autowired
	private IDictionaryService dicService;
	
	
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/exam/exam_index");
		return mv;
	}
	
	@RequiresPermissions("exam:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("e.id");
		Map<String, Object> data = new HashMap<String, Object>();
		
		User user=commonService.getCurrentUser();
		
		ExamExample ex = new ExamExample();
		ex.createCriteria().andIsdeletedEqualTo(false).andUseridEqualTo(user.getId());
//		if(!StringUtils.isBlank(searchText)){
//			c.andNameEqualTo("%"+searchText+"%");
//		}
		List<Exam> exam = examService.getExamList(ex);
		
		// 取分页信息
        PageInfo<Exam> pageInfo = new PageInfo<Exam>(exam);
		data.put("rows", exam);
		data.put("total", pageInfo.getTotal());

		return data;
	}
	
	
	@RequestMapping("/edit.html")
	public ModelAndView update(String id){
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("jbList",dicService.findByKind(Constants.DIC_KIND_SBJB));
		mv.addObject("xlList",dicService.findByKind(Constants.DIC_KIND_SBXL));
		mv.addObject("ex", examService.findById(id));
		mv.setViewName("/exam/exam_edit");
		
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
        mv.addObject("userName", commonService.getCurrentUserName());
		
		mv.setViewName("/exam/exam_add");
		
		return mv;
	}
	
	
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(Exam exam) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			User user=commonService.getCurrentUser();
			
			Date now=new Date();
			exam.setId(UUID.randomUUID().toString());
			exam.setDatetimecreated(now);
			exam.setIsdeleted(false);
			exam.setUserid(user.getId());
			int a = examService.add(exam);
		
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
			LogUtil.addLog(logger, LogType.DIC_ADD.getValue(), exam.getId(), commonService.getCurrentUser());
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}
	
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(Exam exam) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			Date now=new Date();
			exam.setDatetimemodified(now);
			int a = examService.update(exam);
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

			int a = examService.batchDelete(ida);

			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			logger.error("dictionaryController.batchDelete", e);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}
	
	
	@RequestMapping("/import.html")
	public ModelAndView dicImport(String orgId) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("url", "/system/exam/import");
		mv.addObject("tableId", "exTable");
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
		        		Exam ex=new Exam();//暂时单条处理  待完善
		        		ex.setId(UUID.randomUUID().toString());
		        		//ex.setName(lo.get(0));
		        		ex.setKind(lo.get(1));
		        		ex.setDate(lo.get(2));
		        		ex.setScore(lo.get(3));
			            
			            examService.add(ex);
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
