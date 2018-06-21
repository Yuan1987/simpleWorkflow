package com.dynastech.model.entity;

public enum ModelEnum {
	STATUS_0("草稿"),STATUS_1("审核中"),STATUS_2("已通过"),STATUS_3("未通过"),STATUS_4("被退回"),
	
	EVALSTATUS_0("草稿"),
	EVALSTATUS_1("审核中"),
	EVALSTATUS_2("已完成"),
	EVALSTATUS_3("未通过"),
	EVALSTATUS_4("被退回"),
	
	FLOW_NODE1_CODE("bbmld"),
	FLOW_NODE2_CODE("syb"),
	FLOW_NODE3_CODE("xlxz"),
	FLOW_NODE4_CODE("cpr"),
	FLOW_NODE5_CODE("hz"),
	FLOW_NODE6_CODE("gb")
	;
	
	
	
	
	// 成员变量
	private String name;

	// 构造方法
	private ModelEnum(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.name;
	}
}
