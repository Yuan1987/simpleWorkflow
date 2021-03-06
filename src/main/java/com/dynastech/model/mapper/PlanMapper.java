package com.dynastech.model.mapper;

import com.dynastech.model.entity.Plan;
import com.dynastech.model.entity.PlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int countByExample(PlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int deleteByExample(PlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int insert(Plan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int insertSelective(Plan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    List<Plan> selectByExample(PlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    Plan selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int updateByExampleSelective(@Param("record") Plan record, @Param("example") PlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int updateByExample(@Param("record") Plan record, @Param("example") PlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int updateByPrimaryKeySelective(Plan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_plan
     *
     * @mbggenerated Tue Oct 17 10:50:37 CST 2017
     */
    int updateByPrimaryKey(Plan record);
}