
package com.dynastech.user.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.file.IFileSystemService;
import com.dynastech.base.global.Constants;
import com.dynastech.base.util.FileUploadUtil;
import com.dynastech.common.service.IDictionaryService;
import com.dynastech.system.entity.User;
import com.dynastech.user.entity.UserProfile;
import com.dynastech.user.entity.UserResume;
import com.dynastech.user.service.IUserService;

@Controller
@RequestMapping("/user/userProfile")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IFileSystemService fileService;
	
	@Autowired
	private IDictionaryService dicService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	
	
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping("/userProfile.html")
	public ModelAndView userProfile() {
		
		User user=commonService.getCurrentUser();
		UserProfile userProfile=userService.selectUserProfileByUserId(user.getId());
		UserResume userResume=userService.selectUserResumeByUserId(user.getId());
		
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
}
