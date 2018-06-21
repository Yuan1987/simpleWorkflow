package com.dynastech.model.service;

import com.dynastech.model.entity.SysAbility;
import com.dynastech.model.entity.SysAbilityExample;

import java.util.List;
import java.util.Map;

/**
 * Created by ganlu on 2017/9/18.
 */
public interface ISysAbilityService {


    public List<Map<String, String>> getSysAbilityItem();

    public List<SysAbility> getSysAbilityList(SysAbilityExample sysAbilityExample);
    
    public SysAbility findAbilityById(String id);
    
    public int addSysAbility(SysAbility sa);
    
    public int update(SysAbility sa);
    
    public int batchDelete(String ...ids);
}
