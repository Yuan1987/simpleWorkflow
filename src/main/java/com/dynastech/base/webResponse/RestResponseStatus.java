package com.dynastech.base.webResponse;

/***
 * <p>
 * rest请求响应状态码
 * </p>
 * 
 * @author Administrator
 *
 */
public interface RestResponseStatus {

	/***
	 * 请求处理成功
	 */
	public static final int SUCCESS = 200;

	/***
	 * 请求出现一般性的错误 
	 */
	public static final int NORMAL_ERROR = 400;

	/***
	 * 请求出现客户端输入错误 验证未通过
	 */
	public static final int INPUT_ERROR = 402;
	
	/***
	 * 请求出现客户端输入token 验证错识
	 */
	public static final int INPUT_TOKEN_ERROR = 45009;

	/***
	 * 权限不足，拒绝访问
	 */
	public static final int DENY = 401;

	/***
	 * 试图访问必须要登陆的资源
	 */
	public static final int AONYMOUS_DENY = 403;
	
	/***
	 * 内部错误
	 */
	public static final int ERROR_500 = 500;
}
