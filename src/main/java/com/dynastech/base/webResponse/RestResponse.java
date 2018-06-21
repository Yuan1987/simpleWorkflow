package com.dynastech.base.webResponse;

import java.io.Serializable;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * <p>
 * rest请求响应对象
 * </p>
 * 
 * @author yuan
 *
 * @param <T>
 */
public class RestResponse<T> implements Serializable {
	private static Logger logger = Logger.getLogger(RestResponse.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 2256166525414042854L;

	/**
	 * <p>
	 * 状态码
	 * </p>
	 */
	private int status;

	/**
	 * <p>
	 * 响应消息
	 * </p>
	 */
	private String message;

	/**
	 * <p>
	 * 响应内容
	 * </p>
	 */
	
	private transient T content;
	
	/**
	 * 签文
	 */
	private String sign;
	
	/**
	 * 加密后的返回内容
	 */
	private String encryptContent;

	/**
	 * <p>
	 * 构造无内容的rest响应对象
	 * </p>
	 * 
	 * @param status
	 *            状态码
	 * @param message
	 *            响应消息
	 */
	public RestResponse(int status, String message) {

		this(status, message, null);
	}

	/**
	 * <p>
	 * 构造有内容的rest响应对象
	 * </p>
	 * 
	 * @param status
	 *            状态码
	 * @param message
	 *            响应消息
	 * @param content
	 *            响应内容
	 */
	public RestResponse(int status, String message, T content) {
		super();
		this.status = status;
		this.message = message;
		this.content = content;
		if (logger.isDebugEnabled()) {
			if (content != null) {
				try {
					JSONObject jsonobject = JSONObject.fromObject(content);
					logger.debug("the RestResponse content is :" + jsonobject.toString());
				} catch (Exception e) {
					logger.warn("warn ::: the RestResponse content is :" + content);
				}
			} else {
				logger.debug("the RestResponse content is :" + null);
			}
		}
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getSign() {
		return sign;
	}
	
	public String getEncryptContent() {
		return encryptContent;
	}
	
	public void setEncryptContent(String encryptContent) {
		this.encryptContent = encryptContent;
	}

}
