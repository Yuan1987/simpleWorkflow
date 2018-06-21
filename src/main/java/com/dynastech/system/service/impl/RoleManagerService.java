package com.dynastech.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dynastech.system.entity.Role;
import com.dynastech.system.entity.RoleExample;
import com.dynastech.system.entity.RoleResourceExample;
import com.dynastech.system.entity.RoleResourceKey;
import com.dynastech.system.entity.RoleType;
import com.dynastech.system.entity.RoleTypeExample;
import com.dynastech.system.mapper.RoleMapper;
import com.dynastech.system.mapper.RoleResourceMapper;
import com.dynastech.system.mapper.RoleTypeMapper;
import com.dynastech.system.service.IRoleManagerService;

@Service
public class RoleManagerService implements IRoleManagerService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
	@Autowired
	private RoleTypeMapper roleTypeMapper;
	
	@Override
	public List<Role> selectRoleByExample(RoleExample re) {
		return roleMapper.selectByExample(re);
	}
	
	@Override
	public List<Role> selectRoleByOrgId(String orgId, String searchText) {
		return roleMapper.selectRolesByOrgId(orgId, searchText);
	}
	
	@Override
	@Transactional
	public int add(Role role, String[] resoureIds) {
	
		int i=roleMapper.insertSelective(role);
		
		List<RoleResourceKey> list=new ArrayList<RoleResourceKey>();
		
		for(String rid:resoureIds){
			
			RoleResourceKey rk=new RoleResourceKey();
			rk.setRoleid(role.getId());
			rk.setResourceid(rid);
			list.add(rk);
		};
		roleResourceMapper.insertBatch(list);
		return i;
	}
	
	@Override
	public Role findById(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	@Transactional
	public int update(Role role, String[] resoureIds) {
	
		int i=roleMapper.updateByPrimaryKeySelective(role);
		
		RoleResourceExample rre=new RoleResourceExample();
		rre.createCriteria().andRoleidEqualTo(role.getId());
		roleResourceMapper.deleteByExample(rre);
		
		List<RoleResourceKey> list=new ArrayList<RoleResourceKey>();
		for(String rid:resoureIds){
			RoleResourceKey rk=new RoleResourceKey();
			rk.setRoleid(role.getId());
			rk.setResourceid(rid);
			list.add(rk);
		};
		roleResourceMapper.insertBatch(list);
		return i;
	}
	
	@Override
	@Transactional
	public int delete(String id) {
		
		Role role=new Role();
		role.setId(id);
		role.setIsdeleted(true);
		
		int i=roleMapper.updateByPrimaryKeySelective(role);
		//删除角色对应的资源权限
		RoleResourceExample rre=new RoleResourceExample();
		rre.createCriteria().andRoleidEqualTo(id);
		roleResourceMapper.deleteByExample(rre);
		
		return i;
	}
	
	@Override
	public List<RoleType> findRoleType() {
		
		RoleTypeExample rte=new RoleTypeExample();
		
		rte.createCriteria().andIsdeletedEqualTo(false);
		
		return roleTypeMapper.selectByExample(rte);
	}
	
	@Override
	public List<RoleResourceKey> selectRoleReourceByRoleId(List<String> roleIds) {
		
		RoleResourceExample rre=new RoleResourceExample();
		rre.createCriteria().andRoleidIn(roleIds);
		
		return roleResourceMapper.selectByExample(rre);
	}
	
	@Override
	public List<Map<String,String>> selectRoleByOrgIdForSelect2(String[]orgIds, String searchText){
		return roleMapper.selectRolesByOrgIdForSelect2(orgIds, searchText);
	}
	
	@Override
	public List<Role> selectRolesByUserId(String userId) {
		return roleMapper.selectRolesByUserId(userId);
	}
	
	@Override
	public List<Role> selectRolesByUserId(String userId, String orgId) {
		return roleMapper.selectRolesByUserIdAndOrgId(userId, orgId);
	}
	
	@Override
	public List<Role> selectRoleByOrgIds(List<String> orgIds,String typeId) {
		return roleMapper.selectRoleByOrgIds(orgIds,typeId);
	}
}
