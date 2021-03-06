package com.dynastech.flow.entity;

import java.util.Date;

public class Task {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.NAME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.PROC_DEF_ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private String procDefId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.TASK_DEF_KEY
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private String taskDefKey;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.PROC_INST_ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private String procInstId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.ASSIGNEE
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private String assignee;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.START_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private Date startTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.CLAIM_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private Date claimTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.END_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private Date endTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.PRIORITY
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private Integer priority;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column F_TASK.isDeleted
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	private Boolean isdeleted=false;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.ID
	 * @return  the value of F_TASK.ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.ID
	 * @param id  the value for F_TASK.ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.NAME
	 * @return  the value of F_TASK.NAME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.NAME
	 * @param name  the value for F_TASK.NAME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.PROC_DEF_ID
	 * @return  the value of F_TASK.PROC_DEF_ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public String getProcDefId() {
		return procDefId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.PROC_DEF_ID
	 * @param procDefId  the value for F_TASK.PROC_DEF_ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.TASK_DEF_KEY
	 * @return  the value of F_TASK.TASK_DEF_KEY
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public String getTaskDefKey() {
		return taskDefKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.TASK_DEF_KEY
	 * @param taskDefKey  the value for F_TASK.TASK_DEF_KEY
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.PROC_INST_ID
	 * @return  the value of F_TASK.PROC_INST_ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public String getProcInstId() {
		return procInstId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.PROC_INST_ID
	 * @param procInstId  the value for F_TASK.PROC_INST_ID
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.ASSIGNEE
	 * @return  the value of F_TASK.ASSIGNEE
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.ASSIGNEE
	 * @param assignee  the value for F_TASK.ASSIGNEE
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.START_TIME
	 * @return  the value of F_TASK.START_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.START_TIME
	 * @param startTime  the value for F_TASK.START_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.CLAIM_TIME
	 * @return  the value of F_TASK.CLAIM_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public Date getClaimTime() {
		return claimTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.CLAIM_TIME
	 * @param claimTime  the value for F_TASK.CLAIM_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.END_TIME
	 * @return  the value of F_TASK.END_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.END_TIME
	 * @param endTime  the value for F_TASK.END_TIME
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.PRIORITY
	 * @return  the value of F_TASK.PRIORITY
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.PRIORITY
	 * @param priority  the value for F_TASK.PRIORITY
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column F_TASK.isDeleted
	 * @return  the value of F_TASK.isDeleted
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public Boolean getIsdeleted() {
		return isdeleted;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column F_TASK.isDeleted
	 * @param isdeleted  the value for F_TASK.isDeleted
	 * @mbggenerated  Tue Nov 07 15:27:22 CST 2017
	 */
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
}