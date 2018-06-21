package com.dynastech.base.filter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * 国际化拦截器
 * 
 * @author yuan
 * 
 */
public class LocaleFilter extends HandlerInterceptorAdapter {

	@Autowired
	private CookieLocaleResolver resolver;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		String language = request.getParameter("language");
//		if (!StringUtils.hasLength(language)) {
//		String language = Global.lANG_ZH_CN.getValue();
////		}
//		if (language.equals(Global.lANG_ZH_CN.getValue())) {
//			resolver.setLocale(request, response, Locale.CHINA);
//		} else if (language.equals(Global.lANG_EN_US.getValue())) {
//			resolver.setLocale(request, response, Locale.US);
//		} else {
//			resolver.setLocale(request, response, Locale.CHINA);
//		}
		resolver.setLocale(request, response, Locale.CHINA);
		return true;
	}
}
