package com.dynastech.model.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.file.IFileSystemService;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.common.service.IDictionaryService;
import com.dynastech.model.entity.AttachmentFileBean;
import com.dynastech.model.entity.PersonAbility;
import com.dynastech.model.entity.SysAbilityExample;
import com.dynastech.model.service.IMyFileService;
import com.dynastech.model.service.impl.AttachmentFileService;
import com.dynastech.model.service.impl.SysAbilityService;
import com.dynastech.system.controller.DictionaryController;
import com.dynastech.system.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/model/myability")
public class MyFileController {
	
	private static Logger logger = Logger.getLogger(DictionaryController.class);
	
	@Autowired
	private IMyFileService myFileService;
	
	@Autowired
	private SysAbilityService sysAbility;
	
	@Autowired
	private IDictionaryService dicService;
	
	@Autowired
	private AttachmentFileService afService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@Autowired
	private IFileSystemService fileSystemService;
	
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/myAbility");
		mv.addObject("fileBasePath", fileSystemService.getAbsoluteAccessUrl(""));
		return mv;
		
	}
	
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(
			@RequestParam(value = "page", defaultValue = "1", required = false)int page,
			@RequestParam(value = "size", defaultValue = "20", required = false)int size,
			@RequestParam(value = "searchText", required = false) String searchText){
		
		PageHelper.startPage(page, size);
		PageHelper.orderBy("af.CreateTime desc");
		Map<String, Object> data = new HashMap<String, Object>();
		
		User user=commonService.getCurrentUser();
		
		List<AttachmentFileBean> list = myFileService.getAttachmentFileBeanList(user.getId(), searchText);
		//取分页信息
		PageInfo<AttachmentFileBean> pageInfo = new PageInfo<AttachmentFileBean>(list);
		data.put("rows",list);
		data.put("total", pageInfo.getTotal());
		return data;
		
	}
	@RequestMapping("/edit.html")
	public ModelAndView update(String id){
		
		ModelAndView mv = new ModelAndView();
		SysAbilityExample sysAbilityExample = new SysAbilityExample();
		sysAbilityExample.createCriteria().andIsdeletedEqualTo(false);
		
		
		mv.addObject("sa",sysAbility.getSysAbilityList(sysAbilityExample));
		mv.addObject("af", afService.getAttachmentFile(id));
		mv.setViewName("/model/myAbility_edit");
		
        return mv;
	}
	
	@RequestMapping(value="/getPAByFileId",method = RequestMethod.POST)
	public @ResponseBody List<PersonAbility> getPAByFileId(String fileId){
		return myFileService.findPersonAbilityByFileId(fileId);
	}
	
	@RequestMapping("/add.html")
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("dics_abilitykind",dicService.findByKind("abilitykind"));
		SysAbilityExample sysAbilityExample = new SysAbilityExample();
		sysAbilityExample.createCriteria().andIsdeletedEqualTo(false);
		sysAbilityExample.setOrderByClause("serial,thelevel");
		mv.addObject("ability",sysAbility.getSysAbilityList(sysAbilityExample));
		mv.setViewName("/model/myAbility_add");
		return mv;
	}
	
	@RequestMapping("/add.do")
	public void add(@RequestParam("file")MultipartFile  file,@RequestParam(defaultValue="")String saId,@RequestParam(defaultValue="")String description,HttpServletResponse response){
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			
			User user = commonService.getCurrentUser();
			String userId = user.getId();
			myFileService.uploadFile(saId, userId, file, description);
			data.put("message",I18nUtil.getTextValue("dynastech.common.operation.success"));
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("PersonEvaluationFormController.add", e);
		}
		try {
			response.getWriter().println(JSON.toJSONString(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(String fileId,String paIds,String description){
		HashMap<String, Object> data = new HashMap<String, Object>();
		try {
			User user = commonService.getCurrentUser();
			String userId = user.getId();
			myFileService.update(fileId,paIds,userId,description);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("PersonEvaluationFormController.add", e);
		}
		return data;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(String fileId){
		HashMap<String, Object> data = new HashMap<String, Object>();
		try {
			int a=myFileService.delete(fileId);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("PersonEvaluationFormController.add", e);
		}
		return data;
	}
}
	

