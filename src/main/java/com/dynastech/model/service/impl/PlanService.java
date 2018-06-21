package com.dynastech.model.service.impl;

import com.dynastech.model.entity.Plan;
import com.dynastech.model.entity.PlanExample;
import com.dynastech.model.mapper.PlanMapper;
import com.dynastech.model.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 计划服务类
 * @author Jack
 * @date 2017年10月17
 */
@Service
public class PlanService implements IPlanService {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public List<Plan> selectByExample(PlanExample example) {
        return planMapper.selectByExample(example);
    }

    @Override
    public Plan selectByPrimaryKey(String id) {
        return planMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertPlan(Plan record) {
        return planMapper.insert(record);
    }

    @Override
    public int updatePlan(Plan record) {
        return planMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deletePlan(String id) {
        //逻辑删除
        Plan record= planMapper.selectByPrimaryKey(id);
        record.setIsdeleted(true);
        return planMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int batchDeletePlan(String... ids) {
        Plan plan = new Plan();
        plan.setIsdeleted(true);

        PlanExample example = new PlanExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        return planMapper.updateByExampleSelective(plan, example);
    }
}
