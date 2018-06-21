package com.dynastech.base.log;

public enum LogType {
	
	DIC_ADD("字典新增"),DIC_UPDATE("字典修改"),DIC_DELETE("字典删除"),
	APPLY_ADD("申请新增"),APPLY_UPDATE("申请修改"),APPLY_COMMIT("申请提交"),
	EVALU_ADD("评测表新增"),EVALU_UPDATE("评测表修改"),EVALU_DELETE("评测表删除"),
	PLAN_ADD("计划新增"),PLAN_UPDATE("计划修改"),PLAN_DELETE("计划删除"),
	;

	// 成员变量
	private String type;

	// 构造方法
	private LogType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.type;
	}
}
