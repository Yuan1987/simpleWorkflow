package com.data.model.mapper;

import com.data.model.entity.VDEPT;
import com.data.model.entity.VDEPTExample;
import java.util.List;

public interface VDEPTMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table V_DEPT
	 * @mbggenerated  Thu Nov 30 11:21:50 CST 2017
	 */
	int countByExample(VDEPTExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table V_DEPT
	 * @mbggenerated  Thu Nov 30 11:21:50 CST 2017
	 */
	List<VDEPT> selectByExample(VDEPTExample example);
}