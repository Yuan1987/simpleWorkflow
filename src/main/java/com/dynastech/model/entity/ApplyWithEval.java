package com.dynastech.model.entity;

import java.util.Date;

/**
 * 申请与对应的测评表 实体
 * 
 * @author yuanhb
 *
 */
public class ApplyWithEval extends Apply{
	
	/**
	 * 测评表Id
	 */
	private String evaluationFormId;
	
	/**
	 * 测评表状态
	 */
	private String evalStatus=ModelEnum.EVALSTATUS_0.getValue();
	
	/**
	 * 测评表提交时间
	 */
	private Date evalsubmittime;
	
	/**
	 * 测评表 的审核流程Id
	 */
	private String evalProcessId;
	
	
	public String getEvaluationFormId() {
		return evaluationFormId;
	}
	
	public void setEvaluationFormId(String evaluationFormId) {
		this.evaluationFormId = evaluationFormId;
	}
	
	public String getEvalStatus() {
		return evalStatus;
	}
	
	public void setEvalStatus(String evalStatus) {
		this.evalStatus = evalStatus;
	}
	
	public Date getEvalsubmittime() {
		return evalsubmittime;
	}
	
	public void setEvalsubmittime(Date evalsubmittime) {
		this.evalsubmittime = evalsubmittime;
	}
	
	public String getEvalProcessId() {
		return evalProcessId;
	}
	
	public void setEvalProcessId(String evalProcessId) {
		this.evalProcessId = evalProcessId;
	}
   
}