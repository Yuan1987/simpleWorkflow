package com.dynastech.base.login;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.global.Constants;
import com.dynastech.base.global.Global;
import com.dynastech.system.entity.User;
import com.dynastech.system.service.IOrgManagerService;
import com.dynastech.system.service.IRoleManagerService;
import com.dynastech.system.service.IUserManagerService;

/**
 * 
 * @author yuan
 */
@Controller
public class LoginController {
	
	@Autowired
	private IUserManagerService userManagerService;
	
	@Autowired
	private IOrgManagerService orgManagerService;
	
	@Autowired
	private IRoleManagerService roleManagerService;

	private static Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = { "/login"})
	public ModelAndView login( String username,String password) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login");
		
		if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
			return mv;
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException ice) {
			// 捕获密码错误异常
			mv.addObject("error", "password error!");
		} catch (UnknownAccountException uae) {
			// 捕获未知用户名异常
			mv.addObject("error", "username error!");
		} catch (ExcessiveAttemptsException eae) {
			// 捕获错误登录过多的异常
			mv.addObject("error", "times error");
		}
		
		if (subject.isAuthenticated()) {
			mv.setViewName("redirect:/system/plan/view");
			User user = userManagerService.selectByUserName((String)subject.getPrincipal());
			
			try{
				user.setOrgs(orgManagerService.selectOrgsUserId(user.getId()));
			}catch(Exception e){
				logger.warn("not find orgs by this userId");
			}
			
			user.setRoles(roleManagerService.selectRolesByUserId(user.getId()));
			
			
			subject.getSession().setAttribute(Constants.CURRENT_USER, user);
			
			//redisService.setValue("menus:"+user.getId(), resourceManagerService.findMenus(user.getId()),30,TimeUnit.MINUTES);
			
		}
		return mv;
	}
	
	@RequestMapping(value = { "/home"})
	public String home() {
		return "index";
	}
	
	@RequestMapping(value = { "/"})
	public String index() {
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/unauthorized")
	public ModelAndView unauthorized() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("code", HttpStatus.UNAUTHORIZED.value());
		mv.addObject("desc", Global.ERROR_401.getValue());
		mv.setViewName("unauthorized");
		return mv;
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			logger.debug("user:" + SecurityUtils.getSubject().getPrincipal() + " logout!");
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
		return "login";
	}

}
