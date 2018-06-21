package com.dynastech.system.service;


import java.util.List;
import java.util.Map;

import com.dynastech.system.entity.Serial;
import com.dynastech.system.entity.SerialExample;
import com.dynastech.system.entity.UserSerial;
import com.dynastech.system.entity.UserSerialExample;

/**
 * @author yuan
 *
 */
public interface ISerialService {
	
	public List<Serial> selectByExample(SerialExample se);
	
	/**
	 * @param id
	 * @return
	 */
	public Serial findById(String id);
	
	
	/**
	 * 新增
	 * @param dic
	 * @return
	 */
	public int add(Serial obj);
	
	/**
	 * 修改
	 * @param dic
	 * @return
	 */
	public int update(Serial obj);
	
	/**
	 * 查询此序列的人员
	 * @param sid
	 * @return
	 */
	List<UserSerial> selectUserBySerialId(String sid,String type);
	
	
	/**
	 * 序列小组关联人员
	 * 
	 * @return
	 */
	int addGl(String sid,List<String> uids,String type);
	
	/**
	 * @param id
	 * @param type
	 * @return
	 */
	int editGl(String id,String type);
	
	/**
	 * @param id
	 * @return
	 */
	int deleteGl(String id);
	
	/**
	 * @param queryText
	 * @return
	 */
	public List<Map<String,String>> userListForSelect2(String serialId);
	
	
	List<UserSerial> selectByExample(UserSerialExample use);

}
