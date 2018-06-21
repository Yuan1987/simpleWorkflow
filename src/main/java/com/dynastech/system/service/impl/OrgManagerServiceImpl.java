package com.dynastech.system.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.dynastech.system.entity.Organization;
import com.dynastech.system.entity.OrganizationDepth;
import com.dynastech.system.entity.OrganizationDepthKey;
import com.dynastech.system.entity.OrganizationExample;
import com.dynastech.system.entity.OrganizationUser;
import com.dynastech.system.entity.OrganizationUserExample;
import com.dynastech.system.mapper.OrganizationDepthMapper;
import com.dynastech.system.mapper.OrganizationMapper;
import com.dynastech.system.mapper.OrganizationUserMapper;
import com.dynastech.system.service.IOrgManagerService;

@Service
public class OrgManagerServiceImpl implements IOrgManagerService {
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Autowired
	private OrganizationDepthMapper organizationDepthMapper;
	
	@Autowired
	private OrganizationUserMapper organizationUserMapper;
	
	@Override
	public List<Organization> selectOrganizationForTree() {
		
		OrganizationExample oe =new OrganizationExample();
		oe.createCriteria().andIsdeletedEqualTo(false);
		
		//先查全部 无权限过滤
		
		return organizationMapper.selectByExample(oe);
	}

	@Override
	public Organization findById(String id) {
		return organizationMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int add(Organization org,String pobjs) {
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> parents = (List<Map<String,Object>>) JSONArray.parse(pobjs);
		
		int a=organizationMapper.insertSelective(org);
		
		OrganizationDepth od=new OrganizationDepth();
		for(Map<String,Object> p:parents){
			
			od.setParentid(p.get("id")+"");
			od.setChildid(org.getId());
			od.setParenttypeid(p.get("type")+"");
			od.setParentdepth(Integer.parseInt(p.get("depth")+""));
			od.setChilddepth(org.getDepth());
			organizationDepthMapper.insertSelective(od);
		}
		//自己本身
		od.setParentid(org.getId());
		od.setChildid(org.getId());
		od.setParenttypeid(org.getTypeid());
		od.setParentdepth(org.getDepth());
		od.setChilddepth(org.getDepth());
		organizationDepthMapper.insertSelective(od);
		
		return a;
	}
	
	@Override
	public int update(Organization org) {
		return organizationMapper.updateByPrimaryKeySelective(org);
	}
	
	@Override
	@Transactional
	public int delete(String id,String pobjs) {
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> parents = (List<Map<String,Object>>) JSONArray.parse(pobjs);
		
		//删除层级关系
		OrganizationDepthKey odk=new OrganizationDepthKey();
		for(Map<String,Object> p:parents){
			odk.setParentid(p.get("id")+"");
			odk.setChildid(id);
			organizationDepthMapper.deleteByPrimaryKey(odk);
		}
		
		//删除层级关系（自己本身）
		odk.setParentid(id);
		odk.setChildid(id);
		organizationDepthMapper.deleteByPrimaryKey(odk);
		
		//逻辑删除组织
		Organization org=new Organization();
		org.setId(id);
		org.setIsdeleted(true);
		return organizationMapper.updateByPrimaryKeySelective(org);
	}

	@Override
	public List<String> selectOrgIdsByUserId(String userId){
		
		OrganizationUserExample oue=new OrganizationUserExample();
		
		oue.createCriteria().andUseridEqualTo(userId);
		
		List<OrganizationUser> oulist= organizationUserMapper.selectByExample(oue);
		
		List<String> ids=new Vector<String>();
		for(OrganizationUser ou:oulist){
			ids.add(ou.getOrganizationid());
		}
		
		return ids;
	}
	
	
	@Override
	public List<Organization> selectOrgsUserId(String userId){
		
		List<String> ids=this.selectOrgIdsByUserId(userId);
		
		if(ids.isEmpty()){
			return null;
		}
		
		OrganizationExample oe=new OrganizationExample();
		
		oe.createCriteria().andIsdeletedEqualTo(false).andIdIn(ids);
		
		return organizationMapper.selectByExample(oe);
	}
	
	@Override
	public List<Organization> selectByExample(OrganizationExample example) {
		
		return organizationMapper.selectByExample(example);
	}
}
