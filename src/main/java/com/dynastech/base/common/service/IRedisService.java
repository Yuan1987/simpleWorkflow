package com.dynastech.base.common.service;

import java.util.concurrent.TimeUnit;

/**
 * 
 * redis 操作service
 * 
 * @author yuan
 *
 */
public interface IRedisService {
	
	/**
	 * 设置值
	 * @param key
	 * @param value
	 */
	public void setValue(String key,Object value);
	
	/**
	 * 设置值
	 * @param key
	 * @param value
	 * @param timeOut 超时 毫秒
	 */
	public void setValue(String key,Object value,long timeOut);
	
	/**
	 * 设置值
	 * @param key
	 * @param value
	 * @param timeOut
	 * @param timeUnit
	 */
	public void setValue(String key,Object value,long timeOut,TimeUnit timeUnit);
	
	
	/**
	 * 根据key获取值
	 * @param key
	 * @return
	 */
	public Object getValue(String key);
	
	/**
	 * 根据key删除redis数据
	 * @param key
	 */
	public void deleteKeyValue(String key);

}
