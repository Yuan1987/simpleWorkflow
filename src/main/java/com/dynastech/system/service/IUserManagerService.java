package com.dynastech.system.service;

import java.util.List;

import com.dynastech.model.entity.UserPostInfo;
import com.dynastech.system.entity.User;
import com.dynastech.system.entity.UserExample;


/**
 * @author  yuan
 */
public interface IUserManagerService {
	
	/**
	 * 通过组织机构查询user
	 * @param orgId
	 * @return
	 */
	public List<User>selectUserByOrgId(String orgId,String searchText);
	
	/**
	 * @param user
	 * @param roleIds
	 * @param orgIds
	 * @return
	 */
	public int add(User user,String[] roleIds,String orgId);
	
	/**
	 * @param user
	 * @param roleIds
	 * @param orgIds
	 * @return
	 */
	public int update(User user,String[] roleIds,String orgId);
	
	
	/**
	 * 处理人员与角色、组织关系
	 * 
	 * @param userId
	 * @param roleIds
	 * @param orgId
	 * @return
	 */
	public int userHandle(String userId,String[] roleIds,String orgId);
	
	
	/**
	 * @param ue
	 * @return
	 */
	public List<User> selectByExample(UserExample ue);
	
	/**
	 * @param userName
	 * @return
	 */
	public User selectByUserName(String userName);
	
	/**
	 * @param id
	 * @return
	 */
	public User selectById(String id);
	
	/**
	 * @param Id
	 * @return
	 */
	public int delete(String Id,String orgId);
	
	
	/**
	 * 获取下一审批环节 候选人id
	 * @param userId 发起人id
	 * @param nextFlowKey 下一节点key
	 * @return
	 */
	public List<User> findLeadersByOrgId(String userId, String nextKey,String serialId);
	
	/**
	 * @param roleName
	 * @return
	 */
	public List<User> findUsersByRoleName(String roleName);
	
	/**
	 * 查询就职信息
	 * @param userId
	 * @return
	 */
	UserPostInfo findUserPostInfoByUserId(String userId);
}
