
package com.dynastech.model.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.file.IFileSystemService;
import com.dynastech.base.global.Constants;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.common.entity.Dictionary;
import com.dynastech.common.entity.DictionaryExample;
import com.dynastech.common.service.IDictionaryService;
import com.dynastech.flow.entity.Activity;
import com.dynastech.flow.entity.Procinst;
import com.dynastech.flow.entity.QueryTask;
import com.dynastech.flow.entity.QueryTaskResult;
import com.dynastech.flow.entity.Task;
import com.dynastech.flow.service.IFlowService;
import com.dynastech.model.entity.Apply;
import com.dynastech.model.entity.ApplyWithEval;
import com.dynastech.model.entity.AttachmentFile;
import com.dynastech.model.entity.CheckDetail;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.entity.PersonAbility;
import com.dynastech.model.entity.PersonAbilityEvaluate;
import com.dynastech.model.service.IApplyService;
import com.dynastech.model.service.IMarkSettingService;
import com.dynastech.model.service.IPersonEvaluationFormService;
import com.dynastech.system.entity.Organization;
import com.dynastech.system.entity.Role;
import com.dynastech.system.entity.User;
import com.dynastech.system.entity.UserSerial;
import com.dynastech.system.entity.UserSerialExample;
import com.dynastech.system.service.IRoleManagerService;
import com.dynastech.system.service.ISerialService;
import com.dynastech.system.service.IUserManagerService;

/**
 * 待我审批 （测评表）
 * 
 * @author yuanhb
 *
 */
@Controller
@RequestMapping("/model/efcheck")
public class EFCheckController {

	private static Logger logger = Logger.getLogger(EFCheckController.class);

	@Autowired
	private IApplyService applyService;

	@Autowired
	private IPersonEvaluationFormService pefService;

	@Autowired
	private CommonServiceImpl commonService;

	@Autowired
	private IFlowService flowService;

	@Autowired
	private IRoleManagerService roleManagerService;

	@Autowired
	private IFileSystemService fileSystemService;

	@Autowired
	private IUserManagerService userManagerService;

	@Autowired
	private IDictionaryService dicService;

	@Autowired
	private IMarkSettingService msService;
	
	@Autowired
	private ISerialService serialService;
	
	@RequestMapping("/index")
	public ModelAndView index(String type) {
		User user=commonService.getCurrentUser();
		String currentUserId=user.getId();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/efcheck_index");
		UserSerialExample use=new UserSerialExample();
		use.createCriteria().andUidEqualTo(currentUserId).andTypeEqualTo(Constants.SERIAL_USER_TYPE_0);
		//当前xlxz 领导所在的序列
		List<UserSerial> list= serialService.selectByExample(use);
		
		List <Role> roles=user.getRoles();
		
		roles=roles.stream().filter(r->r.getOrganizationid().equals(Constants.HRDEPTID)).collect(Collectors.toList());
		boolean isHr=!roles.isEmpty();
		mv.addObject("isxlxzld",list.size()>0?true:false);
		mv.addObject("isHr",isHr);
		return mv;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "15", required = false) int size,
			@RequestParam(value = "status", defaultValue = "0") boolean status,
			@RequestParam(value="node",defaultValue="")String node) {

		Map<String, Object> data = new HashMap<String, Object>();

		User user = commonService.getCurrentUser();

		String userId = user.getId();

		List<Apply> list = new Vector<Apply>();

		List<String> roleIds = new ArrayList<String>();

		List<Role> roles = roleManagerService.selectRolesByUserId(userId);

		for (Role role : roles) {
			roleIds.add(role.getId());
		}

		QueryTask qt=new QueryTask(Constants.FLOW_CPB,page,size);
		qt.setStatus(status);
		qt.setNode(node);
		QueryTaskResult qtr = flowService.querytodoList(roleIds, userId,qt);
		long count = qtr.getCount();

		for (Task task : qtr.gettList()) {
			String processinsId = task.getProcInstId();
			Procinst pi = flowService.findProcinstById(processinsId);
			String businesskey = pi.getBusinessId();
			ApplyWithEval apply = applyService.selectApplyWithEvalByEFId(businesskey);
			apply.setTaskId(task.getId());
			apply.setTaskName(task.getName());
			apply.setTaskKey(task.getTaskDefKey());
			apply.setComplete(status);
			apply.setTaskEndTime(task.getEndTime());
			list.add(apply);
		}
		data.put("rows", list);
		data.put("total", count);

		return data;
	}

	@RequestMapping("/checkApplyInfo.html")
	public ModelAndView checkApplyInfo(String efid, String taskId, boolean complete, String taskKey) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/efcheckInfo");
		mv.addObject("detailList", applyService.selectDetailsById(efid));
		 
		List<CheckDetail> qList=applyService.
				selectDetailsById(efid).stream()
				.filter(cd->ModelEnum.FLOW_NODE4_CODE.getValue().equals(cd.getNodecode())).collect(Collectors.toList());
		
		mv.addObject("qList",qList);
		Apply apply = applyService.selectApplyWithEvalByEFId(efid);
		mv.addObject("apply", apply);
		mv.addObject("id", efid);
		mv.addObject("taskId", taskId);
		mv.addObject("complete", complete);
		mv.addObject("taskKey", taskKey);
		mv.addObject("fileBasePath", fileSystemService.getAbsoluteAccessUrl(""));
		mv.addObject("currentUserId", commonService.getCurrentUser().getId());
		// 是否要评分

		boolean needMark = false;
		int level = Integer.parseInt(apply.getGrade());
		int msLevel = msService.getMarkSetting().getLevel();
		if ("04".equals(apply.getType()) || level < msLevel) {
			needMark = true;
		}
		//待选的测评人(该序列小组的成员)
		List<UserSerial> users= serialService.selectUserBySerialId(apply.getSeq(), Constants.SERIAL_USER_TYPE_1);
		mv.addObject("users", users);
		mv.addObject("usersJson", JSON.toJSONString(users));
		mv.addObject("needMark", needMark);
		mv.addObject("jbList", dicService.findByKind(Constants.DIC_KIND_SBJB));
		mv.addObject("xlList", dicService.findByKind(Constants.DIC_KIND_SBXL));
		return mv;
	}

	@RequestMapping("/toCheck.html")
	public ModelAndView toCheck(String id, boolean pass, String taskId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/efcheck");
		mv.addObject("pass", pass);
		mv.addObject("taskId", taskId);
		mv.addObject("applyId", id);

		Task task = flowService.findTaskByTaskId(taskId);
		String taskKey = task.getTaskDefKey();
		if (ModelEnum.FLOW_NODE1_CODE.getValue().equals(taskKey)) {// 部门领导审批
			mv.addObject("isDEPT", true);
		}
		if (ModelEnum.FLOW_NODE4_CODE.getValue().equals(taskKey)) {// 测评人
			User user = commonService.getCurrentUser();
			List<PersonAbilityEvaluate> list = pefService.getPersonAbilityEvaluateSByPeIdandUserId(id, user.getId());

			StringBuffer sb = new StringBuffer();
			for (PersonAbilityEvaluate pae : list) {
				sb.append(pae.getAbilityname());
				sb.append(":");
				sb.append(pae.getNote());
				sb.append("\r\n");
			}
			mv.addObject("notes", sb.toString());
			mv.addObject("paeList", list);
		}
		mv.addObject("taskKey", taskKey);
		return mv;
	}
	
	@RequestMapping("/toBack.html")
	public ModelAndView toBack(String id,String taskId,String type) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/back");
		mv.addObject("taskId", taskId);
		mv.addObject("applyId", id);
		mv.addObject("type",type);
		return mv;
	}

	/**
	 * 加签 选人页面
	 * 
	 * @param id
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/toAddPreNode.html")
	public ModelAndView toAddPreNode(String id, String taskId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/addFlowNode");
		mv.addObject("taskId", taskId);
		mv.addObject("applyId", id);

		List<Organization> ogrs = commonService.getCurrentUser().getOrgs();

		Set<User> set = new HashSet<User>();

		for (Organization org : ogrs) {
			List<User> users = userManagerService.selectUserByOrgId(org.getId(), null);
			set.addAll(users);
		}
		mv.addObject("users", set);
		return mv;
	}

	@RequestMapping("/addNode")
	public @ResponseBody Map<String, Object> addNode(String taskId, String userIds) {
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			Task task = flowService.findTaskByTaskId(taskId);
			Activity act = new Activity();
			act.setKey(task.getTaskDefKey() + "-add");
			act.setMultiInstance(true);
			act.setName(task.getName() + "加签");
			flowService.addPreNode(taskId, act, Arrays.asList(userIds.split(",")));
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("EFCheckController.check", e);
		}
		return data;
	}

	@RequestMapping("/check2")
	public @ResponseBody Map<String, Object> check2(CheckDetail cd) {
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			data = pefService.check2(cd);
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("EFCheckController.check", e);
		}
		return data;
	}
	
	/**
	 * 指定评分人 指定+走流程
	 * 
	 * @param jsonData
	 * @return
	 */
	@RequestMapping("/chose")
	public @ResponseBody Map<String, Object> chose(String jsonData, String taskId, String efId) {
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			data = pefService.chose(efId, taskId, jsonData);
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("EFCheckController.check", e);
		}
		return data;
	}

	/**
	 * 单条能力项打分
	 * 
	 * @param cd
	 * @return
	 */
	@RequestMapping("/markScore")
	public @ResponseBody Map<String, Object> markScore(String paeId, Double scoring, String note) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = pefService.markscore(paeId, scoring, note);

			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success")
					: I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("EFCheckController.check", e);
		}
		return data;
	}

	/*@RequestMapping("/count")
	public @ResponseBody Map<String, Object> count(String jsonData) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			pefService.count(jsonData);
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("EFCheckController.check", e);
		}
		return data;
	}*/

	@RequestMapping(value = "/applyCheckDetail", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> applyCheckDetail(String applyId,@RequestParam(value = "isQeustionList", defaultValue = "false") boolean isQeustionList) {

		Map<String, Object> data = new HashMap<String, Object>();
		
		List<CheckDetail> list=applyService.selectDetailsById(applyId);
		
		if(isQeustionList){
			list=list.stream()
					.filter(cd->ModelEnum.FLOW_NODE4_CODE.getValue().equals(cd.getNodecode())).collect(Collectors.toList());
		}
		data.put("rows",list);
		return data;
	}

	/**
	 * 测评表附件打包下载
	 * 
	 * @param peid
	 *            测评表ID
	 * @param name
	 *            申请人姓名
	 * @param response
	 */
	@RequestMapping("/download")
	public void download(String peId, String name, HttpServletResponse response) {

		response.setContentType("APPLICATION/OCTET-STREAM");
		try {
			name = new String(name.getBytes("gb2312"), "ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment;filename=" + name + ".zip");

		try {
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

			List<PersonAbility> paList = pefService.findPersonAbilityByEvalId(peId);

			for (PersonAbility pa : paList) {
				List<AttachmentFile> afList = pa.getFiles();
				for (AttachmentFile af : afList) {

					String path = fileSystemService.getAbsoluteAccessPath(af.getFilephysicalpath());
					File file = new File(path);
					FileInputStream is = new FileInputStream(file);

					byte[] data = readInputStream(is);
					zos.putNextEntry(new ZipEntry(af.getFriendlyfilename()));
					zos.write(data);
					is.close();
				}
			}
			zos.flush();
			zos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	@RequestMapping("/toAbilitycheck.html")
	public ModelAndView toAbilitycheck(String paeId, String paId, String index, boolean needMark,boolean complete) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("pae", pefService.getPersonAbilityEvaluateById(paeId));
		mv.addObject("pa", pefService.findPersonAbilityWithOutFileByPaId(paId));
		mv.addObject("index", index);
		mv.addObject("needMark", needMark);
		mv.addObject("complete", complete);
		mv.setViewName("model/personAbilityCheck");
		return mv;
	}
	
	@RequestMapping("/getTypeaheadSource")
	public @ResponseBody List<String> typeahead(String key){
		
		DictionaryExample de=new DictionaryExample();
		de.createCriteria().andKindEqualTo(Constants.DIC_KIND_CYY).andIsdeletedEqualTo(false).andDetailLike("%"+key+"%");
		
		List<Dictionary> list=dicService.selectByExample(de);
		List<String> source=new ArrayList<String>();
		list.forEach(dic->source.add(dic.getDetail()));
		
		return source;
	}
	/**
	 * 批量审核页面
	 * 
	 * @param data
	 * @return
	 */
	@RequiresPermissions("check:view")
	@RequestMapping("/toBacthCheck.html")
	public ModelAndView toBacthCheck(String data) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/check_batch");
		mv.addObject("type",Constants.FLOW_CPB);
		mv.addObject("data", StringEscapeUtils.escapeJson(data));
		return mv;
	}

	/**
	 * 批量审核
	 * 
	 * @param data
	 * @param opinion
	 * @param result
	 * @return
	 */
	@RequiresPermissions("check:view")
	@RequestMapping("/batchCheck")
	public @ResponseBody Map<String, Object> batchCheck(String data, String opinion, boolean result) {

		Map<String, Object> map = new HashMap<String, Object>(4);
		try {
			List<Apply> list = JSONArray.parseArray(data, Apply.class);
			for (Apply a : list) {
				CheckDetail cd = new CheckDetail();
				cd.setTaskid(a.getTaskId());
				cd.setApplyid(a.getId());
				cd.setResult(result);
				cd.setOpinion(opinion);
				map = pefService.check2(cd);
			}
		} catch (Exception e) {
			map.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("checkController.check", e);
		}
		return map;
	}
	
	/**
	 * 批量指定测评人
	 * 
	 * @param data
	 * @return
	 */
	@RequiresPermissions("check:view")
	@RequestMapping("/toBacthChose.html")
	public ModelAndView toBacthChose(String data) {

		User currentUser=commonService.getCurrentUser();
		String currentUserId=currentUser.getId();
		
		UserSerialExample use=new UserSerialExample();
		use.createCriteria().andUidEqualTo(currentUserId).andTypeEqualTo(Constants.SERIAL_USER_TYPE_0);
		//当前xlxz 领导所在的序列
		List<UserSerial> list= serialService.selectByExample(use);
		
		List<UserSerial> cyList=new ArrayList<UserSerial>();
		
		for(UserSerial us: list){
			cyList.addAll(serialService.selectUserBySerialId(us.getSid(),Constants.SERIAL_USER_TYPE_1));
		}
		
		Set<UserSerial> usSet=new HashSet<UserSerial>(cyList);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/chose_batch");
		mv.addObject("users", usSet);
		mv.addObject("data", StringEscapeUtils.escapeJson(data));
		return mv;
	}
	
	/**
	 * 批量审核
	 * 
	 * @param data
	 * @param opinion
	 * @param result
	 * @return
	 */
	@RequiresPermissions("check:view")
	@RequestMapping("/batchChose")
	public @ResponseBody Map<String, Object> batchChose(String data,String users) {

		Map<String, Object> map = new HashMap<String, Object>(4);
		try {
			List<Apply> list = JSONArray.parseArray(data, Apply.class);
			for (Apply a : list) {
				map = pefService.batchChose(a.getId(), a.getTaskId(), users);
			}
		} catch (Exception e) {
			map.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("checkController.check", e);
		}
		return map;
	}
}
