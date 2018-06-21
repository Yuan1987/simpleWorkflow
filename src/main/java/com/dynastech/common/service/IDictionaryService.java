package com.dynastech.common.service;


import java.util.List;
import java.util.Map;

import com.dynastech.common.entity.Dictionary;
import com.dynastech.common.entity.DictionaryExample;

public interface IDictionaryService {
	


	public List<Map<String,Object>> getDicKind();

	/**
	 * @param de
	 * @return
	 */
	public List<Dictionary> selectByExample(DictionaryExample de);
	
	
	/**
	 * 通过字段的kind 获取字段列表
	 * 
	 * 由于 序列这个类型 不用字典存储，为了减少改动 此方法作特殊处理
	 * 
	 * @param kind
	 * @return
	 */
	List<Dictionary> findByKind(String kind);
	
	
	/**
	 * @param id
	 * @return
	 */
	public Dictionary findById(String id);
	
	
	/**
	 * 新增
	 * @param dic
	 * @return
	 */
	public int add(Dictionary dic);
	
	/**
	 * 修改
	 * @param dic
	 * @return
	 */
	public int update(Dictionary dic);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int batchDelete(String ... ids);

}
