
package com.dynastech.system.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.util.I18nUtil;
import com.dynastech.system.entity.Serial;
import com.dynastech.system.entity.SerialExample;
import com.dynastech.system.entity.UserSerial;
import com.dynastech.system.service.ISerialService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/system/serial")
public class SerialController {
	
	private static Logger logger = Logger.getLogger(SerialController.class);
	
	@Autowired
	private ISerialService serialService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/serial_index");
		return mv;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody List<Serial> list() {
		
		SerialExample se=new SerialExample();
		se.createCriteria().andIsdeletedEqualTo(false);
		List<Serial> list = serialService.selectByExample(se);

		return list;
	}
	
	@RequestMapping(value = "/userList")
	public @ResponseBody Map<String,Object> userList(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "15", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText,
			String sid) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("rus.sid,rus.type");
		Map<String, Object> data = new HashMap<String, Object>();
		List<UserSerial> list = serialService.selectUserBySerialId(sid,null);
        PageInfo<UserSerial> pageInfo = new PageInfo<UserSerial>(list);
		data.put("rows", list);
		data.put("total", pageInfo.getTotal());

		return data;
	}
	
	@RequestMapping("/edit.html")
	public ModelAndView edit(String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("serial", serialService.findById(id));
		mv.setViewName("/system/serial_edit");
		return mv;
	}
	
	@RequestMapping("/gl.html")
	public ModelAndView add(String sid,String sname) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("sid", sid);
		mv.addObject("sname", sname);
		/*UserExample ue=new UserExample();
		ue.createCriteria().andIsdeletedEqualTo(false);
		List<User> list=userManagerService.selectByExample(ue);*/
		List<Map<String,String>> list = serialService.userListForSelect2(sid);
		mv.addObject("users",list);
		mv.setViewName("/system/serial_user_gl");
		return mv;
	}
	
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(Serial obj) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			obj.setId(UUID.randomUUID().toString());
			int a = serialService.add(obj);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(Serial obj) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = serialService.update(obj);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(String id) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			Serial obj=new Serial();
			obj.setId(id);
			obj.setIsdeleted(true);
			int a = serialService.update(obj);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}
	
	@RequestMapping("/gl")
	public @ResponseBody Map<String, Object> gl(String sid,String uids,String type) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			List<String> uidList=Arrays.asList(uids.split(","));
			int a = serialService.addGl(sid, uidList, type);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			logger.error(e);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}
	
	@RequestMapping("/serial_user_edit.html")
	public ModelAndView toSerialUserEdit(String id,String uname,String type) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id",id);
		mv.addObject("uname", uname);
		mv.addObject("type", type);
		mv.setViewName("/system/serial_user_edit");
		return mv;
	}
	
	@RequestMapping("/serialUserEdit")
	public @ResponseBody Map<String, Object> serialUserEdit(String id,String type) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = serialService.editGl(id, type);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			logger.error(e);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}
	
	@RequestMapping("/serialUserDelete")
	public @ResponseBody Map<String, Object> serialUserDelete(String id) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = serialService.deleteGl(id);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			logger.error(e);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
		}
		return data;
	}
	
}
