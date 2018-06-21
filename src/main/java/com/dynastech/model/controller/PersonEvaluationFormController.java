package com.dynastech.model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.dynastech.base.global.Constants;
import com.dynastech.base.log.LogType;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.base.util.LogUtil;
import com.dynastech.flow.entity.Procinst;
import com.dynastech.flow.service.IFlowService;
import com.dynastech.model.entity.Apply;
import com.dynastech.model.entity.ApplyWithEval;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.entity.PersonAbility;
import com.dynastech.model.entity.PersonEvaluationform;
import com.dynastech.model.service.IApplyService;
import com.dynastech.model.service.IMarkSettingService;
import com.dynastech.model.service.IPersonEvaluationFormService;
import com.dynastech.system.controller.DictionaryController;
import com.dynastech.system.entity.Organization;
import com.dynastech.system.entity.User;
import com.dynastech.system.service.IUserManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/model/evaluation")
public class PersonEvaluationFormController {
	
	private static Logger logger = Logger.getLogger(DictionaryController.class);
	
	@Autowired
	private IPersonEvaluationFormService pefService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@Autowired
	private IApplyService applyService;
	
	@Autowired
	private IFileSystemService fileSystemService;
	
	@Autowired
	private IFlowService flowService;
	
	@Autowired
	private IMarkSettingService msService;
	
	@Autowired
	private IUserManagerService userManagerService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/person_evaluation");
		return mv;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("a.datetimecreated desc");
		Map<String, Object> data = new HashMap<String, Object>();
		
		User user=commonService.getCurrentUser();
		
		List<ApplyWithEval> datas = applyService.selectApplyWithEval(user.getId(),ModelEnum.STATUS_2.getValue());
		// 取分页信息
        PageInfo<ApplyWithEval> pageInfo = new PageInfo<ApplyWithEval>(datas);
		data.put("rows", datas);
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	@RequestMapping("/edit.html")
	public ModelAndView add(String applyId,String serial,String level,String evaluationFormId){
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("applyId", applyId);
		mv.addObject("serial", serial);
		mv.addObject("level", level);
		
		Apply apply=applyService.findById(applyId);
		//是否要评分
		boolean needMark=false;
		int thelevel=Integer.parseInt(level);
		int msLevel=msService.getMarkSetting().getLevel();
		if("04".equals(apply.getType())||thelevel<msLevel){
			needMark=true;
		}
		
		User user=commonService.getCurrentUser();
		if(StringUtils.isBlank(evaluationFormId)){
			evaluationFormId=pefService.initPersonEvaluationForm(user.getId(), applyId, serial, level);
		}
		mv.addObject("evaluationFormId", evaluationFormId);
		mv.addObject("ef", pefService.findById(evaluationFormId));
		mv.addObject("needMark", needMark);
		mv.setViewName("/model/person_evaluation_edit");
		
		return mv;
	}
	
	@RequestMapping(value = "/table", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> list(String evaluationFormId) {

        Map<String, Object> data = new HashMap<>();
        
    	List<PersonAbility> list=pefService.findPersonAbilityByEvalId(evaluationFormId);
    	
        data.put("rows", list);
        return data;
    }
	
	@RequestMapping("/editAbility")
	public @ResponseBody Map<String, Object> editAbility(String id,String description) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			PersonAbility pa=new PersonAbility();
			
			pa.setId(id);
			pa.setDescription(description);
			
			int a = pefService.updatePersonAbility(pa);
			
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("PersonEvaluationFormController.add", e);
		}
		return data;
	}
	
	@RequestMapping("/fileInput.html")
	public ModelAndView fileInput(String paId,String efstatus){
		ModelAndView mv = new ModelAndView();
		mv.addObject("paId", paId);
		mv.addObject("fileBasePath", fileSystemService.getAbsoluteAccessUrl(""));
		mv.addObject("pa",pefService.findPersonAbilityByPaId(paId));
		mv.addObject("efstatus",efstatus);
		mv.setViewName("/model/person_evaluation_ability_fileinput");
		return mv;
	}
	
	@RequestMapping("/fileJSON")
	public @ResponseBody PersonAbility fileJSON(String paId){
		return pefService.findPersonAbilityByPaId(paId);
	}
	
	@RequestMapping("/fileUpload.do")
	public void fileUpload(@RequestParam("file")MultipartFile [] file,String paId,@RequestParam(defaultValue="")String fileIds,@RequestParam(defaultValue="")String description,@RequestParam(defaultValue="")String newDescription,
			HttpServletResponse response){
		
		response.setContentType("text/html; charset=UTF-8");
        Map<String, Object> data = new HashMap<>();
        try {
        	
        	User user=commonService.getCurrentUser();
        	String userId=user.getId();
        	pefService.uploadFile(paId, userId, file,fileIds,description,newDescription);
        	
            data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
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
	
	@RequestMapping("/fileDelete")
	public @ResponseBody Map<String, Object> fileDelete(String fileId,String paId){
		
        Map<String, Object> data = new HashMap<>();
        try {
        	pefService.deleteFile(fileId,paId);
            data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
        } catch (Exception e) {
            data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
            logger.error("PersonEvaluationFormController.add", e);
        }
        return data;
    }
	
	/**
	 * 角色对应人员模式（）
	 * @param id
	 * @param leaderId
	 * @return
	 */
	@RequestMapping("/commit2")
	public @ResponseBody Map<String, Object> doCommit2(String id,String leaderId) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			User user=commonService.getCurrentUser();
			List<Organization> orgs=user.getOrgs();
			List<String> orgIds= new Vector<String>();
			for(Organization org:orgs){
				orgIds.add(org.getId());
			}
			//查询该部门领导角色的人员
			List<User> users=userManagerService.findLeadersByOrgId(user.getId(),ModelEnum.FLOW_NODE1_CODE.getValue(),"");
			if(users.size()<1){
				data.put("message","未找到该部门的领导");
				return data;
			}
			List<String> userList=new ArrayList<String>();
			
			for(User u:users){
				userList.add(u.getId());
			}
			//启动流程
			Procinst processins=flowService.startProcess(user.getId(),Constants.FLOW_CPB, id, userList);
			
			PersonEvaluationform  pef=new PersonEvaluationform();
			pef.setId(id);
			pef.setProcessid(processins.getId());
			pef.setSubmittime(new Date());
			pef.setStatus(ModelEnum.EVALSTATUS_1.getValue());
			
			int a=pefService.update(pef);
			
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
			
			LogUtil.addLog(logger, LogType.APPLY_COMMIT.getValue(), id, user);
			
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("PersonEvaluationFormController.commit", e);
		}
		return data;
	}
	
	@RequestMapping("/checkDetail.html")
	public ModelAndView checkDetail(String id,String processId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id",id);
		mv.addObject("processId",processId);
		mv.setViewName("/model/ef_checkDetail");
		return mv;
	}
}
