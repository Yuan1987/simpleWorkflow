package com.dynastech.common.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynastech.base.global.Constants;
import com.dynastech.common.entity.Dictionary;
import com.dynastech.common.entity.DictionaryExample;
import com.dynastech.common.mapper.DictionaryMapper;
import com.dynastech.common.service.IDictionaryService;
import com.dynastech.system.entity.Serial;
import com.dynastech.system.entity.SerialExample;
import com.dynastech.system.mapper.SerialMapper;

@Service
public class DictionaryService implements IDictionaryService{
	
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Autowired
	private SerialMapper serialMapper;


	@Override
	public List<Map<String, Object>> getDicKind() {
		return dictionaryMapper.getDicKind();
	}

	@Override
	public List<Dictionary> selectByExample(DictionaryExample de) {
		return dictionaryMapper.selectByExample(de);
	}
	
	@Override
	public List<Dictionary> findByKind(String kind) {
		
		if(Constants.DIC_KIND_SBXL.equals(kind)){
			SerialExample se=new SerialExample();
			se.createCriteria().andIsdeletedEqualTo(false).andIdNotEqualTo("0");
			
			List<Dictionary> diclist=new ArrayList<Dictionary>();
			
			List<Serial> serialList=serialMapper.selectByExample(se);
			
			serialList.forEach(s->{
				Dictionary dic=new Dictionary();
				dic.setCode(s.getId());
				dic.setDetail(s.getName());
				diclist.add(dic);
			});
			return diclist;
		}else{
			DictionaryExample de=new DictionaryExample();
			de.createCriteria().andKindEqualTo(kind);
			de.setOrderByClause("code");
			return dictionaryMapper.selectByExample(de);
		}
		
		
	}
	
	@Override
	public Dictionary findById(String id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int add(Dictionary dic) {
		return dictionaryMapper.insertSelective(dic);
	}
	
	@Override
	public int update(Dictionary dic) {
		return dictionaryMapper.updateByPrimaryKeySelective(dic);
	}
	
	@Override
	public int batchDelete(String ...ids) {
		
		Dictionary dic=new Dictionary();
		dic.setIsdeleted(true);
		
		DictionaryExample de=new DictionaryExample();
		
		de.createCriteria().andIdIn(Arrays.asList(ids));
		
		return dictionaryMapper.updateByExampleSelective(dic, de);
	}
}
