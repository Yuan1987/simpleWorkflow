package com.dynastech.model.mapper;

import com.dynastech.model.entity.MarkSetting;
import com.dynastech.model.entity.MarkSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarkSettingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int countByExample(MarkSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int deleteByExample(MarkSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int insert(MarkSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int insertSelective(MarkSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    List<MarkSetting> selectByExample(MarkSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    MarkSetting selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int updateByExampleSelective(@Param("record") MarkSetting record, @Param("example") MarkSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int updateByExample(@Param("record") MarkSetting record, @Param("example") MarkSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int updateByPrimaryKeySelective(MarkSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_markSetting
     *
     * @mbggenerated Tue Oct 24 15:35:57 CST 2017
     */
    int updateByPrimaryKey(MarkSetting record);
}