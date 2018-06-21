/**
 * 
 */
package com.dynastech.base.common.service;

import org.apache.shiro.session.InvalidSessionException;

import com.dynastech.system.entity.User;


/**
 * 公共服务接口
 * 
 * @author yuan
 *
 */
public interface ICommonService {

	/**
	 * 获取当前用户名
	 * 
	 * @return
	 */
	public String getCurrentUserName();

	/**
	 * 判断是否当前用户名
	 * 
	 * @return
	 */
	public boolean isCurrentUser(String userId);

	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public User getCurrentUser();
	
	/**
	 * 设置数据到session
	 * 
	 * @return
	 */
	 void setSessionAttribute(String key, Object value);
	 
	 /**
	  * 从sesssion获取数据
	  * @param key
	  * @return
	  * @throws InvalidSessionException
	  */
	 Object getAttributeFromSession(Object key) throws InvalidSessionException;

	 /**
	  * 删除sesssion数据并获取
	  * @param key
	  * @return
	  * @throws InvalidSessionException
	  */
	 Object removeAttributeFromSession(Object key) throws InvalidSessionException;
}
