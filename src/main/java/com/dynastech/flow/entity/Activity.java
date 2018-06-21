package com.dynastech.flow.entity;

import java.io.Serializable;

public class Activity implements Serializable{
	
	
	public static final String ACTIVITY_CANDIDATE_TYPE_USER="user";
	
	public static final String ACTIVITY_CANDIDATE_TYPE_ROLE="role";

	private static final long serialVersionUID = -817326984961582236L;
	
	/**
	 * 节点名称
	 */
	private String name;
	
	/**
	 * 节点key
	 */
	private String key;
	
	/**
	 * 上次节点
	 */
	/*private String preActivityKey;*/
	
	/**
	 * 下一节点
	 */
	private String nextActivityKey;
	
	/**
	 * 子流程
	 */
	private Boolean multiInstance;
	
	/**
	 * 候选类型（userId 或 roleId）
	 */
	private String candidateType=ACTIVITY_CANDIDATE_TYPE_USER;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNextActivityKey() {
		return nextActivityKey;
	}

	public void setNextActivityKey(String nextActivityKey) {
		this.nextActivityKey = nextActivityKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Boolean getMultiInstance() {
		return multiInstance;
	}
	
	public void setMultiInstance(Boolean multiInstance) {
		this.multiInstance = multiInstance;
	}
	
	public String getCandidateType() {
		return candidateType;
	}
	
	public void setCandidateType(String candidateType) {
		this.candidateType = candidateType;
	}

	@Override
	public String toString() {
		return "Activity [name=" + name + ", key=" + key + ", nextActivityKey="
				+ nextActivityKey + "]";
	}
}
