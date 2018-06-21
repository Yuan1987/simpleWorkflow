package com.dynastech.system.service;

import java.util.List;
import java.util.Map;

import com.dynastech.system.entity.Role;
import com.dynastech.system.entity.RoleExample;
import com.dynastech.system.entity.RoleResourceKey;
import com.dynastech.system.entity.RoleType;


/**
 * @author  yuan
 */
public interface IRoleManagerService {
	
	public List<Role>selectRoleByExample(RoleExample re);
	
	/**
	 * 通过组织机构查询role 包含下级组织结构的角色
	 * @param orgId
	 * @return
	 */
	public List<Role>selectRoleByOrgId(String orgId,String searchText);
	
	/**
	 * @param role
	 * @param resoureIds
	 * @return
	 */
	public int add(Role role,String[] resoureIds);
	
	/**
	 * @param id
	 * @return
	 */
	public Role findById(String id);
	
	/**
	 * @param role
	 * @param resoureIds
	 * @return
	 */
	public int update(Role role,String[] resoureIds);
	
	/**
	 * 角色id
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 获取角色类型
	 * @return
	 */
	public List<RoleType>findRoleType();
	
	/**
	 * 通过roleId查询角色对应的资源权限
	 * @param roleId
	 * @return
	 */
	public List<RoleResourceKey> selectRoleReourceByRoleId(List<String> roleIds);
	
	/**
	 * 通过组织机构查询role
	 * @param orgId
	 * @return
	 */
	public List<Map<String,String>>selectRoleByOrgIdForSelect2(String [] orgIds,String searchText);
	
	/**
	 * 通过userid 查询用户的角色
	 * @return
	 */
	public List<Role> selectRolesByUserId(String userId);
	
	/**
	 * 查询用户在某组织的角色
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public List<Role> selectRolesByUserId(String userId,String orgId);
	
	/**
	 * 通过组织机构查询role
	 * @param orgIds
	 * @return
	 */
	public List<Role>selectRoleByOrgIds(List<String> orgIds,String typeId);
	
	
}
