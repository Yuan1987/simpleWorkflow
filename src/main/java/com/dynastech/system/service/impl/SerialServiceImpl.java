package com.dynastech.system.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dynastech.system.entity.Serial;
import com.dynastech.system.entity.SerialExample;
import com.dynastech.system.entity.UserSerial;
import com.dynastech.system.entity.UserSerialExample;
import com.dynastech.system.mapper.RoleMapper;
import com.dynastech.system.mapper.SerialMapper;
import com.dynastech.system.mapper.UserSerialMapper;
import com.dynastech.system.service.ISerialService;

@Service
public class SerialServiceImpl implements ISerialService{
	
	@Autowired
	private SerialMapper serialMapper;
	
	@Autowired
	private UserSerialMapper userSerialMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Serial> selectByExample(SerialExample se) {
		return serialMapper.selectByExample(se);
	}

	@Override
	public Serial findById(String id) {
		return serialMapper.selectByPrimaryKey(id);
	}

	@Override
	public int add(Serial obj) {
		return serialMapper.insert(obj);
	}

	@Override
	public int update(Serial obj) {
		return serialMapper.updateByPrimaryKeySelective(obj);
	}
	
	@Override
	public List<UserSerial> selectUserBySerialId(String sid,String type) {
		return userSerialMapper.selectSerialUserById(sid,type);
	}
	
	@Override
	@Transactional
	public int addGl(String sid, List<String> uids, String type){
		
		UserSerialExample use=new UserSerialExample();
		use.createCriteria().andSidEqualTo(sid).andUidIn(uids);
		userSerialMapper.deleteByExample(use);
		
		UserSerial us=new UserSerial();
		
		for(String uid :uids){
			us.setId(UUID.randomUUID().toString());
			us.setSid(sid);
			us.setUid(uid);
			us.setType(type);
			userSerialMapper.insert(us);
			//此次应给该人员增加序列人员角色 以便获取菜单权限
		}
		
		return 1;
	}

	@Override
	public int editGl(String id, String type) {
		
		UserSerial us=new UserSerial();
		us.setId(id);
		us.setType(type);
		return userSerialMapper.updateByPrimaryKeySelective(us);
	}
	
	@Override
	public int deleteGl(String id) {
		return userSerialMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public List<Map<String, String>> userListForSelect2(String sid) {
		return userSerialMapper.userListForSelect2(sid);
	}
	
	@Override
	public List<UserSerial> selectByExample(UserSerialExample use) {
		return userSerialMapper.selectByExample(use);
	}
}
