package com.dynastech.model.entity;

import com.dynastech.flow.entity.Procinst;

/**
 * 流程实例 bean
 * @author yuan
 */
public class FlowBean extends Procinst{
	
	private String startUserName;
	
	public String getStartUserName() {
		return startUserName;
	}

	public void setStartUserName(String startUserName) {
		this.startUserName = startUserName;
	}
	
}
