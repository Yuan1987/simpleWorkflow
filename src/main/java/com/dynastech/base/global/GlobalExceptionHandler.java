package com.dynastech.base.global;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author yuan
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	/**
	 * 没有权限 异常
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("code", HttpStatus.UNAUTHORIZED.value());
		mv.addObject("desc", Global.ERROR_401.getValue());
		mv.setViewName("unauthorized");
		return mv;
	}

	@ExceptionHandler({ NoHandlerFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView process404Exception() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("code", HttpStatus.NOT_FOUND.value());
		mv.addObject("desc", Global.ERROR_404.getValue());
		mv.setViewName("unauthorized");
		return mv;
	}
	
	@ExceptionHandler({ RuntimeException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView process500Exception(Exception exception) {
		logger.error(" 500Exception occured:::",exception);
		ModelAndView mv = new ModelAndView();
		mv.addObject("code", HttpStatus.SERVICE_UNAVAILABLE.value());
		mv.addObject("desc", Global.ERROR_503.getValue());
		mv.setViewName("unauthorized");
		return mv;
	}

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView processBindException() {
		try {
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			StringBuilder sb = new StringBuilder();
			for (StackTraceElement stackTraceElement : st) {
				sb.append(stackTraceElement.getClassName()).append(" :: ").append(stackTraceElement.getMethodName())
						.append(" :: ").append(stackTraceElement.getLineNumber()).append(" \n");
			}
			logger.error("BindException occured: " + sb.toString());
		} catch (Exception e) {

		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("code", HttpStatus.BAD_REQUEST.value());
		mv.addObject("desc", Global.ERROR_400.getValue());
		mv.setViewName("unauthorized");
		return mv;
	}

}
