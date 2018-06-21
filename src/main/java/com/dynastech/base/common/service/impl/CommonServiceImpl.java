package com.dynastech.base.common.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.dynastech.base.common.service.ICommonService;
import com.dynastech.base.global.Constants;
import com.dynastech.base.global.Global;
import com.dynastech.system.entity.User;

/**
 * @author yuan
 */

@Service
public class CommonServiceImpl implements ICommonService {
	
	private static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	@Override
	public String getCurrentUserName() {
		Subject subject = SecurityUtils.getSubject();
		return subject.getPrincipal() == null ? Global.USER_ANONYMITY.getValue()
				: String.valueOf(subject.getPrincipal());
	}

	@Override
	public boolean isCurrentUser(String userId) {
		if (StringUtils.isBlank(userId)) {
			return false;
		}
		User user = getCurrentUser();
		if (user == null) {
			return false;
		}
		return userId.equals(String.valueOf(user.getId()));
	}

	@Override
	public User getCurrentUser() {
		return (User)getAttributeFromSession(Constants.CURRENT_USER);
	}

	@Override
	public void setSessionAttribute(String key, Object value) {
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute(key, value);
	}

	@Override
	public Object getAttributeFromSession(Object key) throws InvalidSessionException {
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession().getAttribute(key);
	}

	@Override
	public Object removeAttributeFromSession(Object key) throws InvalidSessionException {
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession().removeAttribute(key);
	}
}
