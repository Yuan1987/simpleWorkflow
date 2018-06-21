package com.dynastech.flow.mapper;

import com.dynastech.flow.entity.Procinst;
import com.dynastech.flow.entity.ProcinstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcinstMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int countByExample(ProcinstExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int deleteByExample(ProcinstExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int insert(Procinst record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int insertSelective(Procinst record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	List<Procinst> selectByExample(ProcinstExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	Procinst selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int updateByExampleSelective(@Param("record") Procinst record, @Param("example") ProcinstExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int updateByExample(@Param("record") Procinst record, @Param("example") ProcinstExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int updateByPrimaryKeySelective(Procinst record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table f_procinst
	 * @mbggenerated  Mon Nov 06 11:06:00 CST 2017
	 */
	int updateByPrimaryKey(Procinst record);
}