package com.dynastech.model.service.impl;

import com.dynastech.model.entity.SysAbility;
import com.dynastech.model.entity.SysAbilityExample;
import com.dynastech.model.mapper.SysAbilityMapper;
import com.dynastech.model.service.ISysAbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by ganlu on 2017/9/18.
 */
@Service
public class SysAbilityService implements ISysAbilityService {
   @Autowired
   private SysAbilityMapper sysAbilityMapper;


    /**
     * 获取不重复能力项
     * @return
     */
    @Override
    public List<Map<String, String>> getSysAbilityItem() {
        return sysAbilityMapper.getSysAbilityItem();
    }

    @Override
    public List<SysAbility> getSysAbilityList(SysAbilityExample sysAbilityExample) {
        return sysAbilityMapper.selectByExample(sysAbilityExample);
    }

	@Override
	public int addSysAbility(SysAbility sa) {
		
		return sysAbilityMapper.insertSelective(sa);
	}

	@Override
	public SysAbility findAbilityById(String id) {
		return sysAbilityMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(SysAbility sa) {
		return sysAbilityMapper.updateByPrimaryKeySelective(sa);
	}

	@Override
	public int batchDelete(String... ids) {
		SysAbility ability = new SysAbility();
		ability.setIsdeleted(true);
		
		SysAbilityExample example = new SysAbilityExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		return sysAbilityMapper.updateByExampleSelective(ability, example);
	}

	

}
