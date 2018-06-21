package com.dynastech.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dynastech.base.global.Constants;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.entity.UserPostInfo;
import com.dynastech.model.entity.UserPostInfoExample;
import com.dynastech.model.mapper.UserPostInfoMapper;
import com.dynastech.system.entity.Organization;
import com.dynastech.system.entity.OrganizationExample;
import com.dynastech.system.entity.OrganizationUser;
import com.dynastech.system.entity.OrganizationUserExample;
import com.dynastech.system.entity.RoleUser;
import com.dynastech.system.entity.RoleUserExample;
import com.dynastech.system.entity.User;
import com.dynastech.system.entity.UserExample;
import com.dynastech.system.entity.UserSerial;
import com.dynastech.system.mapper.OrganizationUserMapper;
import com.dynastech.system.mapper.RoleUserMapper;
import com.dynastech.system.mapper.UserMapper;
import com.dynastech.system.service.IOrgManagerService;
import com.dynastech.system.service.ISerialService;
import com.dynastech.system.service.IUserManagerService;

@Service
public class UserManagerService implements IUserManagerService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrganizationUserMapper ouMapper;
	
	@Autowired
	private RoleUserMapper ruMapper;
	
	@Autowired
	private IOrgManagerService orgManagerService;
	
	@Autowired
	private ISerialService serialService;
	
	@Autowired
	private UserPostInfoMapper userPostInfoMapper;
	
	@Override
	public List<User> selectUserByOrgId(String orgId,String searchText) {
		
		return userMapper.selectUsersByOrgId(orgId,searchText);
	}
	
	@Override
	@Transactional
	public int add(User user, String[] roleIds, String orgId) {
		
		int i=userMapper.insertSelective(user);
		
		this.userHandle(user.getId(), roleIds, orgId);
		
		return i;
	}
	
	@Override
	@Transactional
	public int update(User user, String[] roleIds, String orgId) {
		
		int i=userMapper.updateByPrimaryKeySelective(user);
		
		this.userHandle(user.getId(), roleIds, orgId);
		
		return i;
	}
	
	@Override
	@Transactional
	public int userHandle(String userId, String[] roleIds, String orgId) {
		//删除人员对应的组织信息 并新增 新的组织信息
		OrganizationUserExample oue=new OrganizationUserExample();
		oue.createCriteria().andUseridEqualTo(userId).andOrganizationidEqualTo(orgId);
		ouMapper.deleteByExample(oue);
		OrganizationUser ou=new OrganizationUser();
		ou.setUserid(userId);
		ou.setOrganizationid(orgId);
		ou.setOrdinal(0);
		ouMapper.insertSelective(ou);
		
		//删除人员对应的角色信息 并新增 新的角色信息
		RoleUserExample rue=new RoleUserExample();
		rue.createCriteria().andUseridEqualTo(userId).andOrganizationidEqualTo(orgId);
		ruMapper.deleteByExample(rue);
		RoleUser ru=new RoleUser();
		for(String roleId:roleIds){
			ru.setRoleid(roleId);
			ru.setUserid(userId);
			ru.setOrdinal(0);
			ru.setOrganizationid(orgId);
			ruMapper.insertSelective(ru);
		}
		return 1;
	}
	
	@Override
	public List<User> selectByExample(UserExample ue) {
		return userMapper.selectByExample(ue);
	}
	
	@Override
	public User selectByUserName(String userName) {
		
		UserExample ue=new UserExample();
		ue.createCriteria().andIsdeletedEqualTo(false).andAccountnameEqualTo(userName);
		
		List<User> list=this.selectByExample(ue);
		
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public User selectById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	@Transactional
	public int delete(String id,String orgId) {
		
		//删除本组织下的 人员关系数据 
		OrganizationUserExample oue=new OrganizationUserExample();
		oue.createCriteria().andUseridEqualTo(id).andOrganizationidEqualTo(orgId);
		ouMapper.deleteByExample(oue);
		
		//删除本组织下的 人员角色
		RoleUserExample rue=new RoleUserExample();
		rue.createCriteria().andUseridEqualTo(id).andOrganizationidEqualTo(orgId);
		ruMapper.deleteByExample(rue);
		
		//该人员 是否还在其他组织部门  如没有 就注销人员
		OrganizationUserExample oue2=new OrganizationUserExample();
		oue2.createCriteria().andUseridEqualTo(id);
		int count=ouMapper.countByExample(oue2);
		
		if(count==0){
			userMapper.deleteByPrimaryKey(id);
		}
		return 1;
	}
	
	@Override
	public List<User>  findLeadersByOrgId(String userId, String nextKey,String serialId){
		
		List <User> list=new ArrayList<User>();
		
		if(ModelEnum.FLOW_NODE1_CODE.getValue().equals(nextKey)){
			//本部门领导审批
			List<String> orgIds=orgManagerService.selectOrgIdsByUserId(userId);
			list= userMapper.findLeadersByOrgId(orgIds,Constants.ROLE_NAME_BMLD);
		}else if(ModelEnum.FLOW_NODE2_CODE.getValue().equals(nextKey)){
			//事业部领导审批
			//该流程发起人 所在的部门
			List<Organization>orgs=orgManagerService.selectOrgsUserId(userId);
			List<String> orgIds= new Vector<String>();
			for(Organization org:orgs){
				orgIds.add(org.getParentid());
			}
			list= userMapper.findLeadersByOrgId(orgIds,Constants.ROLE_NAME_SYBLD);
		}else if(ModelEnum.FLOW_NODE3_CODE.getValue().equals(nextKey)||ModelEnum.FLOW_NODE5_CODE.getValue().equals(nextKey)){
			//序列小组 (审核、指派测评人||汇总) 查询该序列的领导
			List<UserSerial> slist= serialService.selectUserBySerialId(serialId, Constants.SERIAL_USER_TYPE_0);
			
			for(UserSerial us:slist){
				User u=new User();
				u.setId(us.getUid());
				list.add(u);
			}
		}else if(ModelEnum.FLOW_NODE4_CODE.getValue().equals(nextKey)){
			//评分人
			//序列小组 (查询该序列的成员
			List<UserSerial> slist= serialService.selectUserBySerialId(serialId, Constants.SERIAL_USER_TYPE_1);
			for(UserSerial us:slist){
				User u=new User();
				u.setId(us.getUid());
				list.add(u);
			}
		}else if(ModelEnum.FLOW_NODE6_CODE.getValue().equals(nextKey)){
			//公布环节 找人力资源
			OrganizationExample oe=new OrganizationExample();
			oe.createCriteria().andIdEqualTo(Constants.HRDEPTID);
			List<Organization>orgs= orgManagerService.selectByExample(oe);
			List<String> orgIds= new Vector<String>();
			for(Organization org:orgs){
				orgIds.add(org.getId());
			}
			list= userMapper.findLeadersByOrgId(orgIds,Constants.ROLE_NAME_BMLD);
		}
		
		return list;
	}
	
	@Override
	public List<User> findUsersByRoleName(String roleName) {
		return userMapper.findUsersByRoleName(roleName);
	}
	
	@Override
	public UserPostInfo findUserPostInfoByUserId(String userId){
		
		UserPostInfoExample upie=new UserPostInfoExample();
		upie.createCriteria().andUseridEqualTo(userId);
		
		List<UserPostInfo> list=userPostInfoMapper.selectByExample(upie);
		
		return list.size()>0?list.get(0):null;
	}
}
