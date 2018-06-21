package com.dynastech.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 
 * @author yuan
 *
 */
public class CommonFilter implements Filter {
	private static Logger logger = Logger.getLogger(CommonFilter.class);
	private String staticVersion;
	public void setStaticVersion(String staticVersion) {
		this.staticVersion = staticVersion;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {// 已认证
		}
		request.setAttribute("staticVersion", staticVersion);
		filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
