package com.dynastech.system.service;

import java.util.List;

import com.dynastech.system.entity.Resource;
import com.dynastech.system.entity.ResourceExample;


/**
 * @author  yuan
 */
public interface IResourceManagerService {
	
	/**
	 * @return
	 */
	public List<Resource>selectByExample(ResourceExample example);
	
	/**
	 * 查询树形菜单 带权限
	 * @param userid
	 * @return
	 */
	public List<Resource>findMenus(String userId);
	
	/**
	 * 新增
	 * @param resource
	 * @return
	 */
	public int add(Resource resource);
	
	/**
	 * @param resource
	 * @return
	 */
	public int update(Resource resource);
	
	/**
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * @param id
	 * @return
	 */
	public Resource findById(String id);
	
	/**
	 * @param userId
	 * @param type
	 * @return
	 */
	public List<Resource> findResourceByUserId(String userId,String type);
	
}
