package com.dynastech.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dynastech.model.entity.FlowBean;
import com.dynastech.model.entity.TaskBean;

public interface FlowMapper {
	
	List<FlowBean> selectProcint(@Param("status")Boolean status,@Param("queryStr") String queryStr);
	
	List<TaskBean> selectTaskByProcinstId(@Param("procinstId")String procinstId);
}