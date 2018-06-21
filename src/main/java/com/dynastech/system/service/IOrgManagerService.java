package com.dynastech.system.service;

import java.util.List;

import com.dynastech.system.entity.Organization;
import com.dynastech.system.entity.OrganizationExample;


/**
 * @author  yuan
 */
public interface IOrgManagerService {
	
	/**
	 * 查找所有组织机构  for tree
	 * @return
	 */
	public List<Organization> selectOrganizationForTree();
	
	/**
	 * @param id
	 * @return
	 */
	public Organization findById(String id);
	
	/**
	 * 新增
	 * @param org
	 * @param pobjs 所有上级节点信息
	 * @return
	 */
	public int add(Organization org,String pobjs);
	
	/**
	 * 修改
	 * @param org
	 * @return
	 */
	public int update(Organization org);
	
	/**
	 * 逻辑删除
	 * @param id
	 * @param pobjs 所有上级节点信息
	 * @return
	 */
	public int delete(String id,String pobjs);
	
	/**
	 * 查找用户所在的组织ids
	 * @return
	 */
	public List<String> selectOrgIdsByUserId(String userId);
	
	/**
	 * 查找用户所在的组织
	 * @return
	 */
	public List<Organization> selectOrgsUserId(String userId);
	
	public List<Organization> selectByExample(OrganizationExample example);
}
