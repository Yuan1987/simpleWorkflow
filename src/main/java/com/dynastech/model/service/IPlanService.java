package com.dynastech.model.service;

import com.dynastech.model.entity.Plan;
import com.dynastech.model.entity.PlanExample;

import java.util.List;

/**
 * @author ganlu
 * @date   2017/10/17.
 */
public interface IPlanService {


    /**
     * 查询计划
     *
     * @param example 条件
     * @return 返回结果集
     */
    List<Plan> selectByExample(PlanExample example);

    /**
     * 按主键查询计划
     *
     * @param id 主键
     * @return
     */
    Plan selectByPrimaryKey(String id);

    /**
     * 新增计划
     *
     * @param record
     * @return 返回成功条数
     */
    int insertPlan(Plan record);

    /**
     * 修改计划
     *
     * @param record
     * @return 返回成功条数
     */
    int updatePlan(Plan record);


    /**
     * 逻辑删除计划
     *
     * @param id 计划ID
     * @return 成功执行条数
     */
    int deletePlan(String id);


    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    int batchDeletePlan(String... ids);

}
