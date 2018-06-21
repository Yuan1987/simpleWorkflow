
package com.dynastech.system.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
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

import com.dynastech.base.global.Constants;
import com.dynastech.base.util.FileUploadUtil;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.base.util.ImportExcelUtil;
import com.dynastech.common.service.IDictionaryService;
import com.dynastech.system.entity.Organization;
import com.dynastech.system.entity.Role;
import com.dynastech.system.entity.User;
import com.dynastech.system.entity.UserExample;
import com.dynastech.system.service.IOrgManagerService;
import com.dynastech.system.service.IRoleManagerService;
import com.dynastech.system.service.IUserManagerService;
import com.dynastech.user.entity.UserCertificate;
import com.dynastech.user.entity.UserProfile;
import com.dynastech.user.entity.UserResume;
import com.dynastech.user.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping({"/system/userManager"})
public class UserManagerController {

	private static Logger logger = Logger.getLogger(UserManagerController.class);

	@Autowired
	private IUserManagerService userManagerService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleManagerService roleManagerService;
	
	@Autowired
	private IOrgManagerService orgManagerService;
	
	@Autowired
	private IDictionaryService dicService;
	
	@RequiresPermissions("userManager:view")
	@RequestMapping(value = "/userList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> userList(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "nodeid", required = false,defaultValue="F24FB3B0-CE54-4E87-B524-CEF637F7C2A9") String nodeid) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("u.DateTimeModified desc");
		Map<String, Object> data = new HashMap<String, Object>();
		List<User> orgs = userManagerService.selectUserByOrgId(nodeid,searchText);
		// 取分页信息
        PageInfo<User> pageInfo = new PageInfo<User>(orgs);
		data.put("rows", orgs);
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	@RequiresPermissions("userManager:add")
	@RequestMapping("/add.html")
	public ModelAndView add(String orgId,String orgName) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orgId",orgId);
		mv.addObject("orgName",orgName);
		mv.setViewName("/system/user_add");
		if(!StringUtils.isBlank(orgId)){
			mv.addObject("roles", roleManagerService.selectRoleByOrgIdForSelect2(orgId.split(","),null));
		}
		return mv;
	}
	
	@RequiresPermissions("userManager:update")
	@RequestMapping("/edit.html")
	public ModelAndView edit(String userId,String orgId,String orgName) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user",userManagerService.selectById(userId));
		mv.addObject("orgId",orgId);
		mv.addObject("orgName",orgName);
		List<Role> roleList=roleManagerService.selectRolesByUserId(userId,orgId);
		mv.addObject("roleList",roleList);
		mv.setViewName("/system/user_edit");
		return mv;
	}
	
	@RequiresPermissions("userManager:add")
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(User user,String orgId,String roleIds) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String accountname=user.getAccountname();
			UserExample ue=new UserExample();
			ue.createCriteria().andAccountnameEqualTo(accountname).andIsdeletedEqualTo(false);
			List <User> list=userManagerService.selectByExample(ue);
			
			if(list.size()>0){
				List<Organization> orgs= orgManagerService.selectOrgsUserId(list.get(0).getId());
				data.put("message", I18nUtil.getTextValue("dynastech.common.operation.repeat"));
				data.put("orgs", orgs);
				data.put("userId", list.get(0).getId());
				return data;
			}
			
			user.setId(UUID.randomUUID().toString());
			
			user.setPassword("123");
			
			int a = userManagerService.add(user,roleIds.split(","),orgId);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}
	
	@RequiresPermissions("userManager:update")
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> update(User user,String orgId,String roleIds) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = userManagerService.update(user,roleIds.split(","),orgId);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.update", e);
		}
		return data;
	}
	
	@RequiresPermissions("userManager:update")
	@RequestMapping("/userHandle")
	public @ResponseBody Map<String, Object> userHandle(String id,String orgId,String roleIds) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a = userManagerService.userHandle(id,roleIds.split(","),orgId);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.update", e);
		}
		return data;
	}
	
	@RequiresPermissions("userManager:delete")
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(String id,String orgId) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
			int a = userManagerService.delete(id,orgId);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}
	
	/**
	 * 个人资料
	 * @return
	 */
	@RequiresPermissions("userManager:view")
	@RequestMapping("/userProfile.html")
	public ModelAndView userProfile(String userId) {
		
		User user=userManagerService.selectById(userId);
		UserProfile userProfile=userService.selectUserProfileByUserId(userId);
		UserResume userResume=userService.selectUserResumeByUserId(userId);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/user_profile");
		mv.addObject("user", user);
		mv.addObject("userProfile",userProfile);
		mv.addObject("userResume",userResume);
		mv.addObject("imgBaseUrl", FileUploadUtil.getImageServerUrl(""));
		
		mv.addObject("xbList",dicService.findByKind(Constants.DIC_KIND_XB));
		mv.addObject("hyzkList",dicService.findByKind(Constants.DIC_KIND_HYZK));
		mv.addObject("mzList", dicService.findByKind(Constants.DIC_KIND_MZ));
		return mv;
	}
	
	//@RequiresPermissions({"userManager:add","user:add"}) 管理员与本人的操作暂用同一个--》暂时不控制操作权限 。
	@RequestMapping("/addUserProfile")
	public @ResponseBody Map<String, Object> addUserProfile(UserProfile up,String file,String userId) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a =0;
			Date now=new Date();
			
			try {
				if ((file != null) && (!"".equals(file))) {
					/*String path = "Portrait/" + UUID.randomUUID() + ".jpg";
					byte[] b = Base64.decodeBase64(file);
					fileService.save(b, path);*/
					
					byte[] b = Base64.decodeBase64(file);
					String path=FileUploadUtil.upload(b, UUID.randomUUID().toString(),"jpg", b.length+"");
					up.setPortrait(path);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!StringUtils.isBlank(up.getId())){
				up.setDatetimecreated(now);
				a = userService.updateUserProfile(up);
			}else{
				up.setDatetimemodified(now);
				up.setId(userId);
				a = userService.addUserProfile(up);
			}
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.addUserProfile", e);
		}
		return data;
	}
	
	/**
	 * 个人履历信息 增加/修改
	 * @param resume
	 * @param userId
	 * @return
	 */
	@RequestMapping("/userResume")
	public @ResponseBody Map<String, Object> userResume(UserResume resume,String userId) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a =0;
			Date now=new Date();
			
			if(!StringUtils.isBlank(resume.getId())){
				resume.setDatetimecreated(now);
				a = userService.updateUserResume(resume);
			}else{
				resume.setDatetimemodified(now);
				resume.setId(userId);
				a = userService.addUserResume(resume);
			}
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.userResume", e);
		}
		return data;
	}
	
	/**
	 * 获取人员的证书信息  暂无权限控制
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/certificateList")
	public @ResponseBody Map<String, Object> certificateList(String userId) {

		Map<String, Object> data = new HashMap<String, Object>();
		List<UserCertificate> ucs = userService.selectCertificateByUserId(userId);
		data.put("rows", ucs);
		return data;
	}
	
	/**
	 * 证书信息 新增/修改 页面
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/certificate.html")
	public ModelAndView certificate(String id,String userId) {

		ModelAndView mv = new ModelAndView();
		if(!StringUtils.isBlank(id)){
			UserCertificate uc = userService.selectCertificateById(id);
			mv.addObject("uc",uc);
		}
		mv.addObject("userid",userId);
		mv.setViewName("/user/user_certificate");
		return mv;
	}
	
	/**
	 * 个人证书信息 新增/修改 操作
	 * @param uc
	 * @return
	 */
	@RequestMapping("/certificate")
	public @ResponseBody Map<String, Object> addOrUpdate(UserCertificate uc) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a =0;
			Date now=new Date();
			
			if(!StringUtils.isBlank(uc.getId())){
				uc.setDatetimecreated(now);
				a = userService.updateCertificate(uc);
			}else{
				uc.setDatetimemodified(now);
				uc.setId(UUID.randomUUID().toString());
				a = userService.addCertificate(uc);
			}
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("UserManagerController.certificate", e);
		}
		return data;
	}
	
	/**
	 * 个人证书信息 新增/修改 操作
	 * @param uc
	 * @return
	 */
	@RequestMapping("/certificateDelete")
	public @ResponseBody Map<String, Object> certificateDelete(String ids) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			int a =userService.deleteCertificate(ids.split(","));
			
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success"):I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("UserManagerController.certificate", e);
		}
		return data;
	}
	
	/**
	 * 人员导入 页面
	 */
	@RequiresPermissions("userManager:add")
	@RequestMapping("/userImport.html")
	public ModelAndView userImport() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("url", "/system/userManager/import");
		mv.addObject("tableId", "userTable");
		mv.addObject("templateName", "userTemplate.xlsx");
		mv.setViewName("/common/import");
		return mv;
	}
	
	/**
	 * 人员信息导入   具体信息内容未实现
	 * @param file
	 * @return
	 */
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
			            User user = new User();  
			            user.setId(UUID.randomUUID().toString());
			            user.setAccountname(lo.get(0));
			            String name= String.valueOf(lo.get(1));
			            user.setDisplayname(name);
			            //插入。。。。。。。。。
			            a++;
		        		
		        	}catch(Exception e){
		        		logger.error("userManagerController.import", e);
		        	}
		        }
			
			data.put("count", a);
		} catch (Exception e) {
			data.put("count", 0);
			logger.error("userManagerController.import", e);
		}
		return data;
	}
}
